package com.example.demo.service;

import com.example.demo.dto.VendorMgmtDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface VendorMgmtService {
    ResponseEntity saveVendor(VendorMgmtDto vendorMgmtDto);

    ResponseEntity editVendor(Integer vendorId, VendorMgmtDto vendorMgmtDto);

    ResponseEntity deleteVendor(Integer vendorId);

    List<VendorMgmtDto> fetchAllVendors();

    List<VendorMgmtDto> searchVendors(String keyword);
}
