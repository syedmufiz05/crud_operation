package com.example.demo.service;

import com.example.demo.dto.DestinationDto;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface DestinationService {
    String addDestination(DestinationDto destinationDto, String authToken) throws JsonProcessingException;

    String editDestination(Integer destinationId, DestinationDto destinationDto) throws JsonProcessingException;

    String deleteDestination(Integer destinationId);
}
