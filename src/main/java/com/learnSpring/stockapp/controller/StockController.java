package com.learnSpring.stockapp.controller;

import com.learnSpring.stockapp.service.StockServiceWebflux;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class StockController {

    private final StockServiceWebflux stockService;

    public StockController(StockServiceWebflux stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/stock/options/{symbol}")
    public Mono<String> getRealTimeStockPrices(@PathVariable String symbol) {
        return stockService.getRealTimeStockPrices(symbol);
    }

}
