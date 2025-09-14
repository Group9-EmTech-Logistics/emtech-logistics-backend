package com.emtech.logistics.service.impl;

import com.emtech.logistics.dto.Shipment;
import com.emtech.logistics.entity.ShipmentEntity;
import com.emtech.logistics.repository.ShipmentRepository;
import com.emtech.logistics.service.TrackingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TrackingServiceImpl implements TrackingService {

    private final ShipmentRepository shipmentRepository;

    @Override
    public List<Shipment> getAllShipments() {
        return shipmentRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Shipment getShipmentById(Long id) {
        return shipmentRepository.findById(id).map(this::toDTO).orElse(null);
    }

    @Override
    public Shipment getShipmentByTrackingId(String trackingId) {
        return shipmentRepository.findByTrackingId(trackingId).map(this::toDTO).orElse(null);
    }

    @Override
    public Shipment addShipment(Shipment shipment) {
        ShipmentEntity entity = toEntity(shipment);
        ShipmentEntity saved = shipmentRepository.save(entity);
        return toDTO(saved);
    }

    @Override
    public Shipment updateShipmentProgress(String trackingId, Integer progress, String currentLocation) {
        ShipmentEntity entity = shipmentRepository.findByTrackingId(trackingId).orElse(null);
        if (entity == null) return null;
        entity.setProgress(progress);
        entity.setCurrentLocation(currentLocation);
        return toDTO(shipmentRepository.save(entity));
    }

    @Override
    public Shipment updateShipmentStatus(String trackingId, String status) {
        ShipmentEntity entity = shipmentRepository.findByTrackingId(trackingId).orElse(null);
        if (entity == null) return null;
        entity.setStatus(status);
        return toDTO(shipmentRepository.save(entity));
    }

    @Override
    public boolean deleteShipment(Long id) {
        if (!shipmentRepository.existsById(id)) return false;
        shipmentRepository.deleteById(id);
        return true;
    }

    private Shipment toDTO(ShipmentEntity entity) {
        return new Shipment(
                entity.getId(),
                entity.getTrackingId(),
                entity.getOrigin(),
                entity.getDestination(),
                entity.getStatus(),
                entity.getEta(),
                entity.getProgress(),
                entity.getCurrentLocation()
        );
    }

    private ShipmentEntity toEntity(Shipment dto) {
        ShipmentEntity entity = new ShipmentEntity();
        entity.setId(dto.getId());
        entity.setTrackingId(dto.getTrackingId());
        entity.setOrigin(dto.getOrigin());
        entity.setDestination(dto.getDestination());
        entity.setStatus(dto.getStatus());
        entity.setEta(dto.getEta());
        entity.setProgress(dto.getProgress());
        entity.setCurrentLocation(dto.getCurrentLocation());
        return entity;
    }
}
