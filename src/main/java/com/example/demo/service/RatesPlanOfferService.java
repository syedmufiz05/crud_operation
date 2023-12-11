package com.example.demo.service;

import com.example.demo.dto.RatesPlanOfferDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RatesPlanOfferService {
    ResponseEntity saveRatesPlanDetail(RatesPlanOfferDto ratesPlanOfferDto);

    List<String> getAllRatesPlans();
}
