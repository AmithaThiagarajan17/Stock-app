package com.learnSpring.stockapp.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class StockServiceWebflux {

    private final WebClient.Builder webClientBuilder;

    @Value("${alpha.vantage.api}")
    private String stocksAppApiKey;

    @Value("${alpha.vantage.url}")
    private String stocksAppUrl;

    public StockServiceWebflux(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    public Mono<String> getRealTimeStockPrices(String symbol) {
        return webClientBuilder.baseUrl(stocksAppUrl)
                .build()
                .get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("apikey", stocksAppApiKey)
                        .queryParam("symbol", symbol)
                        .build())
                .retrieve()
                .bodyToMono(String.class);
    }


}
