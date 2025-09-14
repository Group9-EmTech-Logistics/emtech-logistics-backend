package com.emtech.logistics.service.impl;

import com.emtech.logistics.dto.SalesDTO;
import com.emtech.logistics.dto.SalesTransaction;
import com.emtech.logistics.entity.SalesTransactionEntity;
import com.emtech.logistics.repository.SalesRepository;
import com.emtech.logistics.service.SalesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SalesServiceImpl implements SalesService {

    private final SalesRepository salesRepository;

    @Override
    public List<SalesTransaction> getAllSalesTransactions() {
        return salesRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SalesTransaction getSalesTransactionById(Long id) {
        return salesRepository.findById(id).map(this::toDTO).orElse(null);
    }

    @Override
    public SalesTransaction addSalesTransaction(SalesTransaction transaction) {
        SalesTransactionEntity entity = toEntity(transaction);
        SalesTransactionEntity saved = salesRepository.save(entity);
        return toDTO(saved);
    }

    @Override
    public SalesDTO convertToDTO(SalesTransaction transaction) {
        return SalesDTO.builder()
                .id(transaction.getId())
                .transactionId(transaction.getTransactionId())
                .customerId(transaction.getCustomerId())
                .customerName(transaction.getCustomerName())
                .totalAmount(transaction.getTotalAmount())
                .saleType(transaction.getTransactionType())
                .saleDate(transaction.getTransactionDate())
                .build();
    }

    private SalesTransaction toDTO(SalesTransactionEntity entity) {
        return new SalesTransaction(
                entity.getId(),
                entity.getTransactionId(),
                entity.getCustomerId(),
                entity.getCustomerName(),
                entity.getItems(),
                entity.getTotalAmount(),
                entity.getTransactionType(),
                entity.getTransactionDate()
        );
    }

    private SalesTransactionEntity toEntity(SalesTransaction dto) {
        SalesTransactionEntity entity = new SalesTransactionEntity();
        entity.setId(dto.getId());
        entity.setTransactionId(dto.getTransactionId());
        entity.setCustomerId(dto.getCustomerId());
        entity.setCustomerName(dto.getCustomerName());
        entity.setItems(dto.getItems());
        entity.setTotalAmount(dto.getTotalAmount());
        entity.setTransactionType(dto.getTransactionType());
        entity.setTransactionDate(dto.getTransactionDate());
        return entity;
    }
}
