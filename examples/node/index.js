// index.js

const express = require("express");
const jwt = require("jsonwebtoken");
const addMinutes = require("date-fns/addMinutes");
const cors = require("cors");

const app = express();
app.use(cors({ origin: "http://localhost:3000" }));

const API_KEY = "YOUR_API_KEY"; // change this

app.get("/", async (_req, res) => {
  // あなたのアプリケーションのユーザーを一意に識別するID（任意）
  // 付与すると、ユーザーがインポートを中断した場合でも再アクセス時に途中から再開でき、またユーザーごとに列のマッピング情報が記憶されます
  const userId = "user-id-of-your-app";
  const token = jwt.sign(
    { sub: userId, exp: addMinutes(new Date(), 60).getTime() / 1000 },
    Buffer.from(API_KEY, "base64").toString(),
    { algorithm: "RS256" }
  );
  return res.json({ token });
});

app.listen(8000);
