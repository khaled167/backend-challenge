package com.passboard.PaymentManagement.client;


import com.passboard.PaymentManagement.model.entity.EventTicket;
import com.passboard.PaymentManagement.model.entity.PromoCode;
import org.springframework.stereotype.Component;

@Component
public interface IDigitalPaymentClient {
    boolean apply(Long visitorId, Integer deductionAmount, EventTicket eventTicket, PromoCode promoCode);

    boolean refund(Long visitorId, Integer adductionAmount);
}
