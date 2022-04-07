// src/main/java/com/example/demo/DemoApplication.java

package com.example.demo;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;

@SpringBootApplication
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @GetMapping("/")
    public Response hello() {
        String API_KEY = "YOUR_API_KEY"; // change this

        // あなたのアプリケーションのユーザーを一意に識別するID（任意）
        // 付与すると、ユーザーがインポートを中断した場合でも再アクセス時に途中から再開できます
        String userId = "user-id-of-your-app";
        String token = generateToken(API_KEY, userId);

        return new Response(token);
    }

    private String generateToken(String apiKey, String userId) {
        try {
            String privatePem = new String(Base64.getDecoder().decode(apiKey));
            String privateKeyContent = privatePem.replaceAll("\\n", "").replace("-----BEGIN PRIVATE KEY-----", "").replace("-----END PRIVATE KEY-----", "");
            KeyFactory factory = KeyFactory.getInstance("RSA");
            PKCS8EncodedKeySpec keySpecPKCS8 = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKeyContent));
            PrivateKey privateKey = factory.generatePrivate(keySpecPKCS8);

            Date now = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(now);
            calendar.add(Calendar.HOUR, 1);
            Date expiresAt = calendar.getTime();

            String token = Jwts.builder().setSubject(userId).setExpiration(expiresAt).signWith(privateKey, SignatureAlgorithm.RS256).compact();
            return token;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("generateToken failed");
        }
    }

    public class Response {
        private String token;

        public Response(String token) {
            this.token = token;
        }

        public String getToken() {
            return token;
        }
    }
}
