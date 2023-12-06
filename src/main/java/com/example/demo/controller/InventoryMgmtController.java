package com.example.demo.controller;

import com.example.demo.dto.InventoryMgmtDto;
import com.example.demo.service.InventoryMgmtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/inventory/detail")
@CrossOrigin("http://localhost:5173/")
public class InventoryMgmtController {
    @Autowired
    private InventoryMgmtService inventoryMgmtService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<InventoryMgmtDto> fetchInventoryDetails(@RequestBody InventoryMgmtDto inventoryMgmtDto) throws ParseException {
        return inventoryMgmtService.saveInventory(inventoryMgmtDto);
    }

    @RequestMapping(value = "/get/all", method = RequestMethod.GET)
    public List<InventoryMgmtDto> getAllInventoryDetails() {
        return inventoryMgmtService.getAllInventory();
    }
}
