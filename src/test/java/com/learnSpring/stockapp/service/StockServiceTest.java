package com.learnSpring.stockapp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CompletableFuture;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StockServiceTest {

    @Autowired
    private StockServiceWebflux stockService;

    @Autowired
    private StockServiceRestTemplate stockMvcService;

    @Test
    public void testGetRealTimeStockPrices() {
        long startTime = System.currentTimeMillis();

        CompletableFuture<String> futureAAPL = stockService.getRealTimeStockPrices("AAPL").toFuture();
        CompletableFuture<String> futureGOOG = stockService.getRealTimeStockPrices("GOOG").toFuture();
        CompletableFuture<String> futureAMZN = stockService.getRealTimeStockPrices("AMZN").toFuture();
        CompletableFuture<String> futureMSFT = stockService.getRealTimeStockPrices("MSFT").toFuture();
        CompletableFuture<String> futureTSLA = stockService.getRealTimeStockPrices("TSLA").toFuture();

        CompletableFuture.allOf(futureAAPL, futureGOOG, futureAMZN, futureMSFT, futureTSLA).join();

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        System.out.println("Total time for all requests: " + duration + " ms");

        assertThat(duration).isLessThan(2000);
    }

    @Test
    public void testGetRealTimeStockPricesV1() {

        String[] symbols = {"AAPL", "GOOG", "AMZN", "MSFT", "TSLA"};
        long startTime = System.currentTimeMillis();

        // Create an array of CompletableFuture tasks for concurrent requests
        CompletableFuture<String>[] futures = new CompletableFuture[symbols.length];

        // Fire concurrent requests
        for (int i = 0; i < symbols.length; i++) {
            int j = i;
            futures[i] = CompletableFuture.supplyAsync(() -> {
                // Call the method in StockService to get stock options
                return stockMvcService.getRealTimeStockPricesV1(symbols[j]);
            });
        }

        // Wait for all futures to complete
        CompletableFuture.allOf(futures).join();

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        System.out.println("Total time for all concurrent requests: " + duration + " ms");

        assertThat(duration).isLessThan(2000);
    }
}