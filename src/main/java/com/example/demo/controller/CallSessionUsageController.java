package com.example.demo.controller;

import com.example.demo.dto.CallSessionUsageDto;
import com.example.demo.service.CallSessionUsageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/call/session/usage")
public class CallSessionUsageController {
    @Autowired
    private CallSessionUsageService callSessionUsageService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<CallSessionUsageDto> saveCallSession(@RequestBody CallSessionUsageDto callSessionUsageDto) {
        return callSessionUsageService.saveCallSessionUsage(callSessionUsageDto);
    }

    @RequestMapping(value = "/get/all", method = RequestMethod.GET)
    public List<CallSessionUsageDto> getAllCallSession() {
        return callSessionUsageService.getAllCallSessionUsage();
    }
}
