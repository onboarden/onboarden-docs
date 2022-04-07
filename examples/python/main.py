import base64
import time

import jwt
from fastapi import FastAPI
from starlette.middleware.cors import CORSMiddleware

app = FastAPI()

app.add_middleware(
    CORSMiddleware,
    allow_origins=["http://localhost:3000"],
)

API_KEY = "YOUR_API_KEY"  # Change here!
PRIVATE_KEY = base64.b64decode(API_KEY)


@app.get("/")
async def index(): 
    # あなたのアプリケーションのユーザーを一意に識別するID（任意）
    # 付与すると、ユーザーがインポートを中断した場合でも再アクセス時に途中から再開できます
    userId = "user-id-of-your-app"
    token = jwt.encode({"sub": userId, "exp": int(
        time.time()) + 60 * 30}, PRIVATE_KEY, algorithm="RS256")
    return {"token": token}
