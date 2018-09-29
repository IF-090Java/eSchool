#!/bin/bash
docker run --name ejournal_mysql -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=ejournal_mysql -d mysql:5.6
docker build -f Dockerfile -t docker-ejournal
docker run -p 8080:8080 docker-ejournal