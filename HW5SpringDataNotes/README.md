### Использование Docker

[Docker Desktop](https://www.docker.com/products/docker-desktop/)

### Создание Docker контейнера

terminal> docker run --name postgres-hw5 -e POSTGRES_DB=notes_db -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=secret -p 5432:5432 -d postgres:15.5-bullseye

### Вход в контейнер

terminal> docker exec -it postgres-hw5 psql -U admin notes_db