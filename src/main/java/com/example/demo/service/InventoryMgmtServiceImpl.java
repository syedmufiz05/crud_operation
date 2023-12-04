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
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class InventoryMgmtServiceImpl implements InventoryMgmtService {
    @Autowired
    private InventoryMgmtRepository inventoryMgmtRepository;

    @Override
    public ResponseEntity saveInventory(InventoryMgmtDto inventoryMgmtDto) throws ParseException {
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
    public List<InventoryMgmtDto> getAllInventory() {
        return inventoryMgmtRepository.fetchAllInventoriesMgmt();
    }

    private Date fetchReadableDateTime(String value) throws ParseException {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = sdf.parse(value);
        System.out.println(date);
        return date;
    }
}
