package com.hristo.gateway.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@XmlRootElement
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDto {
    private String currency;
    private BigDecimal exchangeRate;
    private LocalDateTime dateTime;

    private String message;
}
