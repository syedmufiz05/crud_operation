package com.example.demo.service;

import com.example.demo.dto.VendorMgmtDto;
import com.example.demo.exception.CustomMessage;
import com.example.demo.model.VendorMgmt;
import com.example.demo.repository.VendorMgmtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public ResponseEntity editVendor(Integer vendorId, VendorMgmtDto vendorMgmtDto) {
        Optional<VendorMgmt> vendorMgmt = vendorMgmtRepository.findById(vendorId);
        if (vendorMgmt.isPresent()) {
            VendorMgmt vendorMgmtDb = vendorMgmt.get();
            vendorMgmtDb.setVendorName(vendorMgmtDto.getVendorName() != null ? vendorMgmtDto.getVendorName() : vendorMgmtDb.getVendorName());
            vendorMgmtDb.setEmail(vendorMgmtDto.getEmail() != null ? vendorMgmtDto.getEmail() : vendorMgmtDb.getEmail());
            vendorMgmtDb.setContact(vendorMgmtDto.getContact() != null ? vendorMgmtDto.getContact() : vendorMgmtDb.getContact());
            vendorMgmtDb.setAddress(vendorMgmtDto.getAddress() != null ? vendorMgmtDto.getAddress() : vendorMgmtDb.getAddress());
            vendorMgmtDb.setType(vendorMgmtDto.getType() != null ? vendorMgmtDto.getType() : vendorMgmtDb.getType());
            vendorMgmtDb.setIdentification(vendorMgmtDto.getIdentification() != null ? vendorMgmtDto.getIdentification() : vendorMgmtDb.getIdentification());
            vendorMgmtDb.setBatchPrefix(vendorMgmtDto.getBatchPrefix() != null ? vendorMgmtDto.getBatchPrefix() : vendorMgmtDb.getBatchPrefix());
            vendorMgmtDb.setStatus(vendorMgmtDto.getStatus() != null ? vendorMgmtDto.getStatus() : vendorMgmtDb.getStatus());
            vendorMgmtRepository.save(vendorMgmtDb);
            VendorMgmtDto vendorMgmtDtoNew = new VendorMgmtDto(vendorMgmtDb.getId(), vendorMgmtDb.getVendorName(), vendorMgmtDb.getEmail(), vendorMgmtDb.getContact(), vendorMgmtDb.getAddress(), vendorMgmtDb.getType(), vendorMgmtDb.getIdentification(), vendorMgmtDb.getBatchPrefix(), vendorMgmtDb.getRegistrationDate(), vendorMgmtDb.getStatus());
            return new ResponseEntity<>(vendorMgmtDtoNew, HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomMessage(HttpStatus.NOT_FOUND.value(), "Invalid Vendor Id"));
    }

    @Transactional
    @Override
    public ResponseEntity deleteVendor(Integer vendorId) {
        Optional<VendorMgmt> vendorMgmt = vendorMgmtRepository.findById(vendorId);
        if (vendorMgmt.isPresent()) {
            vendorMgmtRepository.deleteById(vendorId);
            return ResponseEntity.status(HttpStatus.OK).body(new CustomMessage(HttpStatus.OK.value(), "Deleted Successfully"));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomMessage(HttpStatus.NOT_FOUND.value(), "Invalid Vendor Id"));
    }

    @Override
    public List<VendorMgmtDto> fetchAllVendors() {
        return vendorMgmtRepository.fetchAllVendors();
    }

    @Override
    public List<VendorMgmtDto> searchVendors(String keyword) {
        List<VendorMgmt> vendorMgmtListDb = vendorMgmtRepository.searchItemsByName(keyword);
        List<VendorMgmtDto> vendorMgmtListDto = new ArrayList<>();
        for (VendorMgmt vendorMgmtDb : vendorMgmtListDb) {
            VendorMgmtDto vendorMgmtDto = new VendorMgmtDto();
            vendorMgmtDto.setVendorId(vendorMgmtDb.getId());
            vendorMgmtDto.setVendorName(vendorMgmtDb.getVendorName());
            vendorMgmtDto.setEmail(vendorMgmtDb.getEmail());
            vendorMgmtDto.setContact(vendorMgmtDb.getContact());
            vendorMgmtDto.setAddress(vendorMgmtDb.getAddress());
            vendorMgmtDto.setType(vendorMgmtDb.getType());
            vendorMgmtDto.setIdentification(vendorMgmtDb.getIdentification());
            vendorMgmtDto.setBatchPrefix(vendorMgmtDb.getBatchPrefix());
            vendorMgmtDto.setRegistrationDate(vendorMgmtDb.getRegistrationDate());
            vendorMgmtDto.setStatus(vendorMgmtDb.getStatus());
            vendorMgmtListDto.add(vendorMgmtDto);
        }
        return vendorMgmtListDto;
    }
}
