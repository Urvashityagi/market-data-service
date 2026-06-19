package com.marketdata.market_data_service.dto;


import lombok.Data;

@Data
public class TickerData {

    private String instId;

    private String last;

    private String volCcy24h;

    private String open24h;
}
