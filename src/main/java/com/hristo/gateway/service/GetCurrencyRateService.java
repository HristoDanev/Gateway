package com.hristo.gateway.service;

import com.hristo.gateway.dto.CurrencyExchangeRateDto;
import com.hristo.gateway.dto.mapper.CurrencyRateMapper;
import com.hristo.gateway.model.CurrencyRate;
import com.hristo.gateway.repository.CurrencyRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.UnknownHttpStatusCodeException;

import java.util.Optional;
import java.util.Set;

@Service
public class GetCurrencyRateService {
    @Value("${currencies.url}")
    private String currencyUrl;
    @Value("${currencies.key}")
    private String apiKey;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private CurrencyRateMapper currencyRateMapper;
    @Autowired
    private CurrencyRateRepository currencyRateRepository;

    public Optional<CurrencyExchangeRateDto> get() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("apikey", apiKey);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<CurrencyExchangeRateDto> response = null;
        try{
            response = restTemplate.exchange(currencyUrl, HttpMethod.GET, entity, CurrencyExchangeRateDto.class);
            return Optional.ofNullable(response.getBody());
        } catch (HttpClientErrorException | HttpServerErrorException | UnknownHttpStatusCodeException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public void save(CurrencyExchangeRateDto response) {
        Set<CurrencyRate> rates = currencyRateMapper.mapToEntityList(response);
        currencyRateRepository.saveAll(rates);
    }
}
