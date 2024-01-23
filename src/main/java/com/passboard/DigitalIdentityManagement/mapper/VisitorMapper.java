package com.passboard.DigitalIdentityManagement.mapper;

import com.passboard.DigitalIdentityManagement.model.dto.VisitorDto;
import com.passboard.DigitalIdentityManagement.model.entity.Visitor;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface VisitorMapper {
    Visitor toEntity(VisitorDto visitorDto);

    VisitorDto toDto(Visitor visitor);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Visitor partialUpdate(VisitorDto visitorDto, @MappingTarget Visitor visitor);
}