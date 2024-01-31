#FROM ubuntu:latest
#LABEL authors="jose"
#ENTRYPOINT ["top", "-b"]

#FROM openjdk:21-jdk-alpine
# FROM openjdk:20-ea-21-jdk-slim early access
#docker pull openjdk:22-jdk
#FROM openjdk:21-jdk
#FROM --platform=linux/amd64 openjdk:17-jdk-alpine3.14
FROM eclipse-temurin:21.0.2_13-jdk-alpine

#docker pull openjdk:21-jdk
#docker pull openjdk:19-ea-21-jdk-slim

RUN addgroup -S paymentchain && adduser -S admin -G paymentchain
USER admin:paymentchain

VOLUME /tmp

ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} app.jar
ADD target/${JAR_FILE} app.jar
#ADD target/classes/application.properties application.properties

ENV JAVA_OPTS=""
ENV TZ=America/Asuncion

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar app.jar" ]

