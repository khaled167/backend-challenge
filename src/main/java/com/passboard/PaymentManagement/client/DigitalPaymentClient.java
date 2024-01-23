package com.passboard.PaymentManagement.client;


import com.passboard.PaymentManagement.model.entity.EventTicket;
import com.passboard.PaymentManagement.model.entity.PromoCode;
import org.springframework.stereotype.Component;

@Component
public class DigitalPaymentClient implements IDigitalPaymentClient {
    /*
      This is a mock client for digital payment with debit/credit cards
      */
    @Override
    public boolean apply(Long visitorId, Integer deductionAmount, EventTicket eventTicket, PromoCode promoCode) {
        return true;
    }

    @Override
    public boolean refund(Long visitorId, Integer adductionAmount) {
        return true;
    }
}
