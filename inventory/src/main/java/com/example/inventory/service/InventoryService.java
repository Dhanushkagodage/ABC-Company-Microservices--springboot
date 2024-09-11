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

    public List<InventoryDTO> getALLItems(){
        List<Inventory> itemList = inventoryRepository.findAll();
        return modelMapper.map(itemList,new TypeToken<List<InventoryDTO>>(){}.getType());
    }
    public InventoryDTO getItemById(Integer itemId){
        Inventory item = inventoryRepository.getInventoryByID(itemId);
        return modelMapper.map(item,InventoryDTO.class);
    }
    public InventoryDTO addItem(InventoryDTO inventoryDTO){
        inventoryRepository.save(modelMapper.map(inventoryDTO,Inventory.class));
        return inventoryDTO;
    }
    public InventoryDTO updateItem(InventoryDTO inventoryDTO){
        inventoryRepository.save(modelMapper.map(inventoryDTO,Inventory.class));
        return inventoryDTO;
    }
    public String deleteItem(Integer inventoryId){
        inventoryRepository.deleteById(inventoryId);
        return "Inventory " +inventoryId+" is deleted";
    }
}
