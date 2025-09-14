package com.emtech.logistics.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "inventory")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Inventory {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(unique = true, nullable = false)
    private String sku;
    
    @Enumerated(EnumType.STRING)
    private Category category;
    
    private Integer quantity;
    
    @Column(precision = 10, scale = 2)
    private BigDecimal purchasePrice;
    
    @Column(precision = 10, scale = 2)
    private BigDecimal sellingPrice;
    
    private String countryOfOrigin;
    
    private String manufacturer;
    
    private String manufacturerAddress;
    
    private String manufacturerPhone;
    
    private String manufacturerEmail;
    
    private String certifications;
    
    private LocalDate expiryDate;
    
    @Enumerated(EnumType.STRING)
    private Status status;
    
    private String description;
    
    private String barcode;
    
    private Integer minStockLevel = 10;
    
    private Integer maxStockLevel = 1000;
    
    @CreatedDate
    private LocalDateTime createdAt;
    
    @LastModifiedDate
    private LocalDateTime updatedAt;
    
    public enum Category {
        ELECTRONICS, FOOD, CLOTHING, AUTOMOTIVE, HEALTHCARE, OTHER
    }
    
    public enum Status {
        NORMAL, LOW, CRITICAL, EXPIRING, EXPIRED, OUT_OF_STOCK
    }
}