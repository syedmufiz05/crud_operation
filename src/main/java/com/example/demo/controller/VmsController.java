package com.example.demo.controller;

import com.example.demo.dto.VmsDto;
import com.example.demo.service.VmsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v3")
public class VmsController {
    @Autowired
    private VmsService vmsService;

    @RequestMapping(value = "/save/vms/detail", method = RequestMethod.POST)
    public ResponseEntity<VmsDto> saveVmsDetails(@RequestBody VmsDto vmsDto, HttpServletRequest httpServletRequest) throws JsonProcessingException {
        String authToken = httpServletRequest.getHeader("Authorization").replace("Bearer", "");
        return vmsService.saveVmsDetails(vmsDto, authToken);
    }

    @RequestMapping(value = "/update/vms/detail", method = RequestMethod.PUT)
    public ResponseEntity<VmsDto> updateVmsDetails(@RequestParam String msisdn, @RequestBody VmsDto vmsDto) throws JsonProcessingException {
        return vmsService.updateVmsDetails(vmsDto, msisdn);
    }

    @RequestMapping(value = "/delete/vms/detail", method = RequestMethod.DELETE)
    public String deleteVmsDetails(@RequestParam String msisdn) {
        return vmsService.deleteVmsDetails(msisdn);
    }

}
