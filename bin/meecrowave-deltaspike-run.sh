#!/usr/bin/env bash

set -e

mvn install -pl run-meecrowave-deltaspike -am
mvn meecrowave:run -pl run-meecrowave-deltaspike
