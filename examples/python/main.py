import base64
import time

import jwt
from fastapi import FastAPI

app = FastAPI()

API_KEY = "YOUR API KEY"  # Change here!
PRIVATE_KEY = base64.b64decode(API_KEY)


@app.get("/")
async def index():
    userId = "user-id-of-your-app"
    token = jwt.encode({"sub": userId, "exp": int(
        time.time()) + 60 * 30}, PRIVATE_KEY, algorithm="RS256")
    return {"token": token}