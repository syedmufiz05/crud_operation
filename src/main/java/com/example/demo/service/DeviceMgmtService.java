package com.example.demo.service;

import com.example.demo.dto.DeviceMgmtDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DeviceMgmtService {
    ResponseEntity saveDeviceMgmtDetail(DeviceMgmtDto deviceMgmtDto);

    List<DeviceMgmtDto> fetchAllDeviceMgmtDetail();
}
