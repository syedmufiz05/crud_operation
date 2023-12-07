package com.example.demo.service;

import com.example.demo.dto.DeviceMgmtDto;
import com.example.demo.model.DeviceMgmt;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DeviceMgmtService {
    ResponseEntity saveDeviceMgmtDetail(DeviceMgmtDto deviceMgmtDto);

    ResponseEntity editDeviceMgmtDetail(Integer deviceId, DeviceMgmtDto deviceMgmtDto);

    ResponseEntity deleteDeviceMgmtDetail(Integer deviceId);

    List<DeviceMgmtDto> fetchAllDeviceMgmtDetail();

    List<DeviceMgmtDto> searchItems(String keyword);
}
