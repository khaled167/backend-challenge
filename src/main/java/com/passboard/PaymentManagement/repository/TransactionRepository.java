package com.passboard.PaymentManagement.repository;

import com.passboard.PaymentManagement.model.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findAllByVisitorId(Long id);
    
    Integer countAllByVisitorIdAndAppliedPromoCodeId(Long visitorId, Long appliedPromoCodeId);

}