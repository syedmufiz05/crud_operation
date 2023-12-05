package com.example.demo.service;

import com.example.demo.dto.VendorMgmtDto;
import com.example.demo.model.VendorMgmt;
import com.example.demo.repository.VendorMgmtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorMgmtServiceImpl implements VendorMgmtService {
    @Autowired
    private VendorMgmtRepository vendorMgmtRepository;

    @Override
    public ResponseEntity saveVendor(VendorMgmtDto vendorMgmtDto) {
        VendorMgmt vendorMgmtDb = new VendorMgmt();
        vendorMgmtDb.setVendorName(vendorMgmtDto.getVendorName() != null ? vendorMgmtDto.getVendorName() : "");
        vendorMgmtDb.setEmail(vendorMgmtDto.getEmail() != null ? vendorMgmtDto.getEmail() : "");
        vendorMgmtDb.setContact(vendorMgmtDto.getContact() != null ? vendorMgmtDto.getContact() : "");
        vendorMgmtDb.setAddress(vendorMgmtDto.getAddress() != null ? vendorMgmtDto.getAddress() : "");
        vendorMgmtDb.setType(vendorMgmtDto.getType() != null ? vendorMgmtDto.getType() : "");
        vendorMgmtDb.setIdentification(vendorMgmtDto.getIdentification() != null ? vendorMgmtDto.getIdentification() : "");
        vendorMgmtDb.setBatchPrefix(vendorMgmtDto.getBatchPrefix() != null ? vendorMgmtDto.getBatchPrefix() : "");
        vendorMgmtDb.setStatus(vendorMgmtDto.getStatus() != null ? vendorMgmtDto.getStatus() : false);
        vendorMgmtRepository.save(vendorMgmtDb);
        VendorMgmtDto vendorMgmtDtoNew = new VendorMgmtDto(vendorMgmtDb.getId(), vendorMgmtDb.getVendorName(), vendorMgmtDb.getEmail(), vendorMgmtDb.getContact(), vendorMgmtDb.getAddress(), vendorMgmtDb.getType(), vendorMgmtDb.getIdentification(), vendorMgmtDb.getBatchPrefix(), vendorMgmtDb.getRegistrationDate(), vendorMgmtDb.getStatus());
        return new ResponseEntity<>(vendorMgmtDtoNew, HttpStatus.OK);
    }

    @Override
    public List<VendorMgmtDto> fetchAllVendors() {
        return vendorMgmtRepository.fetchAllVendors();
    }
}
