package com.example.demo.service;

import com.example.demo.dto.HssProvDto;
import com.example.demo.dto.HssProvDtoNew;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface HssProvService {
    ResponseEntity saveHssProv(HssProvDto hssProvDto, String authToken) throws JsonProcessingException;

    ResponseEntity saveHssProvNew(HssProvDtoNew hssProvDtoNew, String authToken);

    List<HssProvDto> getAllHssProvRecord();

    ResponseEntity getHssProv(String imsi, String msisdn);

    ResponseEntity updateHssProv(String imsi, String msisdn, HssProvDto hssProvDto) throws JsonProcessingException;

    String deleteHssProv(String imsi, String msisdn);
}
