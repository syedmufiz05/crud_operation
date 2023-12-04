package com.example.demo.controller;

import com.example.demo.dto.SimMgmtDto;
import com.example.demo.service.SimMgmtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/sim/mgmt/detail")
public class SimMgmtController {
    @Autowired
    private SimMgmtService simMgmtService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<SimMgmtDto> fetchSimMgmtDetails(@RequestBody SimMgmtDto simMgmtDto) {
        return simMgmtService.saveSimMgmt(simMgmtDto);
    }

    @RequestMapping(value = "/save/all", method = RequestMethod.GET)
    public List<SimMgmtDto> getAllSimMgmtDetails() {
        return simMgmtService.getAllSimMgmt();
    }
}
