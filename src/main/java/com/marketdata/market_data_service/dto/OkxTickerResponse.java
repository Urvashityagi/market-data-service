package com.marketdata.market_data_service.dto;


import lombok.Data;

import java.util.List;

@Data
public class OkxTickerResponse {

    private String code;
    private String msg;
    private List<TickerData> data;
}