package com.pavan.samplecrud.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class APIAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(nullable = false)
    private String clientIp;
    @Column(nullable = false)
    private LocalDateTime requestReceivedOn;
    private String requestBody;

}
