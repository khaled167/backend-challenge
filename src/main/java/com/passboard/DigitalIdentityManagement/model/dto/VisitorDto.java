package com.passboard.DigitalIdentityManagement.model.dto;

import com.passboard.DigitalIdentityManagement.model.entity.Visitor;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.LuhnCheck;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * DTO for {@link Visitor}
 */
public record VisitorDto(@NotNull @NotEmpty String username, @NotNull @NotEmpty String password,
                         @NotEmpty String firstName, @NotEmpty String lastName, @NotNull @NotEmpty String nationalId,
                         @NotNull @NotEmpty String nationality, @NotEmpty String city, @NotEmpty String address,
                         @Email @NotEmpty String emailAddress, @NotEmpty String phoneNumber,
                         @NotNull @NotEmpty String gender, Short age, @NotNull @NotEmpty @LuhnCheck String cardNumber,
                         @NotNull @NotEmpty String cardCVV, @NotNull Set<RoleDto> roles,
                         boolean active, Date birthDay) implements Serializable {
}