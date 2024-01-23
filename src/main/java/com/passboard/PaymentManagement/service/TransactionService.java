package com.passboard.PaymentManagement.service;

import com.passboard.DigitalIdentityManagement.service.VisitorService;
import com.passboard.PaymentManagement.client.IDigitalPaymentClient;
import com.passboard.PaymentManagement.model.dto.TransactionDto;
import com.passboard.PaymentManagement.model.entity.EventTicket;
import com.passboard.PaymentManagement.model.entity.PromoCode;
import com.passboard.PaymentManagement.model.entity.Transaction;
import com.passboard.PaymentManagement.repository.EventTicketRepository;
import com.passboard.PaymentManagement.repository.PromoCodeRepository;
import com.passboard.PaymentManagement.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final PromoCodeRepository promoCodeRepository;
    private final VisitorService visitorService;
    private final IDigitalPaymentClient digitalPaymentClient;
    private final EventTicketRepository eventTicketRepository;

    public TransactionService(TransactionRepository transactionRepository, PromoCodeRepository promoCodeRepository, VisitorService visitorService, IDigitalPaymentClient digitalPaymentClient, EventTicketRepository eventTicketRepository) {
        this.transactionRepository = transactionRepository;
        this.promoCodeRepository = promoCodeRepository;
        this.visitorService = visitorService;
        this.digitalPaymentClient = digitalPaymentClient;
        this.eventTicketRepository = eventTicketRepository;
    }

    @Transactional(rollbackOn = Exception.class)
    public Transaction transact(Long userId, TransactionDto transactionDto) throws Exception {
        PromoCode promoCode = getPromoCode(userId, transactionDto);
        EventTicket eventTicket = eventTicketRepository.findEventTicketByEvent_IdAndTicket_Id(transactionDto.eventId(), transactionDto.ticketId());
        checkCapping(transactionDto.numberOfTickets(), eventTicket);
        return doPayment(userId, transactionDto.numberOfTickets(), eventTicket, promoCode);
    }

    private PromoCode getPromoCode(Long userId, TransactionDto transactionDto) {
        PromoCode promoCode = promoCodeRepository.findById(transactionDto.promoCodeId()).orElseThrow();
        if (promoCode.getExpiryDate().before(new Date()) || promoCodeRedemptions(userId, transactionDto) > promoCode.getLimitedCount())
            promoCode = null;
        return promoCode;
    }

    private Integer promoCodeRedemptions(Long userId, TransactionDto transactionDto) {
        return transactionRepository.countAllByVisitorIdAndAppliedPromoCodeId(userId, transactionDto.promoCodeId());
    }

    private Transaction doPayment(Long userId, Integer ticketsNumber, EventTicket eventTicket, PromoCode promoCode) throws Exception {
        if (!digitalPaymentClient.apply(userId, ticketsNumber, eventTicket, promoCode))
            throw new Exception("Payment Transaction Failed...");
        eventTicket.setTotalPurchasedTickets(ticketsNumber + eventTicket.getTotalPurchasedTickets());
        eventTicketRepository.save(eventTicket);
        return transactionRepository.save(new Transaction(visitorService.getVisitor(userId), new Date(), promoCode, eventTicket, ticketsNumber));
    }

    private static void checkCapping(Integer ticketsNumber, EventTicket eventTicket) throws Exception {
        if (ticketsNumber + eventTicket.getTotalPurchasedTickets() > eventTicket.getTotalAvailableTickets())
            throw new Exception("capping limit exceeded...");
    }
}
