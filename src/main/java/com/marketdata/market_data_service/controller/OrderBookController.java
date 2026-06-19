package com.marketdata.market_data_service.controller;

import com.marketdata.market_data_service.okx.OkxOrderBookClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orderbook")
@RequiredArgsConstructor
public class OrderBookController {

    private final OkxOrderBookClient client;

    @PostMapping("/subscribe/{symbol}")
    public void subscribe(
            @PathVariable String symbol) {

        client.subscribe(symbol);
    }
}