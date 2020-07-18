#!/bin/bash
set -e

java -Dspring.profiles.active=$SPRING_ENV -jar ./poss.jar