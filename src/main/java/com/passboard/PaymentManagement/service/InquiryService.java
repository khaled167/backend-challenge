package com.passboard.PaymentManagement.service;

import com.passboard.PaymentManagement.model.entity.Refund;
import com.passboard.PaymentManagement.model.entity.Transaction;
import com.passboard.PaymentManagement.repository.RefundRepository;
import com.passboard.PaymentManagement.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class InquiryService {

    private final TransactionRepository transactionRepository;

    private final RefundRepository refundRepository;

    public InquiryService(TransactionRepository transactionRepository, RefundRepository refundRepository) {
        this.transactionRepository = transactionRepository;
        this.refundRepository = refundRepository;
    }

    public List<Refund> getRefunds(Long userId) {
        return refundRepository.findAllByRefundedTransactionVisitorId(userId);
    }

    public List<Transaction> getTransactions(Long userId) {
        return transactionRepository.findAllByVisitorId(userId);
    }
}
