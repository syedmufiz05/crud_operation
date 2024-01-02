package com.example.demo.service;

import com.example.demo.dto.DataSessionUsageDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


public interface DataSessionUsageService {
    ResponseEntity saveDataSessionUsage(DataSessionUsageDto dataSessionUsageDto);

    List<DataSessionUsageDto> getAllDataSessionUsage();
}
