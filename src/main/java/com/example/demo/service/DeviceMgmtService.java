package com.example.demo.service;

import com.example.demo.dto.DeviceMgmtDtoNew;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DeviceMgmtService {
    ResponseEntity saveDeviceMgmtDetail(DeviceMgmtDtoNew deviceMgmtDto);

    ResponseEntity editDeviceMgmtDetail(Integer deviceId, DeviceMgmtDtoNew deviceMgmtDto);

    ResponseEntity deleteDeviceMgmtDetail(Integer deviceId);

    List<DeviceMgmtDtoNew> fetchAllDeviceMgmtDetail();

    List<DeviceMgmtDtoNew> searchItems(String keyword);
}
