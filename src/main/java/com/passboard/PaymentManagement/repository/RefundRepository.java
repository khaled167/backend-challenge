package com.passboard.PaymentManagement.repository;

import com.passboard.PaymentManagement.model.entity.Refund;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RefundRepository extends JpaRepository<Refund, Long> {
    List<Refund> findAllByRefundedTransactionVisitorId(Long id);

    boolean existsByRefundedTransactionId(Long transactionId);
}