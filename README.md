# SQL Delight Sandbox

This project is a sandbox for SQL Delight, a multiplatform Kotlin library that generates typesafe SQL queries.


# Installation

```
gradle build
docker compose

docker compose -f ./infrastructure/docker-compose.yml start
```

# Usage

Have fun with `src/main/kotlin/Main.kt`

All database example implementations are done in `modules/**`

## Postgres example:

./modules/postgres

https://cashapp.github.io/sqldelight/2.0.2/jvm_postgresql/

## Mysql example:

./modules/mysql

https://cashapp.github.io/sqldelight/2.0.2/jvm_mysql/
