package com.hristo.gateway.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@ToString
@NoArgsConstructor
@Table(name = "requests")
public class Request implements Serializable {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "request_id")
    private String requestId;

    @Column(name = "ext_service_name")
    private String name;

    @Column(name = "client")
    private String clientId;

    @Column(name = "time")
    private LocalDateTime time;
}
