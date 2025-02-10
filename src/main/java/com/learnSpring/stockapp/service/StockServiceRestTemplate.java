package com.learnSpring.stockapp.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class StockServiceRestTemplate {

    @Value("${alpha.vantage.api}")
    private String stocksAppApiKey;

    @Value("${alpha.vantage.url}")
    private String stocksAppUrl;

    public String getRealTimeStockPricesV1(String symbol) {
        String url = UriComponentsBuilder.fromUri(URI.create(stocksAppUrl))
                .queryParam("apikey", stocksAppApiKey)
                .queryParam("symbol", symbol)
                .toUriString();

        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.getForObject(url, String.class);
    }
}
