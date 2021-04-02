#!/bin/bash

#$1 is for container name eg alvin-api
#$2 is for image name eg ia/alvin-api

cp ../../../build/libs/mongo-javaee-demo-0.0.1.war .
docker stop $1
docker rm $1
docker image rm $2
docker build -t $2 .
docker push $2:latest
