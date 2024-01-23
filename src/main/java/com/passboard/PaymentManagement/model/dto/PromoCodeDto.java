package com.passboard.PaymentManagement.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link com.passboard.PaymentManagement.model.entity.PromoCode}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record PromoCodeDto(@NotNull @NotEmpty String code, @NotNull @Future Date expiryDate,
                           @Positive Integer discountPercentage,
                           @NotNull @Positive Integer limitedCount) implements Serializable {
}