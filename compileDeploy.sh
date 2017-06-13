#!/bin/bash
rm architecture/payara/miligo.war
mvn clean install
cp target/miligo.war architecture/payara/
cd architecture
docker-compose rm -f
docker-compose build
docker-compose up
