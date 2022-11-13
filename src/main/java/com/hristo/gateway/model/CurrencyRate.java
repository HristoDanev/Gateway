package com.hristo.gateway.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@ToString
@Table(name = "currency_exchange_rates")
public class CurrencyRate {
    @Id
    @GeneratedValue
    private long id;

    @Column(length = 3)
    private String code;

    @Column(name = "exchange_rate", precision = 19, scale = 6)
    private BigDecimal rate;

    @Column(name = "date")
    private LocalDateTime dateTime;
}
