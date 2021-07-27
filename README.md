# springboot-session

SpringBoot で HTTP Session を扱ってみる

## Redis

```bash
docker run --name redis -p 6379:6379 -d redis
```

## 確認方法

1. セッションクリア（http://localhost:8080/sync/index）
2. セッション参照するだけのリクエスト（http://localhost:8080/sync/read）※5秒後にレスポンス
3. セッションに書き込みするリクエスト（http://localhost:8080/sync/foo） ※直ぐ返す

2のレスポンスが返る前に3を呼び出すと3の無いようで最終的にはRedisに書き込まれるっぽい。。。これバグっぽいけどね。。。