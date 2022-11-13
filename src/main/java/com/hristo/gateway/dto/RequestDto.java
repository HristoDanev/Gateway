package com.hristo.gateway.dto;

import lombok.Data;

@Data
public class RequestDto {
    private String requestId;
    private long timestamp;
    private String client;
    private String currency;
    private int period;
}
