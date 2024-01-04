package com.example.demo.service;

import com.example.demo.dto.CallSessionUsageDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CallSessionUsageService {
    ResponseEntity saveCallSessionUsage(CallSessionUsageDto callSessionUsageDto);

    List<CallSessionUsageDto> getAllCallSessionUsage();
}
