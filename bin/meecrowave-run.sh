#!/usr/bin/env bash

set -e

mvn install -pl run-meecrowave -am
mvn meecrowave:run -pl run-meecrowave
