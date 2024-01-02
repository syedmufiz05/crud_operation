package com.example.demo.controller;

import com.example.demo.dto.DataSessionUsageDto;
import com.example.demo.service.DataSessionUsageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/data/session/usage")
public class DataSessionUsageController {
    @Autowired
    private DataSessionUsageService dataSessionUsageService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<DataSessionUsageDto> saveDataSessionUsage(@RequestBody DataSessionUsageDto dataSessionUsageDto) {
        return dataSessionUsageService.saveDataSessionUsage(dataSessionUsageDto);
    }

    @RequestMapping(value = "/get/all", method = RequestMethod.GET)
    public List<DataSessionUsageDto> getAllDataSessionUsage() {
        return dataSessionUsageService.getAllDataSessionUsage();
    }
}
