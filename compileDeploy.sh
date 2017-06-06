#!/bin/bash
rm architecture/payara/miligo-ear.ear
mvn clean install
cp miligo-ear/target/miligo-ear.ear architecture/payara/
cd architecture
docker-compose rm -f
docker-compose build
docker-compose up
