package com.example.demo.service;

import com.example.demo.dto.InventoryMgmtDto;
import com.example.demo.dto.InventoryMgmtDtoList;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface InventoryMgmtService {
    ResponseEntity saveInventory(InventoryMgmtDto inventoryMgmtDto);

    ResponseEntity editInventory(Integer inventoryId, InventoryMgmtDtoList inventoryMgmtDtoList);

    ResponseEntity deleteInventory(Integer inventoryId);

    List<InventoryMgmtDto> getAllInventory();

    List<InventoryMgmtDto> searchRecord(String keyword);

}
