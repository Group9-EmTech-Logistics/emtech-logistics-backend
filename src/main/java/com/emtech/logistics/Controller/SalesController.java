package com.emtech.logistics.controller;

import com.emtech.logistics.dto.SalesTransaction;
import com.emtech.logistics.service.SalesService;
import com.emtech.logistics.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/sales")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class SalesController {

    private final SalesService salesService;

    @GetMapping
    public ResponseEntity<ResponseUtil.ApiResponse<List<SalesTransaction>>> getAllSales() {
        try {
            List<SalesTransaction> transactions = salesService.getAllSalesTransactions();
            return ResponseUtil.success(transactions);
        } catch (Exception e) {
            return ResponseUtil.error("Failed to fetch sales transactions: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseUtil.ApiResponse<SalesTransaction>> getSalesTransaction(@PathVariable Long id) {
        try {
            SalesTransaction transaction = salesService.getSalesTransactionById(id);
            if (transaction == null) {
                return ResponseUtil.error("Sales transaction not found with id: " + id);
            }
            return ResponseUtil.success(transaction);
        } catch (Exception e) {
            return ResponseUtil.error("Failed to fetch sales transaction: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<ResponseUtil.ApiResponse<SalesTransaction>> addSalesTransaction(
            @Valid @RequestBody SalesTransaction transaction,
            @RequestHeader("Authorization") String token) {
        try {
            SalesTransaction newTransaction = salesService.addSalesTransaction(transaction, token);
            return ResponseUtil.success("Sales transaction created successfully", newTransaction);
        } catch (Exception e) {
            return ResponseUtil.error("Failed to create sales transaction: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseUtil.ApiResponse<SalesTransaction>> updateSalesTransaction(
            @PathVariable Long id,
            @Valid @RequestBody SalesTransaction transaction,
            @RequestHeader("Authorization") String token) {
        try {
            SalesTransaction updatedTransaction = salesService.updateSalesTransaction(id, transaction, token);
            return ResponseUtil.success("Sales transaction updated successfully", updatedTransaction);
        } catch (Exception e) {
            return ResponseUtil.error("Failed to update sales transaction: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseUtil.ApiResponse<String>> deleteSalesTransaction(
            @PathVariable Long id,
            @RequestHeader("Authorization") String token) {
        try {
            salesService.deleteSalesTransaction(id, token);
            return ResponseUtil.success("Sales transaction deleted successfully", null);
        } catch (Exception e) {
            return ResponseUtil.error("Failed to delete sales transaction: " + e.getMessage());
        }
    }
}
