#!/bin/bash
docker run --name ejournal_mysql -e MYSQL_ROOT_PASSWORD=root -e MYSQL_USER=admin -e MYSQL_PASSWORD=Admin_Pass01 -e MYSQL_DATABASE=ejournal -d mysql:5.6
docker build -f Dockerfile -t docker-ejournal:1.0 .
docker run --name ejournal -p 8080:8080 --link ejournal_mysql:mysql docker-ejournal:1.0