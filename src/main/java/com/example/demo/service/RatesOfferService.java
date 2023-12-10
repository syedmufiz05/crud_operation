package com.example.demo.service;

import com.example.demo.dto.RatesOfferDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RatesOfferService {
    public ResponseEntity saveRatesOffer(RatesOfferDto ratesOfferDto);

    public List<String> getAllRatesOffer();
}
