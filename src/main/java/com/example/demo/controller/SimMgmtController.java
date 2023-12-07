package com.example.demo.controller;

import com.example.demo.dto.SimMgmtDto;
import com.example.demo.service.SimMgmtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sim/mgmt/detail")
@CrossOrigin("http://localhost:5173/")
public class SimMgmtController {
    @Autowired
    private SimMgmtService simMgmtService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<SimMgmtDto> fetchSimMgmtDetails(@RequestBody SimMgmtDto simMgmtDto) {
        return simMgmtService.saveSimMgmt(simMgmtDto);
    }

    @RequestMapping(value = "/get/all", method = RequestMethod.GET)
    public List<SimMgmtDto> getAllSimMgmtDetails() {
        return simMgmtService.getAllSimMgmt();
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<SimMgmtDto> getSearchRecords(@RequestParam("keyword") String keyword) {
        return simMgmtService.searchRecord(keyword);
    }
}
