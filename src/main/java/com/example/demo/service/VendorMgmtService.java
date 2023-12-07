package com.example.demo.service;

import com.example.demo.dto.VendorMgmtDto;
import com.example.demo.model.VendorMgmt;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface VendorMgmtService {
    ResponseEntity saveVendor(VendorMgmtDto vendorMgmtDto);
    List<VendorMgmtDto> fetchAllVendors();
    List<VendorMgmtDto> searchVendors(String keyword);
}
