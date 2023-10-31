package com.example.demo.service;

import com.example.demo.dto.RatesDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;

public interface RatesService {
    RatesDto addRates(RatesDto ratesDto, String authToken) throws JsonProcessingException;

    ResponseEntity editRates(Integer ratesId, RatesDto ratesDto) throws JsonProcessingException;

    String deleteRates(Integer ratesId);
}
