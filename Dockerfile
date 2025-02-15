# Use OpenJDK 17 with Alpine Linux
FROM openjdk:17-jdk-slim


# Install Maven
RUN apt-get update && apt-get install -y maven

# Set the working directory in the container
WORKDIR /app

# Copy the Maven project descriptor file (pom.xml) and download dependencies
COPY pom.xml .
RUN mvn clean install -DskipTests || true

# Copy the entire project source code into the container
COPY . .

# Build the application (this will create the JAR file in /app/target/)
RUN mvn package -DskipTests

# Expose the port your Spring Boot application uses
EXPOSE 8028

# Set the entry point for the container
COPY target/CRUDApplication-0.0.1-SNAPSHOT.jar /app/CRUDApplication-0.0.1-SNAPSHOT.jar
CMD ["java", "-jar", "/app/CRUDApplication-0.0.1-SNAPSHOT.jar"]

