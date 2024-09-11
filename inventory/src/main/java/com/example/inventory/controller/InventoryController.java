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

    @GetMapping("/getitems")
    public List<InventoryDTO> getItems() {
        return inventoryService.getALLItems();
    }

    @GetMapping("/getitem/{itemID}")
    public InventoryDTO getItem(@PathVariable("itemID") Integer itemID) {
        return inventoryService.getItemById(itemID);
    }

    @PostMapping("/additem")
    public InventoryDTO addItem(@RequestBody InventoryDTO inventoryDTO) {
        return inventoryService.addItem(inventoryDTO);
    }

    @PutMapping("/updateitem")
    public InventoryDTO updateItem(@RequestBody InventoryDTO inventoryDTO) {
        return inventoryService.updateItem(inventoryDTO);
    }

    @DeleteMapping("/deleteinventory/{inventoryID}")
    public String deleteItem(@PathVariable("inventoryID") Integer inventoryID) {
        return inventoryService.deleteItem(inventoryID);
    }
}
