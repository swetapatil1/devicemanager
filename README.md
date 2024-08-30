# Device Management System

## Overview

The Device Management System is a RESTful API built with Spring Boot and Spring Data JPA. It provides functionality to manage devices, including creating, reading, updating, and deleting device records, as well as searching for devices by brand.

## Features

- **Add Device**: Create a new device record.
- **Get Device by Identifier**: Retrieve a device by its unique ID.
- **List All Devices**: Fetch all device records.
- **Update Device**: Perform full or partial updates to a device record.
- **Delete Device**: Remove a device record by its ID.
- **Search Device by Brand**: Find devices by their brand name.

## Technologies Used

- **Spring Boot**: Framework for building the RESTful API.
- **Spring Data JPA**: For database interactions and ORM.
- **H2 Database**: Embedded database for development and testing.
- **JUnit 5**: For unit testing.
- **Mockito**: For mocking dependencies in tests.
- **MockMvc**: For testing the controller layer.

## Getting Started

### Prerequisites

- JDK 11 or higher
- Maven or Gradle
- IDE such as IntelliJ IDEA or Eclipse (optional)
- Docker Desktop(To run application using docker)

### Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/swetapatil1/devicemanager.git
2. **Build the project:**
   Using Gradle:
   ```bash
   ./gradlew build
3. **Run the application:**
   Using Gradle:
   ```bash
   ./gradlew bootRun
   ```
   The application will start on 
   ```bash
   http://localhost:8080
4. **API Documentation:**
   API documentation will be accessible on 
   ```bash
   http://localhost:8080/swagger-ui.html
5. **Run the application using Docker:**
   Run following command to create docker image
   ```bash
    docker build --build-arg JAR_VERSION=0.0.1-SNAPSHOT -t devicemanager . --platform linux/amd64
   ```
   Run following command to start the container
   ```bash
    docker run -p 8080:8080 devicemanager
   ```
   The application will start on
   ```bash
   http://localhost:8080
   ```
   API documentation will be accessible on 
   ```bash
   http://localhost:8080/swagger-ui.html