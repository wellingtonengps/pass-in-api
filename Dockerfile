FROM maven:3.8.7-openjdk-18-slim AS Build

WORKDIR /usr/src/app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src

RUN mvn package -DskipTests

FROM eclipse-temurin:21.0.2_13-jre-alpine AS deploy
WORKDIR /usr/src/app
COPY --from=Build /usr/src/app/target/pass-in-0.0.1-SNAPSHOT.jar .

EXPOSE 8080

CMD ["java", "-jar", "pass-in-0.0.1-SNAPSHOT.jar"]