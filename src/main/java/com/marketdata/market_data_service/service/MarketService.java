package com.marketdata.market_data_service.service;

import com.marketdata.market_data_service.dto.MarketTickerDTO;
import com.marketdata.market_data_service.dto.OkxTickerResponse;
import com.marketdata.market_data_service.dto.TickerData;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MarketService {

    private final RestClient restClient;

    @Scheduled(fixedRate = 5000)
    public List<MarketTickerDTO> getTop20Pairs() {

        OkxTickerResponse response =
                restClient.get()
                        .uri("https://www.okx.com/api/v5/market/tickers?instType=SPOT")
                        .retrieve()
                        .body(OkxTickerResponse.class);

        return response.getData()
                .stream()
                .sorted(
                        Comparator.comparing(
                                t -> new BigDecimal(t.getVolCcy24h()),
                                Comparator.reverseOrder()
                        )
                )
                .limit(20)
                .map(this::convert)
                .toList();
    }

    private MarketTickerDTO convert(
            TickerData ticker) {

        BigDecimal last =
                new BigDecimal(ticker.getLast());

        BigDecimal open =
                new BigDecimal(ticker.getOpen24h());

        BigDecimal change =
                last.subtract(open)
                        .divide(open, 4,
                                java.math.RoundingMode.HALF_UP)
                        .multiply(
                                BigDecimal.valueOf(100));

        return new MarketTickerDTO(
                ticker.getInstId(),
                last,
                change,
                new BigDecimal(
                        ticker.getVolCcy24h())
        );
    }
}
