package com.marketdata.market_data_service.dto;


import lombok.Data;

@Data
public class LoginRequest {

    private String username;
    private String password;
}
