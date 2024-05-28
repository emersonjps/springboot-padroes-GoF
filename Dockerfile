FROM maven:3.8.5-openjdk-17 AS build

COPY  src /app/src
COPY pom.xml /app

WORKDIR /app
RUN mvn clean install

FROM amazoncorretto:17-alpine-jdk

COPY --from=build /app/target/desafio-dio-0.0.1-SNAPSHOT.jar /app/app.jar

WORKDIR /app

EXPOSE 8080

CMD [ "java", "-jar", "app.jar" ]