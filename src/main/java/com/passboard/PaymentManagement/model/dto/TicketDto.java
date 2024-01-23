package com.passboard.PaymentManagement.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.passboard.PaymentManagement.model.entity.TicketType;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link com.passboard.PaymentManagement.model.entity.Ticket}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record TicketDto(@NotNull TicketType type) implements Serializable {
}