package com.example.demo.controller;

import com.example.demo.dto.HssProvDto;
import com.example.demo.model.HssProv;
import com.example.demo.service.AccessLogsService;
import com.example.demo.service.HssProvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class TaskController {
    @Autowired
    private AccessLogsService accessLogsService;
    @Autowired
    private HssProvService hssProvService;

    @PostMapping("/api/v1/save/record")
    public String saveDetails(@RequestBody HssProv hssProv, HttpServletRequest httpServletRequest) {
        String authCode = httpServletRequest.getHeader("Authorization").replace("Bearer", "");
        return hssProvService.saveHssProv(hssProv, authCode);
    }

    @DeleteMapping("/api/v1/delete/record")
    public String deleteHssProvData(@RequestParam String imsi, @RequestParam String msisdn) {
        String msg =  hssProvService.deleteHssProv(imsi, msisdn);
        return msg;
    }

    @PutMapping("/api/v1/update/record")
    public String updateDetails(@RequestParam String imsi, @RequestParam String msisdn, @RequestBody HssProvDto hssProvDto) {
        String msg = hssProvService.updateHssProv(imsi, msisdn, hssProvDto);
        return msg;
    }
}
