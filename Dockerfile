# First stage: complete build environment
FROM maven:3.9.1-amazoncorretto-17 AS builder

# add pom.xml and source code
ADD ./pom.xml pom.xml
ADD ./src src/

# package jar
RUN mvn clean package

# Second stage: minimal runtime environment
From amazoncorretto:17

# copy jar from the first stage
COPY --from=builder target/ItemTrackerDynamoDBRest-1.0-SNAPSHOT.jar ItemTrackerDynamoDBRest-1.0-SNAPSHOT.jar

EXPOSE 8080

CMD ["java", "-jar", "ItemTrackerDynamoDBRest-1.0-SNAPSHOT.jar"]