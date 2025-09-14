package com.emtech.logistics.service;

import com.emtech.logistics.dto.SalesDTO;
import com.emtech.logistics.dto.SalesTransaction;

import java.util.List;

public interface SalesService {
    List<SalesTransaction> getAllSalesTransactions();
    SalesTransaction getSalesTransactionById(Long id);
    SalesTransaction addSalesTransaction(SalesTransaction transaction);
    SalesDTO convertToDTO(SalesTransaction transaction);
}
