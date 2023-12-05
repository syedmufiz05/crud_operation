package com.example.demo.controller;

import com.example.demo.dto.DeviceMgmtDto;
import com.example.demo.dto.InventoryMgmtDto;
import com.example.demo.service.DeviceMgmtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/device/mgmt/detail")
public class DeviceMgmtController {
    @Autowired
    private DeviceMgmtService deviceMgmtService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<DeviceMgmtDto> saveDeviceMgmtDetails(@RequestBody DeviceMgmtDto deviceMgmtDto) {
        return deviceMgmtService.saveDeviceMgmtDetail(deviceMgmtDto);
    }

    @RequestMapping(value = "/get/all", method = RequestMethod.GET)
    public List<DeviceMgmtDto> getAllInventoryDetails() {
        return deviceMgmtService.fetchAllDeviceMgmtDetail();
    }
}
