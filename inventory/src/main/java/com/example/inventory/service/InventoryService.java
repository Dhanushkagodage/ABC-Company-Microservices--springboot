package com.example.inventory.service;

import com.example.inventory.dto.InventoryDTO;
import com.example.inventory.model.Inventory;
import com.example.inventory.repository.InventoryRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<InventoryDTO> getALLInventories(){
        List<Inventory> inventoryList = inventoryRepository.findAll();
        return modelMapper.map(inventoryList,new TypeToken<List<InventoryDTO>>(){}.getType());
    }
    public InventoryDTO getInventory(Integer inventoryId){
        Inventory inventory = inventoryRepository.getInventoryByID(inventoryId);
        return modelMapper.map(inventory,InventoryDTO.class);
    }
    public InventoryDTO addInventory(InventoryDTO inventoryDTO){
        inventoryRepository.save(modelMapper.map(inventoryDTO,Inventory.class));
        return inventoryDTO;
    }
    public InventoryDTO updateInventory(InventoryDTO inventoryDTO){
        inventoryRepository.save(modelMapper.map(inventoryDTO,Inventory.class));
        return inventoryDTO;
    }
    public String deleteInventory(Integer inventoryId){
        inventoryRepository.deleteById(inventoryId);
        return "Inventory " +inventoryId+" is deleted";
    }
}
