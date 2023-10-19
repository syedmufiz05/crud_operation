package com.example.demo.service;

import com.example.demo.dto.AucDto;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface AucService {
    String saveAucDetails(AucDto aucDto, String authToken) throws JsonProcessingException;

    String updateAucDetails(String imsi, AucDto aucDto) throws JsonProcessingException;

    void deleteAucDetails(String imsi);
}
