package com.hristo.gateway.dto;

import lombok.Data;

import javax.xml.bind.annotation.*;

@Data
@XmlRootElement(name = "command")
@XmlAccessorType(XmlAccessType.FIELD)
public class CommandDto {

    @XmlAttribute
    private String id;
    @XmlElement(name = "get")
    private Consumer body;
    @XmlElement(name = "history")
    private History history;
}

