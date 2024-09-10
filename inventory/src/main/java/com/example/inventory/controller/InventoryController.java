package com.example.inventory.controller;

import com.example.inventory.dto.InventoryDTO;
import com.example.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "api/ABC/")

public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/getinventories")
    public List<InventoryDTO> getInventories() {
        return inventoryService.getALLInventories();
    }

    @GetMapping("/getinventory/{inventoryID}")
    public InventoryDTO getInventory(@PathVariable("inventoryID") Integer inventoryID) {
        return inventoryService.getInventory(inventoryID);
    }

    @PostMapping("/addinventory")
    public InventoryDTO addInventory(@RequestBody InventoryDTO inventoryDTO) {
        return inventoryService.addInventory(inventoryDTO);
    }

    @PutMapping("/updateinventory")
    public InventoryDTO updateInventory(@RequestBody InventoryDTO inventoryDTO) {
        return inventoryService.updateInventory(inventoryDTO);
    }

    @DeleteMapping("/deleteinventory/{inventoryID}")
    public String deleteInventory(@PathVariable("inventoryID") Integer inventoryID) {
        return inventoryService.deleteInventory(inventoryID);
    }
}
