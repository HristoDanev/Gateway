package com.hristo.gateway.dto.mapper;

import com.hristo.gateway.dto.ResponseDto;
import com.hristo.gateway.model.CurrencyRate;
import org.springframework.stereotype.Component;

@Component
public class ResponseMapper {
    public ResponseDto mapToDto(CurrencyRate currencyRate) {
        ResponseDto response = new ResponseDto();
        response.setCurrency(currencyRate.getCode());
        response.setExchangeRate(currencyRate.getRate());
        response.setDateTime(currencyRate.getDateTime());
        return response;
    }
}
