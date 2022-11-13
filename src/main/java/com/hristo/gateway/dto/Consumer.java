package com.hristo.gateway.dto;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Consumer implements Serializable {
    @XmlAttribute
    private String consumer;
    @XmlElement
    private String currency;
}