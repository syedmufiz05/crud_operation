package com.example.demo.service;

import com.example.demo.dto.DestinationRatesDto;

public interface DestinationRatesService {
    DestinationRatesDto createDestinationRates(DestinationRatesDto destinationRatesDto);
    String deleteDestinationRates(Integer destinationRatesId);
}
