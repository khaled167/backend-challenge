package com.passboard.PaymentManagement.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * DTO for {@link com.passboard.PaymentManagement.model.entity.EventTicket}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class EventTicketDto implements Serializable {
    @NotNull
    @Positive
    private Integer totalAvailableTickets;
    @PositiveOrZero
    private Integer totalPurchasedTickets = 0;
    @NotNull
    @Positive
    private Integer price;
    private Long eventId;
    private Long ticketId;
}