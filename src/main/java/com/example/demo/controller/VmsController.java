package com.example.demo.controller;

import com.example.demo.dto.VmsDto;
import com.example.demo.service.VmsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/vms/detail")
public class VmsController {
    @Autowired
    private VmsService vmsService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @CrossOrigin("http://localhost:5173/")
    public ResponseEntity<VmsDto> saveVmsDetails(@RequestBody VmsDto vmsDto, HttpServletRequest httpServletRequest) throws JsonProcessingException {
        String authToken = httpServletRequest.getHeader("Authorization").replace("Bearer", "");
        return vmsService.saveVmsDetails(vmsDto, authToken);
    }

    @RequestMapping(value = "/get/{msisdn}", method = RequestMethod.GET)
    @CrossOrigin("http://localhost:5173/")
    public ResponseEntity<VmsDto> getVmsDetails(@PathVariable("msisdn") String msisdn) {
        return vmsService.getVmsDetails(msisdn);
    }

    @RequestMapping(value = "/get/all", method = RequestMethod.GET)
    @CrossOrigin("http://localhost:5173/")
    public List<VmsDto> getAllVmsDetails() {
        return vmsService.getAllVmsDetails();
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @CrossOrigin("http://localhost:5173/")
    public ResponseEntity<VmsDto> updateVmsDetails(@RequestParam String msisdn, @RequestBody VmsDto vmsDto) throws JsonProcessingException {
        return vmsService.updateVmsDetails(vmsDto, msisdn);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @CrossOrigin("http://localhost:5173/")
    public String deleteVmsDetails(@RequestParam String msisdn) {
        return vmsService.deleteVmsDetails(msisdn);
    }

}
