package com.example.demo.service;

import com.example.demo.dto.HssProvDto;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface HssProvService {
    String saveHssProv(HssProvDto hssProvDto, String authToken) throws JsonProcessingException;

    String updateHssProv(String imsi, String msisdn, HssProvDto hssProvDto) throws JsonProcessingException;

    String deleteHssProv(String imsi, String msisdn);
}
