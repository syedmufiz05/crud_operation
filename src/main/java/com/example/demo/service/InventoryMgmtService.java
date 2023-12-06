package com.example.demo.service;

import com.example.demo.dto.InventoryMgmtDto;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;
import java.util.List;

public interface InventoryMgmtService {
    ResponseEntity saveInventory(InventoryMgmtDto inventoryMgmtDto) throws ParseException;

    List<InventoryMgmtDto> getAllInventory();

    List<InventoryMgmtDto> searchRecord(String imsi);

}
