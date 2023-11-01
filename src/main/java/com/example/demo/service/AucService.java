package com.example.demo.service;

import com.example.demo.dto.AucDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;

public interface AucService {
    ResponseEntity saveAucDetails(AucDto aucDto, String authToken) throws JsonProcessingException;

    ResponseEntity updateAucDetails(String imsi, AucDto aucDto) throws JsonProcessingException;

    void deleteAucDetails(String imsi);
}
