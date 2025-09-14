package com.emtech.logistics.repository;

import com.emtech.logistics.model.SalesTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SalesRepository extends JpaRepository<SalesTransactionEntity, Long> {

    /**
     * Today's total revenue (between start and end of current day).
     */
    @Query("SELECT COALESCE(SUM(s.totalAmount), 0) FROM SalesTransactionEntity s " +
           "WHERE s.transactionDate BETWEEN :startOfDay AND :endOfDay")
    BigDecimal getTodaysRevenue(@Param("startOfDay") LocalDateTime startOfDay,
                                @Param("endOfDay") LocalDateTime endOfDay);

    /**
     * Count today's transactions.
     */
    @Query("SELECT COUNT(s) FROM SalesTransactionEntity s " +
           "WHERE s.transactionDate BETWEEN :startOfDay AND :endOfDay")
    Long countTodaysTransactions(@Param("startOfDay") LocalDateTime startOfDay,
                                 @Param("endOfDay") LocalDateTime endOfDay);

    /**
     * Total revenue (all time).
     */
    @Query("SELECT COALESCE(SUM(s.totalAmount), 0) FROM SalesTransactionEntity s")
    BigDecimal getTotalRevenue();

    /**
     * Count distinct active customers since given date.
     */
    @Query("SELECT COUNT(DISTINCT s.customer.id) FROM SalesTransactionEntity s " +
           "WHERE s.transactionDate >= :since")
    Long countActiveCustomersSince(@Param("since") LocalDateTime since);

    /**
     * Default: active customers in last 30 days.
     */
    default Long countActiveCustomers() {
        return countActiveCustomersSince(LocalDateTime.now().minusDays(30));
    }

    /**
     * Weekly revenue report.
     */
    @Query("SELECT COALESCE(SUM(s.totalAmount), 0) FROM SalesTransactionEntity s " +
           "WHERE s.transactionDate >= :startOfWeek AND s.transactionDate <= :endOfWeek")
    BigDecimal getWeeklyRevenue(@Param("startOfWeek") LocalDateTime startOfWeek,
                                @Param("endOfWeek") LocalDateTime endOfWeek);

    /**
     * Top customers by total spending.
     */
    @Query("SELECT s.customer.id, SUM(s.totalAmount) as totalSpent " +
           "FROM SalesTransactionEntity s GROUP BY s.customer.id " +
           "ORDER BY totalSpent DESC")
    List<Object[]> findTopCustomers();
}
