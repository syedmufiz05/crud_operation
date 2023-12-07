package com.example.demo.controller;

import com.example.demo.dto.MsisdnMgmtDto;
import com.example.demo.service.MsisdnMgmtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/msisdn/mgmt/detail")
@CrossOrigin("http://localhost:5173/")
public class MsisdnMgmtController {
    @Autowired
    private MsisdnMgmtService msisdnMgmtService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<MsisdnMgmtDto> fetchMsisdnDetail(@RequestBody MsisdnMgmtDto msisdnMgmtDto) {
        return msisdnMgmtService.saveMsisdnMgmt(msisdnMgmtDto);
    }

    @RequestMapping(value = "/get/all", method = RequestMethod.GET)
    public List<MsisdnMgmtDto> getAllMsisdnDetails() {
        return msisdnMgmtService.getAllMsisdnDetail();
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<MsisdnMgmtDto> searchRecord(@RequestParam("keyword") String keyword) {
        return msisdnMgmtService.searchRecord(keyword);
    }
}
