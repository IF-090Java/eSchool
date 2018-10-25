#!/bin/bash

docker run --name eschool_mysql -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=ejournal -d mysql:5.6
./mvnw package -Dmaven.test.skip=true
docker build -t docker-eschool:1.0 .
docker run --name eschool -p 8080:8080 --link eschool_mysql:mysql docker-eschool:1.0
