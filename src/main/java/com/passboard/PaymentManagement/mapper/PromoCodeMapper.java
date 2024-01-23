package com.passboard.PaymentManagement.mapper;

import com.passboard.PaymentManagement.model.dto.PromoCodeDto;
import com.passboard.PaymentManagement.model.entity.PromoCode;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PromoCodeMapper {
    PromoCode toEntity(PromoCodeDto promoCodeDto);

    PromoCodeDto toDto(PromoCode promoCode);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    PromoCode partialUpdate(PromoCodeDto promoCodeDto, @MappingTarget PromoCode promoCode);
}