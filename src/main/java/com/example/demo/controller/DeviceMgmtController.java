package com.example.demo.controller;

import com.example.demo.dto.DeviceMgmtDto;
import com.example.demo.service.DeviceMgmtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/device/mgmt/detail")
@CrossOrigin("http://localhost:5173/")
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

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ResponseEntity<List<DeviceMgmtDto>> searchInventoryDetails(@RequestParam("keyword") String keyword) {
        List<DeviceMgmtDto> deviceMgmtList = deviceMgmtService.searchItems(keyword);
        return ResponseEntity.ok(deviceMgmtList);
    }
}
