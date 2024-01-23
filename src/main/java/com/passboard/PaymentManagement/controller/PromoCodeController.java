package com.passboard.PaymentManagement.controller;


import com.passboard.PaymentManagement.mapper.PromoCodeMapper;
import com.passboard.PaymentManagement.model.dto.PromoCodeDto;
import com.passboard.PaymentManagement.model.entity.PromoCode;
import com.passboard.PaymentManagement.repository.PromoCodeRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("promoCodes/v1")
public class PromoCodeController {
    private final PromoCodeRepository promoCodeRepository;
    private final PromoCodeMapper promoCodeMapper;

    public PromoCodeController(PromoCodeRepository promoCodeRepository, PromoCodeMapper promoCodeMapper) {
        this.promoCodeRepository = promoCodeRepository;
        this.promoCodeMapper = promoCodeMapper;
    }

    @PostMapping
    public PromoCode addPromoCode(@RequestBody PromoCodeDto promoCodeDto) {
        return promoCodeRepository.save(promoCodeMapper.toEntity(promoCodeDto));
    }
}
