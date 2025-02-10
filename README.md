# Stock Service API - Concurrent Request Handling

## Overview

The **Stock Service API** is a simple service that fetches real-time stock options for a given stock symbol using **Spring WebFlux** and **Spring RestTemplate**. The project demonstrates how to handle concurrent requests efficiently and interact with external APIs, such as Alpha Vantage, to retrieve stock data.

### Key Technologies Used:
- **Spring WebFlux**: A non-blocking, reactive web framework for handling requests asynchronously.
- **Spring RestTemplate**: A synchronous client used to make HTTP requests to external services (such as Alpha Vantage) to retrieve stock data.
- **Java CompletableFuture**: For simulating and managing multiple concurrent requests to the stock service.

## Goal

The primary goal of this project is to:
- Create an **asynchronous, reactive service** using **Spring WebFlux** that can handle multiple concurrent HTTP requests.
- Integrate with an **external stock API** (e.g., Alpha Vantage) to fetch stock options for a given stock symbol.
- Demonstrate the ability to process **multiple concurrent requests** to the stock service using **`CompletableFuture`** for better performance and scalability.
- Ensure that the service can handle **concurrent requests** without blocking threads, improving responsiveness.

## Features

- **Concurrent Requests**: The service can handle multiple requests to fetch stock options for a given symbol concurrently. This is achieved using **`CompletableFuture`** to manage multiple asynchronous requests.
- **External API Integration**: The service interacts with an external stock options API (e.g., Alpha Vantage) via **`Spring WebClient`** to fetch real-time data.
- **Testing Concurrent Requests**: We simulate multiple concurrent requests to the **`StockService`** in unit tests using **JUnit** and **Spring WebFlux** to validate how well the service handles high concurrency.
- **Performance Measurement**: The application measures how long it takes to complete concurrent requests and checks that all responses are valid.

## How to Run the Application

### Prerequisites

- **Java 11+** installed
- **Gradle** (or Maven) for building the project
- **Alpha Vantage API key** (or any other external stock API key)

### Steps

1. Clone the repository:

   ```bash
   git clone https://github.com/your-repo/stock-service.git
   cd stock-service
   ```

2. Add your **API Key** for the external stock API (e.g., Alpha Vantage) to `application.properties`:

   ```properties
   alpha.vantage.api=your-api-key-here
   ```

3. Build and run the application:

   Using Gradle:

   ```bash
   ./gradlew bootRun
   ```

   Using Maven:

   ```bash
   mvn spring-boot:run
   ```

4. The application will run on a random port, and you can make a request to the stock endpoint:

   ```bash
   curl http://localhost:8080/stocks/options/AAPL
   ```

   You should receive the stock options for the symbol `AAPL`.

### Running the Tests

To run the tests (which include concurrent request tests):

```bash
./gradlew test
```

This will execute all tests, including the concurrency tests, and output the results to the console.


## Conclusion

This project demonstrates how to build a scalable and performant **Stock Service API** that handles **concurrent requests** efficiently using **Spring WebFlux** and **Spring `RestTemplate`**. It also highlights how to use **`CompletableFuture`** for managing asynchronous tasks in Java and ensures the service works well under concurrent load. The project provides a solid foundation for building scalable microservices that can interact with external APIs and handle multiple requests simultaneously.
