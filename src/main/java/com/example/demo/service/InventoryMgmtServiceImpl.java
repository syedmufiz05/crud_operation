package com.example.demo.service;

import com.example.demo.dto.InventoryMgmtDto;
import com.example.demo.exception.CustomMessage;
import com.example.demo.model.InventoryMgmt;
import com.example.demo.repository.InventoryMgmtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class InventoryMgmtServiceImpl implements InventoryMgmtService {
    @Autowired
    private InventoryMgmtRepository inventoryMgmtRepository;

    @Override
    public ResponseEntity saveInventory(InventoryMgmtDto inventoryMgmtDto) {
        Optional<InventoryMgmt> inventoryMgmt = inventoryMgmtRepository.findByImsi(inventoryMgmtDto.getImsi());
        if (!inventoryMgmt.isPresent()) {
            InventoryMgmt inventoryMgmtDb = new InventoryMgmt();
            inventoryMgmtDb.setImsi(inventoryMgmtDto.getImsi() != null ? inventoryMgmtDto.getImsi() : "");
            inventoryMgmtDb.setPImsi(inventoryMgmtDto.getPImsi() != null ? inventoryMgmtDto.getPImsi() : "");
            inventoryMgmtDb.setBatchId(inventoryMgmtDto.getBatchId() != null ? inventoryMgmtDto.getBatchId() : Integer.valueOf(""));
            inventoryMgmtDb.setVendorId(inventoryMgmtDto.getVendorId() != null ? inventoryMgmtDto.getVendorId() : Integer.valueOf(""));
            inventoryMgmtDb.setMsisdn(inventoryMgmtDto.getMsisdn() != null ? inventoryMgmtDto.getMsisdn() : "");
            inventoryMgmtDb.setStatus(inventoryMgmtDto.getStatus() != null ? inventoryMgmtDto.getStatus() : false);
            inventoryMgmtDb.setProvStatus(inventoryMgmtDto.getProvStatus() != null ? inventoryMgmtDto.getProvStatus() : false);
            inventoryMgmtRepository.save(inventoryMgmtDb);
            InventoryMgmtDto inventoryMgmtDtoNew = new InventoryMgmtDto(inventoryMgmtDb.getId(), inventoryMgmtDb.getImsi(), inventoryMgmtDb.getPImsi(), inventoryMgmtDb.getBatchId(), inventoryMgmtDb.getVendorId(), inventoryMgmtDb.getMsisdn(), inventoryMgmtDb.getStatus(), inventoryMgmtDb.getProvStatus(), inventoryMgmtDb.getAllocationDate(), inventoryMgmtDb.getActivationDate());
            return new ResponseEntity<>(inventoryMgmtDtoNew, HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new CustomMessage(HttpStatus.CONFLICT.value(), "IMSI Id already exist"));
    }

    @Override
    public ResponseEntity editInventory(Integer inventoryId, InventoryMgmtDto inventoryMgmtDto) {
        Optional<InventoryMgmt> inventoryMgmt = inventoryMgmtRepository.findById(inventoryId);
        if (inventoryMgmt.isPresent()) {
            InventoryMgmt inventoryMgmtDb = inventoryMgmt.get();
            inventoryMgmtDb.setImsi(inventoryMgmtDto.getImsi() != null ? inventoryMgmtDto.getImsi() : inventoryMgmtDb.getImsi());
            inventoryMgmtDb.setPImsi(inventoryMgmtDto.getPImsi() != null ? inventoryMgmtDto.getPImsi() : inventoryMgmtDb.getPImsi());
            inventoryMgmtDb.setBatchId(inventoryMgmtDto.getBatchId() != null ? inventoryMgmtDto.getBatchId() : inventoryMgmtDb.getBatchId());
            inventoryMgmtDb.setVendorId(inventoryMgmtDto.getVendorId() != null ? inventoryMgmtDto.getVendorId() : inventoryMgmtDb.getVendorId());
            inventoryMgmtDb.setMsisdn(inventoryMgmtDto.getMsisdn() != null ? inventoryMgmtDto.getMsisdn() : inventoryMgmtDb.getMsisdn());
            inventoryMgmtDb.setStatus(inventoryMgmtDto.getStatus() != null ? inventoryMgmtDto.getStatus() : inventoryMgmtDb.getStatus());
            inventoryMgmtDb.setProvStatus(inventoryMgmtDto.getProvStatus() != null ? inventoryMgmtDto.getProvStatus() : inventoryMgmtDb.getProvStatus());
            inventoryMgmtRepository.save(inventoryMgmtDb);
            InventoryMgmtDto inventoryMgmtDtoNew = new InventoryMgmtDto(inventoryMgmtDb.getId(), inventoryMgmtDb.getImsi(), inventoryMgmtDb.getPImsi(), inventoryMgmtDb.getBatchId(), inventoryMgmtDb.getVendorId(), inventoryMgmtDb.getMsisdn(), inventoryMgmtDb.getStatus(), inventoryMgmtDb.getProvStatus(), inventoryMgmtDb.getAllocationDate(), inventoryMgmtDb.getActivationDate());
            return new ResponseEntity<>(inventoryMgmtDtoNew, HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomMessage(HttpStatus.NOT_FOUND.value(), "Inventory Id does n't exist"));
    }

    @Override
    public List<InventoryMgmtDto> getAllInventory() {
        return inventoryMgmtRepository.fetchAllInventoriesMgmt();
    }

    @Override
    public List<InventoryMgmtDto> searchRecord(String keyword) {
        List<InventoryMgmt> inventoryMgmtDbList = inventoryMgmtRepository.searchItemsByName(keyword);
        List<InventoryMgmtDto> inventoryMgmtDtoList = new ArrayList<>();
        for (InventoryMgmt inventoryMgmt : inventoryMgmtDbList) {
            InventoryMgmtDto inventoryMgmtDto = new InventoryMgmtDto();
            inventoryMgmtDto.setInventoryId(inventoryMgmt.getId());
            inventoryMgmtDto.setImsi(inventoryMgmt.getImsi());
            inventoryMgmtDto.setPImsi(inventoryMgmt.getPImsi());
            inventoryMgmtDto.setBatchId(inventoryMgmt.getBatchId());
            inventoryMgmtDto.setVendorId(inventoryMgmt.getVendorId());
            inventoryMgmtDto.setMsisdn(inventoryMgmt.getMsisdn());
            inventoryMgmtDto.setStatus(inventoryMgmt.getStatus());
            inventoryMgmtDto.setProvStatus(inventoryMgmt.getProvStatus());
            inventoryMgmtDto.setAllocationDate(inventoryMgmt.getAllocationDate());
            inventoryMgmtDto.setActivationDate(inventoryMgmt.getActivationDate());
            inventoryMgmtDtoList.add(inventoryMgmtDto);
        }
        return inventoryMgmtDtoList;
    }

    private Date fetchReadableDateTime(String value) throws ParseException {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = sdf.parse(value);
        System.out.println(date);
        return date;
    }
}
