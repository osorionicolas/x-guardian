#!/bin/sh

docker stop x-guardian-client
docker rm x-guardian-client
docker rmi xmentor/x-guardian-client:latest

docker build -t xmentor/x-guardian-client:latest .
docker push xmentor/x-guardian-client:latest