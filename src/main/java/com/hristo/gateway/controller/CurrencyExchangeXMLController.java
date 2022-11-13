package com.hristo.gateway.controller;

import com.hristo.gateway.dto.CommandDto;
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

import static com.hristo.gateway.controller.CurrencyExchangeController.REQUEST_DUPLICATION_ERROR;

@RestController
@RequestMapping(path = "/xml_api", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.TEXT_XML_VALUE})
public class CurrencyExchangeXMLController {

    @Autowired
    private CurrencyService currencyService;

    @PostMapping(value = "/command", consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.TEXT_XML_VALUE})
    public ResponseEntity<Object> getCurrencyExchangeRate(@RequestBody CommandDto commandDto) {
        boolean result = currencyService.saveRequest(commandDto);

        if (result) {
            Object body = currencyService.getCurrency(commandDto);
            return body == null || body instanceof Collection<?> && ((Collection<?>) body).isEmpty() ?
                    ResponseEntity.noContent().build() :
                    ResponseEntity.ok().body(body);
        }

        ResponseDto response = new ResponseDto();
        response.setMessage(String.format(REQUEST_DUPLICATION_ERROR, commandDto.getId()));
        return ResponseEntity.badRequest().body(response);
    }
}
