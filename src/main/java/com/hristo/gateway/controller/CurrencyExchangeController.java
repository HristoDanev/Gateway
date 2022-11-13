package com.hristo.gateway.controller;


import com.hristo.gateway.dto.RequestDto;
import com.hristo.gateway.dto.ResponseDto;
import com.hristo.gateway.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(path = "/json_api", produces = MediaType.APPLICATION_JSON_VALUE)
public class CurrencyExchangeController {
    static final String REQUEST_DUPLICATION_ERROR = "Request with id %s is duplicated";

    @Autowired
    private CurrencyService currencyService;

    @PostMapping("/current")
    public ResponseEntity<Object> getCurrencyExchangeRate(@RequestBody RequestDto requestDto) {
        if(currencyService.saveRequest(requestDto)) {
            Object body = currencyService.getCurrency(requestDto);
            return body == null ?
                    ResponseEntity.noContent().build() :
                    ResponseEntity.ok().body(body);
        }

        ResponseDto response = new ResponseDto();
        response.setMessage(String.format(REQUEST_DUPLICATION_ERROR, requestDto.getRequestId()));
        return ResponseEntity.badRequest().body(response);
    }

    @PostMapping("/history")
    public ResponseEntity<Object> getCurrencyHistoricalExchangeRats(@RequestBody RequestDto requestDto) {
        if(currencyService.saveRequest(requestDto)) {
            Object body = currencyService.getCurrency(requestDto);
            return body instanceof Collection<?> && ((Collection<?>) body).isEmpty() ?
                    ResponseEntity.noContent().build() :
                    ResponseEntity.ok().body(body);
        }
        ResponseDto response = new ResponseDto();
        response.setMessage(String.format(REQUEST_DUPLICATION_ERROR, requestDto.getRequestId()));
        return ResponseEntity.badRequest().body(response);
    }
}
