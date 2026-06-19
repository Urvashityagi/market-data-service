# Market Data Service

## Prerequisites

* Java 21
* Maven

## Clone Repository

```bash
git clone https://github.com/Urvashityagi/market-data-service.git
cd market-data-service
```

## Build Project

```bash
mvn clean install
```

## Run Application

```bash
mvn spring-boot:run
```

Application will start on:

```text
http://localhost:8080
```

---

## H2 Database Console

Open:

```text
http://localhost:8080/h2-console
```

Use:

```text
JDBC URL: jdbc:h2:mem:marketdb
Username: sa
Password:
```

---

## Login API

Default User:

```text
Username: admin
Password: admin123
```

```bash
curl --request POST \
  --url http://localhost:8080/api/auth/login \
  --header 'Content-Type: application/json' \
  --data '{
    "username":"admin",
    "password":"admin123"
}'
```

---

## Market Overview API

Returns top 20 spot trading pairs by volume from OKX.

```bash
curl --request GET \
  --url http://localhost:8080/api/market/top20
```

OKX Public API:

```text
https://www.okx.com/api/v5/market/tickers?instType=SPOT
```

---

## Order Book Subscription

Subscribe to a trading pair order book.

```bash
curl --request POST \
  --url http://localhost:8080/api/orderbook/subscribe/BTC-USDT
```

