package com.example.demo.controller;

import com.example.demo.dto.HssProvDto;
import com.example.demo.service.AccessLogsService;
import com.example.demo.service.HssProvService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1")
public class HssProvController {

    @Autowired
    private AccessLogsService accessLogsService;
    @Autowired
    private HssProvService hssProvService;

    @PostMapping("/save/record")
    public String saveDetails(@RequestBody HssProvDto hssProvDto, HttpServletRequest httpServletRequest) throws JsonProcessingException {
        String authCode = httpServletRequest.getHeader("Authorization").replace("Bearer", "");
        return hssProvService.saveHssProv(hssProvDto, authCode);
    }

    @DeleteMapping("/delete/record")
    public String deleteHssProvData(@RequestParam String imsi, @RequestParam String msisdn) {
        String msg = hssProvService.deleteHssProv(imsi, msisdn);
        return msg;
    }

    @PutMapping("/update/record")
    public String updateDetails(@RequestParam String imsi, @RequestParam String msisdn, @RequestBody HssProvDto hssProvDto) throws JsonProcessingException {
        String msg = hssProvService.updateHssProv(imsi, msisdn, hssProvDto);
        return msg;
    }

}
