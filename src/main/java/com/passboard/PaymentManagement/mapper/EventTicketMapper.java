package com.passboard.PaymentManagement.mapper;

import com.passboard.PaymentManagement.model.dto.EventTicketDto;
import com.passboard.PaymentManagement.model.entity.EventTicket;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface EventTicketMapper {
    EventTicket toEntity(EventTicketDto eventTicketDto);

    EventTicketDto toDto(EventTicket eventTicket);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    EventTicket partialUpdate(EventTicketDto eventTicketDto, @MappingTarget EventTicket eventTicket);
}