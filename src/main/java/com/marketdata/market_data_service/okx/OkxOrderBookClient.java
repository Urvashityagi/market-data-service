package com.marketdata.market_data_service.okx;


import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;

@Component
@RequiredArgsConstructor
public class OkxOrderBookClient {

    private final SimpMessagingTemplate template;

    private WebSocket webSocket;

    @PostConstruct
    public void connect() {

        HttpClient.newHttpClient()
                .newWebSocketBuilder()
                .buildAsync(
                        URI.create(
                                "wss://ws.okx.com:8443/ws/v5/public"),
                        new OkxWebSocketListener(template)
                )
                .thenAccept(ws -> {

                    this.webSocket = ws;

                    subscribe("BTC-USDT");
                });
    }

    public void subscribe(String symbol) {

        String payload =
                """
                {
                  "op":"subscribe",
                  "args":[
                    {
                      "channel":"books5",
                      "instId":"%s"
                    }
                  ]
                }
                """.formatted(symbol);

        webSocket.sendText(
                payload,
                true
        );
    }
}
