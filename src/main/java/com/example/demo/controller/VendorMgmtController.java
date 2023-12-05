package com.example.demo.controller;

import com.example.demo.dto.SimMgmtDto;
import com.example.demo.dto.VendorMgmtDto;
import com.example.demo.service.SimMgmtService;
import com.example.demo.service.VendorMgmtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/vendor/mgmt/detail")
public class VendorMgmtController {
    @Autowired
    private VendorMgmtService vendorMgmtService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<VendorMgmtDto> fetchSimMgmtDetails(@RequestBody VendorMgmtDto vendorMgmtDto) {
        return vendorMgmtService.saveVendor(vendorMgmtDto);
    }

    @RequestMapping(value = "/get/all", method = RequestMethod.GET)
    public List<VendorMgmtDto> getAllSimMgmtDetails() {
        return vendorMgmtService.fetchAllVendors();
    }
}
