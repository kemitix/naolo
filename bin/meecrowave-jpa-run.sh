#!/usr/bin/env bash

set -e

mvn install -pl run-meecrowave-jpa -am
mvn meecrowave:run -pl run-meecrowave-jpa
