package com.hristo.gateway.dto.mapper;

import com.hristo.gateway.dto.CommandDto;
import com.hristo.gateway.dto.RequestDto;
import com.hristo.gateway.model.Request;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Component
public class RequestMapper {

    public Request mapToEntity(RequestDto requestDto) {
        Request request = new Request();
        request.setRequestId(requestDto.getRequestId());
        request.setClientId(requestDto.getClient());
        request.setName(requestDto.getPeriod() != 0 ? "json_api/history" : "json_api/current");
        request.setTime(LocalDateTime.now(ZoneOffset.UTC));
        return request;
    }

    public Request mapToEntity(CommandDto commandDto) {
        Request request = new Request();
        request.setRequestId(commandDto.getId());
        request.setName("xml_api/command");
        if (commandDto.getHistory() != null) {
            request.setClientId(commandDto.getHistory().getConsumer());
        } else {
            request.setClientId(commandDto.getBody().getConsumer());
        }
        request.setTime(LocalDateTime.now(ZoneOffset.UTC));
        return request;
    }
}
