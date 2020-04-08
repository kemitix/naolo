#!/usr/bin/env bash

docker pull postgres
docker run --name naolo-db -p 5432:5432 -e POSTGRES_PASSWORD=naolo -d postgres
