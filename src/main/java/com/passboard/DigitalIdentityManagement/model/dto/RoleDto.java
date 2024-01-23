package com.passboard.DigitalIdentityManagement.model.dto;

import com.passboard.DigitalIdentityManagement.model.entity.Role;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link Role}
 */
public record RoleDto(@NotNull @NotEmpty String role) implements Serializable {
}