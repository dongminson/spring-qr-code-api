In the project directory, you can run:

```sh
mvn install
mvn spring-boot:run
```

The API will be accessible at [http://localhost:8080](http://localhost:8080)

You can test it with:

```bash
curl -i -X POST http://localhost:8080/authenticate -H 'Content-Type: application/json' -d '{"email": "admin@hotmail.com", "password": "password"}'
curl -X POST http://localhost:8080/generate -H 'Authorization: Bearer <access token>' -H 'Content-Type: application/json' -d 'random string' --output qr_code.png
```