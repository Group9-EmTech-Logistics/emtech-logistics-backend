package com.emtech.logistics.service;

import com.emtech.logistics.dto.Shipment;

import java.util.List;

public interface TrackingService {
    List<Shipment> getAllShipments();
    Shipment getShipmentById(Long id);
    Shipment getShipmentByTrackingId(String trackingId);
    Shipment addShipment(Shipment shipment);
    Shipment updateShipmentProgress(String trackingId, Integer progress, String currentLocation);
    Shipment updateShipmentStatus(String trackingId, String status);
    boolean deleteShipment(Long id);
}
