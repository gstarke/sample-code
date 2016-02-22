#!/bin/sh

mvn clean package

java -jar target/records-lib-0.0.1-SNAPSHOT.jar
