package com.emtech.logistics.controller;

import com.emtech.logistics.dto.Shipment;
import com.emtech.logistics.service.TrackingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tracking")
@CrossOrigin(origins = "*")
public class TrackingController {

    private final TrackingService trackingService;

    public TrackingController(TrackingService trackingService) {
        this.trackingService = trackingService;
    }

    /**
     * Get all shipments.
     */
    @GetMapping
    public ResponseEntity<List<Shipment>> getAllShipments() {
        return ResponseEntity.ok(trackingService.getAllShipments());
    }

    /**
     * Get a shipment by database ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Shipment> getShipmentById(@PathVariable Long id) {
        Shipment shipment = trackingService.getShipmentById(id);
        return shipment != null ? ResponseEntity.ok(shipment) : ResponseEntity.notFound().build();
    }

    /**
     * Get a shipment by its tracking ID.
     */
    @GetMapping("/tracking-id/{trackingId}")
    public ResponseEntity<Shipment> getShipmentByTrackingId(@PathVariable String trackingId) {
        Shipment shipment = trackingService.getShipmentByTrackingId(trackingId);
        return shipment != null ? ResponseEntity.ok(shipment) : ResponseEntity.notFound().build();
    }

    /**
     * Create a new shipment.
     */
    @PostMapping
    public ResponseEntity<Shipment> addShipment(@RequestBody Shipment shipment) {
        Shipment newShipment = trackingService.addShipment(shipment);
        return ResponseEntity.status(HttpStatus.CREATED).body(newShipment);
    }

    /**
     * Update shipment progress (progress + current location).
     */
    @PatchMapping("/{trackingId}/progress")
    public ResponseEntity<Shipment> updateShipmentProgress(
            @PathVariable String trackingId,
            @RequestParam(required = false, defaultValue = "0") Integer progress,
            @RequestParam(required = false, defaultValue = "Unknown") String currentLocation) {
        Shipment updatedShipment = trackingService.updateShipmentProgress(trackingId, progress, currentLocation);
        return updatedShipment != null ? ResponseEntity.ok(updatedShipment) : ResponseEntity.notFound().build();
    }

    /**
     * Update shipment status (e.g., Pending → In Transit → Delivered).
     */
    @PatchMapping("/{trackingId}/status")
    public ResponseEntity<Shipment> updateShipmentStatus(
            @PathVariable String trackingId,
            @RequestParam String status) {
        Shipment updatedShipment = trackingService.updateShipmentStatus(trackingId, status);
        return updatedShipment != null ? ResponseEntity.ok(updatedShipment) : ResponseEntity.notFound().build();
    }

    /**
     * Delete a shipment by ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShipment(@PathVariable Long id) {
        return trackingService.deleteShipment(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
