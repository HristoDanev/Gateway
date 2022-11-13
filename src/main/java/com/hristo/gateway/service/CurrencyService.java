package com.hristo.gateway.service;

import com.hristo.gateway.dto.CommandDto;
import com.hristo.gateway.dto.RequestDto;
import com.hristo.gateway.dto.mapper.RequestMapper;
import com.hristo.gateway.dto.mapper.ResponseMapper;
import com.hristo.gateway.model.CurrencyRate;
import com.hristo.gateway.model.Request;
import com.hristo.gateway.repository.CurrencyRateRepository;
import com.hristo.gateway.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CurrencyService {

    private static final String JSON_REQUEST_HASH = "json";
    private static final String XML_REQUEST_HASH = "xml";
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    RequestRepository requestRepository;
    @Autowired
    CurrencyRateRepository currencyRateRepository;
    @Autowired
    RequestMapper requestMapper;
    @Autowired
    ResponseMapper responseMapper;
    @Autowired
    ProduceCurrencyRequestStatistics produceCurrencyRequestStatistics;

    public boolean saveRequest(RequestDto requestDto) {
        Request request = requestMapper.mapToEntity(requestDto);
        Boolean result = redisTemplate.opsForHash().putIfAbsent(JSON_REQUEST_HASH, request.getRequestId(), request);
        if (result) {
            requestRepository.save(request);
            produceCurrencyRequestStatistics.send(request);
            return true;
        }
        return false;
    }

    public boolean saveRequest(CommandDto commandDto) {
        Request request = requestMapper.mapToEntity(commandDto);
        Boolean result = redisTemplate.opsForHash().putIfAbsent(XML_REQUEST_HASH, request.getRequestId(), request);
        if (result) {
            requestRepository.save(request);
            return true;
        }
        return false;
    }

    public Object getCurrency(CommandDto commandDto) {
        if (commandDto.getHistory() != null) {
            LocalDateTime after = LocalDateTime.now().minusHours(commandDto.getHistory().getPeriod());
            List<CurrencyRate> entities = currencyRateRepository.findAllByCodeWithDateTimeAfter(commandDto.getHistory().getCurrency(), after);
            return entities.stream().map(e -> responseMapper.mapToDto(e)).collect(Collectors.toList());
        }
        Optional<CurrencyRate> entity = currencyRateRepository.findFirstByCodeOrderByDateTimeDesc(commandDto.getBody().getCurrency());
        return entity.map(e -> responseMapper.mapToDto(e)).orElse(null);
    }

    public Object getCurrency(RequestDto requestDto) {
        if (requestDto.getPeriod() != 0) {
            LocalDateTime after = LocalDateTime.now().minusHours(requestDto.getPeriod());
            List<CurrencyRate> entities = currencyRateRepository.findAllByCodeWithDateTimeAfter(requestDto.getCurrency(), after);
            return entities.stream().map(e -> responseMapper.mapToDto(e)).collect(Collectors.toList());
        }
        Optional<CurrencyRate> entity = currencyRateRepository.findFirstByCodeOrderByDateTimeDesc(requestDto.getCurrency());
        return entity.map(e -> responseMapper.mapToDto(e)).orElse(null);
    }
}
