package com.hristo.gateway.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.HashMap;

@Data
public class CurrencyExchangeRateDto {
    private HashMap<String, BigDecimal> rates;
    private String base;
    private long timestamp;
}
