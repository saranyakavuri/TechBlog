# Build using maven
FROM maven:3.5-jdk-8 AS build
WORKDIR /build
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src /build/src
RUN mvn -f /build/pom.xml clean install -DskipTests

# Run application
FROM openjdk:8u121-jre-alpine
EXPOSE 8123:8123
RUN mkdir -p /opt/tech
WORKDIR /opt/tech
COPY users.json /opt/tech/users.json
COPY --from=build /build/target/techblog.jar /opt/tech/techblog.jar
ENTRYPOINT ["java", "-jar", "techblog.jar"]