package com.marketdata.market_data_service.controller;

import com.marketdata.market_data_service.dto.LoginRequest;
import com.marketdata.market_data_service.dto.LoginResponse;
import com.marketdata.market_data_service.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public LoginResponse login(
            @RequestBody LoginRequest request) {

        return authService.login(request);
    }
}