FROM ubuntu:rolling AS base
RUN apt-get update && apt-get install -y openjdk-21-jdk && apt-get install -y maven

FROM base AS build
WORKDIR /usr/src/app
COPY . .
RUN mvn clean package

FROM ubuntu/jre:8-22.04_edge AS deploy
WORKDIR /usr/src/app
COPY --from=build /usr/src/app/target/*.jar .

ENTRYPOINT ["top", "-b"]