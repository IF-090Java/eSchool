#! /bin/bash

docker stop eschool_mysql eschool
docker rm --volumes eschool_mysql eschool
docker image rm mysql:5.6 docker-eschool:1.0 openjdk:8-jdk-alpine

rm -r target
