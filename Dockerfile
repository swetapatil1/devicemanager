# Use an official Java runtime as a parent image
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Define a build argument for the JAR version
ARG JAR_VERSION=0.0.1-SNAPSHOT

# Copy the built JAR file to the container
# Use the ARG variable in the JAR path
COPY build/libs/devicemanager-${JAR_VERSION}.jar app.jar

# Expose the port on which the application will run
EXPOSE 8080

# Define the command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
