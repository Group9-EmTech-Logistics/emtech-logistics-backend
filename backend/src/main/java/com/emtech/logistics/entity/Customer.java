package com.emtech.logistics.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Customer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Enumerated(EnumType.STRING)
    private CustomerType type;
    
    @Column(nullable = false)
    private String phone;
    
    private String email;
    
    private String address;
    
    private String businessRegistrationNumber;
    
    private String taxId;
    
    private Integer totalOrders = 0;
    
    @Column(precision = 12, scale = 2)
    private BigDecimal totalSpent = BigDecimal.ZERO;
    
    private LocalDateTime lastOrderDate;
    
    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;
    
    private String notes;
    
    @CreatedDate
    private LocalDateTime createdAt;
    
    @LastModifiedDate
    private LocalDateTime updatedAt;
    
    public enum CustomerType {
        WHOLESALE, RETAIL, VIP
    }
    
    public enum Status {
        ACTIVE, INACTIVE, SUSPENDED
    }
}