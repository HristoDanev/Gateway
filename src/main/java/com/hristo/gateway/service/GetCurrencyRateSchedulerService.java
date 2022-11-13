package com.hristo.gateway.service;

import com.hristo.gateway.dto.CurrencyExchangeRateDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetCurrencyRateSchedulerService {

    @Autowired
    GetCurrencyRateService getCurrencyExchangeRateService;

    private static final Logger log = LoggerFactory.getLogger(GetCurrencyRateSchedulerService.class);

    @Scheduled(fixedDelayString = "${scheduler.interval}")
    public void getCurrencyRates() {
        log.info("Saving new currency rates started");

        Optional<CurrencyExchangeRateDto> response = getCurrencyExchangeRateService.get();
        response.ifPresentOrElse(r -> getCurrencyExchangeRateService.save(r), () -> log.error("Could not retrieve currencies"));

        log.info("Saving new currency rates finished");
    }

}