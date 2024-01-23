package com.passboard.PaymentManagement.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * DTO for {@link com.passboard.PaymentManagement.model.entity.Transaction}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record TransactionDto(Long promoCodeId, Long eventId, Long ticketId, Integer numberOfTickets) implements Serializable {
}