#!/usr/bin/env bash

set -e

mvn install -pl run-spring-boot -am
mvn spring-boot:run -pl run-spring-boot
