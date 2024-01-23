package com.passboard.PaymentManagement.model.entity;

import com.passboard.DigitalIdentityManagement.model.entity.Visitor;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.util.Date;

@Entity
@Table
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Transaction {

    public Transaction(Visitor visitor, Date date, PromoCode appliedPromoCode, EventTicket eventTicket, Integer ticketsNumber) {
        this.visitor = visitor;
        this.date = date;
        this.appliedPromoCode = appliedPromoCode;
        this.eventTicket = eventTicket;
        this.ticketsNumber = ticketsNumber;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn
    private Visitor visitor;

    @Column
    @NotNull
    private Date date;

    @ManyToOne
    @JoinColumn
    private PromoCode appliedPromoCode;

    @ManyToOne
    @JoinColumn
    private EventTicket eventTicket;

    @Column
    @Positive
    private Integer ticketsNumber;

}
