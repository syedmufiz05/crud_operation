package com.example.demo.service;

import com.example.demo.dto.DestinationRatesDto;

import java.util.List;

public interface DestinationRatesService {
    DestinationRatesDto createDestinationRates(DestinationRatesDto destinationRatesDto);

    List<DestinationRatesDto> getAllDestinationRates();

    String deleteDestinationRates(Integer destinationRatesId);
}
