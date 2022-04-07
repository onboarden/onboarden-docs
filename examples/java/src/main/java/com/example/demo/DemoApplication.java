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
//        String API_KEY = "YOUR API KEY"; // change this
        String API_KEY = "LS0tLS1CRUdJTiBQUklWQVRFIEtFWS0tLS0tCk1JSUV2d0lCQURBTkJna3Foa2lHOXcwQkFRRUZBQVNDQktrd2dnU2xBZ0VBQW9JQkFRQ2pmZWtKM0JUL1lGZHUKb1FHL29DYVQ5MzdlY1JwaktSdkZOU3dPVEoxUmpoTVk0UndkOGxoQTUzQXJJYk1tbC9ZUk56MkZucHl3WllRbgpGV2VDSmFTd0dMQmVpQXhWVThTN3NHWHhUUFhvdVN0cS9WVjZYYVRvTGNGWSsxaW1CdWRpWlY0Vkc5aVZPeTdOCjVkcEdIRzJwMTFLbnlzVFhiSTR1Q2FlK1JpWnQwbnR6eWJGRDJqMjRsVUxHcjNuYmF4YjBEZ1lOalQ3L3Azd2gKbU9nR1lncWYySzhiVlJZQkE3UEwzU1dMWk5FRTIzN0ZpQ2hVVW9nVDJ5K215d0tFUkFsdXA3WVpZb1JuVjN2Twp2cGR0cytwMXZXQitVL3lPNE1vcWJvSzFoeVEvR2VCajNWVXROWmcreVY3OEZQS1hQaS9kRVFQNG8ydGhjRWlZCllVWG5MTHdCQWdNQkFBRUNnZ0VCQUpQS1h4M3RtQUdlcW9xeit5dXBieFkxRmJlcFVHd3hIZ3h3VzRuMWtnVEcKUk5jcHIwd1c1ZGFPSUdTdEN4Q3BMK1F5Sm1naWtJd1l5SlM0RWFDaHZMVC9MSGZYemlaWUdqQjhianJxRWlXMQpOZDE2Y29teUd6OVRGVVFGdWdZam1Zam1EMTFQbGNLWkJGOVdGN0ZuUTI3VDJjRWFXRVJRdWxvOW1pZ3pxOHQ3CmxtQjZ6SzlLSU80WHVGSTdpOTJMOXNhUTAvNlFJRjUvTzZhS3ByUWFTTGt6bkR0ektlYUFVZVVVTGJWRi9xKy8KenpQem5Jd0thNWdhWVMraVd1TUIzb3BBRVRGbmJMM3FRcUpBVUVGd2NiZldBM2NpcU54RnJ4T1paTmkwRzVMSgplVkNSUk1LVkpkNytDeEFIRHNxMW9QbmhYVytQOTBVTUZ2aHJRQ2N4L0cwQ2dZRUEyUEZrUlBDTzN3SUI3T1FiClpoK0wzY1JrWHE3U0FFcGVLU09hZUhkY0ZZMzh2Ty9ITFpqbVIxZkRlQ0VZaE9wdzBVZTdOcGF1R21lTW9QbFMKK1F4Ty9wL1I5TWtRQkdmeG4vL2RycnVWS2ppTTJibmFDcDE1d21zQVg1OVdWazMzRUptMHZmV1VxbWlMdmlXbgpnRmo0VEtsckpkNldUUk4vSnVMQjVLUTZXbGNDZ1lFQXdPMEhnWHRFSk4xUkJEY0JubVJFOFZUS3c3M0RMYzlEClJoMnlkc1dWSmpEZThXakszcDdqcTFhSHRXdWhWN3VNR1JPMlFZUUUrVktvMzRrYnJIaUZId05uWmhaUStuSVQKN0JqR2Z1V2Q4dHhKd3NlTE9ieEdoQ0xlQ0VCdlM5Y1RJQlgxVVlOTk41S3ZCa3RmQ2tzM3F2Zk1TMlhYMWFYaQptRnFqbVdpNDFXY0NnWUJEaVJJLzYrdHVQVC9BTVZyZU5kL2l6K3EzRnhGRkNlRzZTTjBqdDNTeE9SeCs3TjVDCnNhQ3VnUUhXK2VhYUtpbW8zUDNKQnF0NGlOZmNuUUZaaXAxazR2Z0hqamd3UnZPUGJhWGphWDRNUU1LSVhUVWwKck5SazA4NnY5Zmg0c1pqQ1VEZFdFNGVPYlRGanBuRTRvWG5hMDdzSmRQdXhUdXA4Zm9jQjJLWlp6UUtCZ1FDMApON2NmT1NxNjFSTHRRUVQrRk9SaUc3Y2RpcEpjcEpSUFYwWnl0TGRTNmF3WkRxOFRycEUyYUtwMHlvbjVEblVjCnA5RkUrakk3UlFBRGNHeXRmQmUrbE10dURjS3ZjUWt6THlHZFFKbC9XRkIrNXpBalNzRUVoaDJxejZZbnprMUwKVjdmRjRVV3NlQldQWjdEc1NMOURzaDdnLzgzVEFoYmVBeTE0RE44OFdRS0JnUUNvOWd5V0ZCMVJxT1JOM0hRTQp1eDE2cEZLR0pjYjF1eTlKOUJkQnhMbVIvMllNRy9pMjU0R3p0Nit1SmJ3L0g5RzNtVmppSHdlMU9PWllWa2MyCmVCL29PR29GMUxzVWZnR1JRcUJVVGE4cWcxd0RKYXIyUjNYbDRycWtxb1lhaGcyMXRGYUNVYnZPbmtKbTFBM3YKbFhiTnVodEdENDI3NHVxc2NIdFlUZThvcUE9PQotLS0tLUVORCBQUklWQVRFIEtFWS0tLS0tCg==";

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
