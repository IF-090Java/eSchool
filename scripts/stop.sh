#! /bin/bash

#TODO check resources before removing

docker stop eschool-mysql
docker stop eschool-backend

docker rm eschool-mysql
docker rm eschool-backend

docker rmi $(docker images | grep  "eschool*" | awk '{printf "%s:%s", $1, $2}')
docker rmi mysql:5.6
docker rmi openjdk:8-jdk-alpine

docker network rm eschool-network