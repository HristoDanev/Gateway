package com.hristo.gateway.service;


import com.hristo.gateway.configuration.RabbitMqConfig;
import com.hristo.gateway.model.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProduceCurrencyRequestStatistics {
    @Autowired
    private AmqpTemplate rabbitTemplate;
    @Autowired
    private Exchange exchange;
    private static final Logger logger = LoggerFactory.getLogger(ProduceCurrencyRequestStatistics.class);

    public void send(Request request) {
        rabbitTemplate.convertAndSend(exchange.getName(), RabbitMqConfig.BINDING, request);
        logger.info("Sending Message to the Queue : " + request.toString());
    }
}
