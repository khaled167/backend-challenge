package com.passboard.PaymentManagement.model.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Refund {
    public Refund(Transaction refundedTransaction, String reason) {
        this.refundedTransaction = refundedTransaction;
        this.reason = reason;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn
    private Transaction refundedTransaction;

    @Column
    private String reason;
}
