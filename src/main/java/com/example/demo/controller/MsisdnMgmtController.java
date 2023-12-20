package com.example.demo.controller;

import com.example.demo.dto.MsisdnMgmtDto;
import com.example.demo.dto.MsisdnMgmtDtoNew;
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
    public ResponseEntity<MsisdnMgmtDtoNew> saveMsisdnDetail(@RequestBody MsisdnMgmtDtoNew msisdnMgmtDto) {
        return msisdnMgmtService.saveMsisdnMgmt(msisdnMgmtDto);
    }

    @RequestMapping(value = "/edit/{msisdn_id}", method = RequestMethod.PUT)
    public ResponseEntity<MsisdnMgmtDtoNew> editMsisdnDetail(@PathVariable("msisdn_id") Integer msisdnId, @RequestBody MsisdnMgmtDtoNew msisdnMgmtDto) {
        return msisdnMgmtService.editMsisdnMgmt(msisdnId, msisdnMgmtDto);
    }

    @RequestMapping(value = "/delete/{msisdn_id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteMsisdnDetail(@PathVariable("msisdn_id") Integer msisdnId) {
        return msisdnMgmtService.deleteMsisdnMgmt(msisdnId);
    }

    @RequestMapping(value = "/get/all", method = RequestMethod.GET)
    public List<MsisdnMgmtDtoNew> getAllMsisdnDetails() {
        return msisdnMgmtService.getAllMsisdnDetail();
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<MsisdnMgmtDtoNew> searchRecord(@RequestParam("keyword") String keyword) {
        return msisdnMgmtService.searchRecord(keyword);
    }
}
