package com.passboard.PaymentManagement.service;


import com.passboard.PaymentManagement.client.IDigitalPaymentClient;
import com.passboard.PaymentManagement.model.entity.Refund;
import com.passboard.PaymentManagement.model.entity.Transaction;
import com.passboard.PaymentManagement.repository.EventTicketRepository;
import com.passboard.PaymentManagement.repository.RefundRepository;
import com.passboard.PaymentManagement.repository.TransactionRepository;
import org.springframework.stereotype.Service;

@Service
public class RefundService {

    private final TransactionRepository transactionRepository;
    private final IDigitalPaymentClient digitalPaymentClient;
    private final RefundRepository refundRepository;
    private final EventTicketRepository eventTicketRepository;

    public RefundService(TransactionRepository transactionRepository, IDigitalPaymentClient digitalPaymentClient, RefundRepository refundRepository, EventTicketRepository eventTicketRepository) {
        this.transactionRepository = transactionRepository;
        this.digitalPaymentClient = digitalPaymentClient;
        this.refundRepository = refundRepository;
        this.eventTicketRepository = eventTicketRepository;
    }

    public Refund refund(Long userId, Long transactionId, String reason) throws Exception {
        if (refundRepository.existsByRefundedTransactionId(transactionId))
            throw new Exception("Transaction has been already refunded");
        Transaction transaction = transactionRepository.findById(transactionId).orElseThrow();
        if (!digitalPaymentClient.refund(userId, transaction.getTicketsNumber()))
            throw new Exception("Payment refund fails...");
        eventTicketRepository.save(transaction.getEventTicket().setTotalPurchasedTickets(transaction.getEventTicket().getTotalPurchasedTickets() - transaction.getTicketsNumber()));
        return refundRepository.save(new Refund(transaction, reason));
    }
}
