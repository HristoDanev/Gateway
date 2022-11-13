package com.hristo.gateway.dto;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import java.io.Serializable;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class History implements Serializable {
    @XmlAttribute
    private String consumer;
    @XmlAttribute
    private String currency;
    @XmlAttribute
    private int period;
}