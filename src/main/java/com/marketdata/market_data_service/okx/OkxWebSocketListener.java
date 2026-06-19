package com.marketdata.market_data_service.okx;


import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.net.http.WebSocket;
import java.util.concurrent.CompletionStage;

@RequiredArgsConstructor
public class OkxWebSocketListener
        implements WebSocket.Listener {

    private final SimpMessagingTemplate template;

    @Override
    public CompletionStage<?> onText(
            WebSocket webSocket,
            CharSequence data,
            boolean last) {

        template.convertAndSend(
                "/topic/orderbook",
                data.toString()
        );

        return WebSocket.Listener
                .super
                .onText(
                        webSocket,
                        data,
                        last
                );
    }
}