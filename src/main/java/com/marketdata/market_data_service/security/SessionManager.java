package com.marketdata.market_data_service.security;


import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class SessionManager {

    private final ConcurrentHashMap<String, String>
            activeSessions = new ConcurrentHashMap<>();

    public void register(String username,
                         String token) {

        activeSessions.put(username, token);
    }

    public boolean isValid(String username,
                           String token) {

        return token.equals(
                activeSessions.get(username));
    }
}