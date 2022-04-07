<?php

// index.php

require 'vendor/autoload.php';

use Firebase\JWT\JWT;
use Firebase\JWT\Key;

$API_KEY = 'YOUR_API_KEY'; // change this
    
$token = JWT::encode(
  [ 
    // あなたのアプリケーションのユーザーを一意に識別するID（任意）
    // 付与すると、ユーザーがインポートを中断した場合でも再アクセス時に途中から再開できます
    'sub' => 'user-id-of-your-app',
    'exp' => time() + 60 * 60,
  ],
  base64_decode($API_KEY),
  'RS256'
);

header('Content-Type: application/json; charset=utf-8');
header('Access-Control-Allow-Origin', 'http://localhost:3000');
echo json_encode(['token' => $token]);

