package com.passboard.PaymentManagement.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import lombok.experimental.Accessors;

@Entity
@Table
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Accessors(chain = true)
public class EventTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @NotNull
    @Positive
    private Integer totalAvailableTickets;

    @Column
    @PositiveOrZero
    private Integer totalPurchasedTickets = 0; // Can be calculated/deducted from transactions - refunds but set here for optimization

    @Column
    @NotNull
    @Positive
    private Integer price;

    @ManyToOne
    @JoinColumn
    @NotNull
    private Event event;

    @ManyToOne
    @JoinColumn
    @NotNull
    private Ticket ticket;
}
