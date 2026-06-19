package com.marketdata.market_data_service.dto;


import java.math.BigDecimal;

public record MarketTickerDTO(
        String symbol,
        BigDecimal lastPrice,
        BigDecimal change24h,
        BigDecimal volume24h
) {
}