package com.hristo.gateway.dto.mapper;

import com.hristo.gateway.dto.CurrencyExchangeRateDto;
import com.hristo.gateway.model.CurrencyRate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.TimeZone;

@Component
public class CurrencyRateMapper {

    public Set<CurrencyRate> mapToEntityList(CurrencyExchangeRateDto currencyExchangeRateDto) {
        Set<CurrencyRate> rates = new HashSet<>();

        currencyExchangeRateDto.getRates().forEach((k,v) -> rates.add(createRate(k,v,currencyExchangeRateDto.getTimestamp())));

        return rates;
    }

    private CurrencyRate createRate(String code, BigDecimal rate, long timestamp) {
        CurrencyRate currencyRate = new CurrencyRate();
        currencyRate.setCode(code);
        currencyRate.setRate(rate);
        currencyRate.setDateTime(LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp),
                TimeZone.getDefault().toZoneId()));
        return currencyRate;
    }
}
