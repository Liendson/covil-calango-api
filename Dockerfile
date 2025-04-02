FROM maven:3.8.4-openjdk-17 AS build

WORKDIR /app

ARG DATASOURCE_URL
ARG DATASOURCE_USER
ARG DATASOURCE_PASSWORD

ENV SPRING_DATASOURCE_URL=${DATASOURCE_URL}
ENV SPRING_DATASOURCE_USERNAME=${DATASOURCE_USER}
ENV SPRING_DATASOURCE_PASSWORD=${DATASOURCE_PASSWORD}

COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/covil-calango-api-0.0.1-SNAPSHOT.jar .

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/covil-calango-api-0.0.1-SNAPSHOT.jar"]