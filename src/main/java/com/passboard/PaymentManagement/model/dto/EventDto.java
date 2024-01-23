package com.passboard.PaymentManagement.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.passboard.PaymentManagement.model.entity.Event;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * DTO for {@link Event}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class EventDto implements Serializable {
    @NotNull
    private String htmlViewer;
    @NotNull
    @FutureOrPresent
    private Date startDate;
    @NotNull
    @Future
    private Date endDate;
    List<String> categories;
}