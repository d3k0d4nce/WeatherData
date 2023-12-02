mvn clean install & ^

docker build -t my-app . & ^

docker-compose down & ^
docker-compose up -d

@REM #!/bin/bash
@REM
@REM # Компиляция проекта с помощью Maven
@REM mvn clean install
@REM
@REM # Сборка Docker контейнера
@REM docker build -t my-app .
@REM
@REM # Удаление старых контейнеров и запуск нового контейнера с помощью docker-compose
@REM docker-compose down
@REM docker-compose up -d

