#!/bin/bash

BASEDIR=$(dirname "$0")

source ./$BASEDIR/.env

# clear all previous docker artifacts
./$BASEDIR/stop.sh

# creating network to run all related containers in
docker network create eschool-network

# starting container with mysql DB
docker run --name eschool-mysql \
    -p :3306 \
    --network eschool-network \
    -e MYSQL_ROOT_PASSWORD=$DATASOURCE_PASSWORD \
    -e MYSQL_DATABASE=$MYSQL_DATABASE \
    -d mysql:5.6

# building app image
docker build -t eschool:1.0 .

# starting app container
docker run --name eschool-backend \
     --env-file ./$BASEDIR/.env \
     --network eschool-network \
     -p 8080:8080 \
     -d eschool:1.0
