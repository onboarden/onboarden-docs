# index.rb

require 'sinatra'
require 'jwt'
require "sinatra/cors"

API_KEY = 'YOUR_API_KEY' # change this
key = OpenSSL::PKey::RSA.new Base64.decode64 API_KEY 

set :allow_origin, "http://localhost:3000"
set :allow_methods, "GET"
set :allow_headers, "content-type"
set :port, 8000

get '/' do
  content_type :json

  payload = {
    # あなたのアプリケーションのユーザーを一意に識別するID（任意）
    # 付与すると、ユーザーがインポートを中断した場合でも再アクセス時に途中から再開できます
    sub: 'user-id-of-your-app'
    exp: (Time.now + 60 * 60).to_i 
  }
  token = JWT.encode payload, key, algorithm='RS256'

  {token: token}.to_json
end

