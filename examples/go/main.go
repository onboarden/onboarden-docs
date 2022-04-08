// main.go

package main

import (
	"encoding/base64"
	"fmt"
	"log"
	"time"

	"bytes"
	"encoding/json"
	"net/http"

	"github.com/golang-jwt/jwt"
)

const API_KEY = "YOUR_API_KEY" // change this

func main() {
	http.HandleFunc("/", handler)
	log.Fatal(http.ListenAndServe(":8000", nil))
}

type Response struct {
	Token string `json:"token"`
}

func handler(w http.ResponseWriter, r *http.Request) {
	// cors
	w.Header().Add("Access-Control-Allow-Origin", "http://localhost:3000")

	response := Response{Token: generateToken()}

	var buf bytes.Buffer
	enc := json.NewEncoder(&buf)
	if err := enc.Encode(&response); err != nil {
		log.Fatal(err)
	}

	_, err := fmt.Fprint(w, buf.String())
	if err != nil {
		return
	}
}

func generateToken() string {
	claims := jwt.MapClaims{
		// あなたのアプリケーションのユーザーを一意に識別するID（任意）
		// 付与すると、ユーザーがインポートを中断した場合でも再アクセス時に途中から再開でき、またユーザーごとに列のマッピング情報が記憶されます
		"sub": "user-id-of-your-app",
		"exp": time.Now().Add(time.Hour * 1).Unix(),
	}

	token := jwt.NewWithClaims(jwt.SigningMethodRS256, claims)

	rawKey, err := base64.StdEncoding.DecodeString(API_KEY)
	if err != nil {
		log.Fatal(err)
	}

	key, err := jwt.ParseRSAPrivateKeyFromPEM(rawKey)
	if err != nil {
		log.Fatal(err)
	}

	tokenString, err := token.SignedString(key)
	if err != nil {
		log.Fatal(err)
	}

	return tokenString
}
