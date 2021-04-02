#!/bin/bash

#$1 is for container name eg alvin-api
#$2 is for image name eg ia/alvin-api

docker stop $1
docker rm $1
docker image rm $2
