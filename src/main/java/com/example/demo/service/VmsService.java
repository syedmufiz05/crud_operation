package com.example.demo.service;

import com.example.demo.dto.VmsDto;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface VmsService {
    String saveVmsDetails(VmsDto vmsDto, String authToken) throws JsonProcessingException;

    String updateVmsDetails(VmsDto vmsDto,String msisdn) throws JsonProcessingException;

    void deleteVmsDetails(String msisdn);

}
