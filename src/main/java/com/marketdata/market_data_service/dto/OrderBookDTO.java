package com.marketdata.market_data_service.dto;


import java.util.List;

public record OrderBookDTO(
        String symbol,
        List<List<String>> bids,
        List<List<String>> asks
) {
}
