package com.emtech.logistics.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Shipment {
    private Long id;
    private String trackingId;
    private String origin;
    private String destination;
    private String status;
    private LocalDateTime eta;
    private Integer progress;
    private String currentLocation;
}
