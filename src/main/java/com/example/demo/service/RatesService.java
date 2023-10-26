package com.example.demo.service;

import com.example.demo.dto.RatesDto;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface RatesService {
    String addRates(RatesDto ratesDto, String authToken) throws JsonProcessingException;

    String editRates(Integer ratesId, RatesDto ratesDto) throws JsonProcessingException;

    String deleteRates(Integer ratesId);
}
