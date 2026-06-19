package com.marketdata.market_data_service.controller;

import com.marketdata.market_data_service.dto.MarketTickerDTO;
import com.marketdata.market_data_service.service.MarketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/market")
@RequiredArgsConstructor
public class MarketController {

    private final MarketService marketService;

    @GetMapping("/top20")
    public List<MarketTickerDTO> top20() {

        return marketService.getTop20Pairs();
    }
}
