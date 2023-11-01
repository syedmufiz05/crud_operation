package com.example.demo.service;

import com.example.demo.dto.VmsDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;

public interface VmsService {
    ResponseEntity saveVmsDetails(VmsDto vmsDto, String authToken) throws JsonProcessingException;

    ResponseEntity updateVmsDetails(VmsDto vmsDto, String msisdn) throws JsonProcessingException;

    String deleteVmsDetails(String msisdn);

}
