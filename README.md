# URL Shortener

This is a simple URL shortener application built using Spring Boot and MongoDB. It allows users to create short URLs from long ones and redirects them to the original URLs when they access the short URLs.

## Prerequisites

- Java 11 or higher
- Apache Maven 3.6.3 or higher
- MongoDB 4.0 or higher

## Getting Started

To get started with the URL shortener, follow these steps:

```bash

git clone https://github.com/goeksu/UrlShortenr.git

# Build and run the application:
mvn clean install
java -jar target/url-shortener-0.0.1-SNAPSHOT.jar

# Access the application at http://localhost:8080. Make sure that Mongo is running.
