package com.passboard.PaymentManagement.controller;


import com.passboard.PaymentManagement.model.dto.TransactionDto;
import com.passboard.PaymentManagement.model.entity.Refund;
import com.passboard.PaymentManagement.model.entity.Transaction;
import com.passboard.PaymentManagement.service.InquiryService;
import com.passboard.PaymentManagement.service.RefundService;
import com.passboard.PaymentManagement.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("payments/v1")
public class PaymentController {

    private final InquiryService inquiryService;
    private final TransactionService transactionService;
    private final RefundService refundService;

    public PaymentController(InquiryService inquiryService, TransactionService transactionService, RefundService refundService) {
        this.inquiryService = inquiryService;
        this.transactionService = transactionService;
        this.refundService = refundService;
    }

    @GetMapping("/transactions/{userId}")
    public ResponseEntity<List<Transaction>> getTransactions(@PathVariable Long userId) {
        return ResponseEntity.ok(inquiryService.getTransactions(userId));
    }

    @GetMapping("/refunds/{userId}")
    private ResponseEntity<List<Refund>> getRefunds(@PathVariable Long userId) {
        return ResponseEntity.ok(inquiryService.getRefunds(userId));
    }

    @PostMapping("/transactions/{userId}")
    private ResponseEntity<Transaction> transact(@PathVariable Long userId, @RequestBody TransactionDto transactionDto) throws Exception {
        return ResponseEntity.ok(transactionService.transact(userId, transactionDto));
    }

    @PostMapping("/refunds/{userId}/{transactionId}")
    private ResponseEntity<Refund> refund(@PathVariable Long userId, @PathVariable Long transactionId, @RequestBody String reason) throws Exception {
        return ResponseEntity.ok(refundService.refund(userId, transactionId, reason));
    }
}
