FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean install
# RUN mvn clean package


#
# Package stage
#
FROM openjdk:17-jdk-slim 
COPY --from=build /target/house-0.0.1-SNAPSHOT.jar house.jar
EXPOSE 9090
ENTRYPOINT ["java","-jar","house.jar"]
