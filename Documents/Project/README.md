# E-Commerce RESTful API & Order Processing System

Starter backend built with Java 17 and Spring Boot. It includes the essential boundaries for product catalog, authentication, cart management, concurrent checkout and transaction tracking.

## Run locally

1. Start infrastructure: `docker compose up -d`
2. Run the application with Java 17: `mvn spring-boot:run`
3. Open Swagger UI at `http://localhost:8080/swagger-ui.html`

Configuration is supplied through environment variables (`DB_URL`, `DB_USERNAME`, `DB_PASSWORD`, `REDIS_HOST`, `JWT_SECRET`).

## API outline

- `POST /api/v1/auth/login` (starter users: `user/password` and `admin/password`)
- `GET /api/v1/products`, `GET /api/v1/products/{id}`, `POST /api/v1/products`
- `GET /api/v1/cart`, `POST /api/v1/cart/items`, `DELETE /api/v1/cart/items/{productId}`
- `POST /api/v1/orders/checkout`, `GET /api/v1/orders/{id}`
- `GET /api/v1/transactions/{id}`

This is an intentionally focused foundation: payment-provider callbacks, refresh tokens and production migrations can be added without changing the module boundaries.
