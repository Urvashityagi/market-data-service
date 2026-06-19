package com.marketdata.market_data_service.service;

import com.marketdata.market_data_service.dto.LoginRequest;
import com.marketdata.market_data_service.dto.LoginResponse;
import com.marketdata.market_data_service.entity.User;
import com.marketdata.market_data_service.repository.UserRepository;
import com.marketdata.market_data_service.security.JwtUtil;
import com.marketdata.market_data_service.security.SessionManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtil;
    private final SessionManager sessionManager;

    public AuthService(UserRepository userRepository, PasswordEncoder encoder, JwtUtil jwtUtil, SessionManager sessionManager) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.jwtUtil = jwtUtil;
        this.sessionManager = sessionManager;
    }


    public LoginResponse login(
            LoginRequest request) {

        User user =
                userRepository
                        .findByUsername(
                                request.getUsername())
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "User not found"));

        if (!encoder.matches(
                request.getPassword(),
                user.getPassword())) {

            throw new RuntimeException(
                    "Invalid password");
        }

        String token =
                jwtUtil.generateToken(
                        user.getUsername());

        sessionManager.register(
                user.getUsername(),
                token);

        return new LoginResponse(token);
    }
}
