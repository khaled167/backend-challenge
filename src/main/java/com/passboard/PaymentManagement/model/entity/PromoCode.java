package com.passboard.PaymentManagement.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
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
public class PromoCode {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @NotNull
    @NotEmpty
    private String code;

    @Column
    @NotNull
    @Future
    private Date expiryDate;

    @Column
    @Positive
    private Integer discountPercentage;

    @Column
    @Positive
    @NotNull
    private Integer limitedCount; // limited count per user
}
