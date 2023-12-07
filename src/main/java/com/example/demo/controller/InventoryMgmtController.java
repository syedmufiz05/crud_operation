package com.example.demo.controller;

import com.example.demo.dto.InventoryMgmtDto;
import com.example.demo.service.InventoryMgmtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory/detail")
@CrossOrigin("http://localhost:5173/")
public class InventoryMgmtController {
    @Autowired
    private InventoryMgmtService inventoryMgmtService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<InventoryMgmtDto> saveInventoryDetails(@RequestBody InventoryMgmtDto inventoryMgmtDto) {
        return inventoryMgmtService.saveInventory(inventoryMgmtDto);
    }

    @RequestMapping(value = "/edit/{inventory_id}", method = RequestMethod.PUT)
    public ResponseEntity<InventoryMgmtDto> editInventoryDetails(@PathVariable("inventory_id") Integer inventoryId, @RequestBody InventoryMgmtDto inventoryMgmtDto) {
        return inventoryMgmtService.editInventory(inventoryId, inventoryMgmtDto);
    }

    @RequestMapping(value = "/delete/{inventory_id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteInventoryDetail(@PathVariable("inventory_id") Integer inventoryId) {
        return inventoryMgmtService.deleteInventory(inventoryId);
    }

    @RequestMapping(value = "/get/all", method = RequestMethod.GET)
    public List<InventoryMgmtDto> getAllInventoryDetails() {
        return inventoryMgmtService.getAllInventory();
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ResponseEntity<List<InventoryMgmtDto>> searchInventoryDetails(@RequestParam("keyword") String keyword) {
        List<InventoryMgmtDto> inventoryMgmtDtoList = inventoryMgmtService.searchRecord(keyword);
        return ResponseEntity.ok(inventoryMgmtDtoList);
    }
}
