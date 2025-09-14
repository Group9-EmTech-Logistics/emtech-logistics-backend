package com.emtech.logistics.repository;

import com.emtech.logistics.model.ShipmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ShipmentRepository extends JpaRepository<ShipmentEntity, Long> {

    /**
     * Find a shipment by its tracking ID.
     */
    Optional<ShipmentEntity> findByTrackingId(String trackingId);

    /**
     * Count shipments by status (e.g., Pending, In Transit, Delivered).
     */
    Long countByStatus(String status);

    /**
     * Find all shipments for a given customer.
     */
    @Query("SELECT s FROM ShipmentEntity s WHERE s.customer.id = :customerId")
    List<ShipmentEntity> findByCustomerId(Long customerId);

    /**
     * Count shipments created today.
     */
    @Query("SELECT COUNT(s) FROM ShipmentEntity s " +
           "WHERE s.createdAt BETWEEN :startOfDay AND :endOfDay")
    Long countTodaysShipments(LocalDateTime startOfDay, LocalDateTime endOfDay);

    /**
     * Find all shipments with a given status.
     */
    List<ShipmentEntity> findAllByStatus(String status);

    /**
     * Find overdue shipments (deliveryDate < now and not delivered).
     */
    @Query("SELECT s FROM ShipmentEntity s " +
           "WHERE s.deliveryDate < :now AND s.status <> 'Delivered'")
    List<ShipmentEntity> findOverdueShipments(LocalDateTime now);
}

