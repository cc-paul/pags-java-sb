package com.mycompany.items_service.Controller;


import com.mycompany.items_service.DTO.ItemDTO;
import com.mycompany.items_service.Service.ItemService;
import com.mycompany.items_service.Utilities.Common;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ItemController {
    @Autowired
    private ItemService itemService;
    @Autowired
    private Common common;

    @PostMapping("/items")
    public ResponseEntity<ItemDTO> addItem (@RequestBody ItemDTO itemDTO) {
        ResponseEntity<ItemDTO> response = ResponseEntity.status(201).body(itemDTO);
        itemService.addItem(itemDTO);

        return response;
    }

    @GetMapping("/items")
    public ResponseEntity<List<ItemDTO>> allItems() {
        List<ItemDTO> items = itemService.allItems();
        ResponseEntity<List<ItemDTO>> response = ResponseEntity.status(200).body(items);

        return response;
    }

    @GetMapping("/search-items-by-title")
    public ResponseEntity<List<ItemDTO>> searchByTitle(@RequestParam String title) {
        List<ItemDTO> items = itemService.searchItemByTitle(title);
        ResponseEntity<List<ItemDTO>> response = ResponseEntity.status(200).body(items);

        return response;
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<ItemDTO> getDetail(
            @PathVariable("id") Long id
    ) {
        ItemDTO items = itemService.getDetail(id);
        ResponseEntity<ItemDTO> response = ResponseEntity.status(200).body(items);

        return response;
    }

    @PutMapping("/items/{id}")
    public ResponseEntity<ItemDTO> updateItem(
            @PathVariable("id") Long id,
            @RequestBody ItemDTO itemDTO
    ) {
        ItemDTO item = itemService.updateItem(id,itemDTO);
        ResponseEntity<ItemDTO> response = ResponseEntity.status(200).body(item);

        return response;
    }

    @PatchMapping("/items/title/{id}")
    public ResponseEntity<ItemDTO> updateItemTitle(
            @PathVariable("id") Long id,
            @RequestBody ItemDTO itemDTO
    ) {
        itemDTO = itemService.updateItemTitle(id,itemDTO.getTitle());
        ResponseEntity<ItemDTO> response = ResponseEntity.status(200).body(itemDTO);

        return response;
    }

    @DeleteMapping("/items/{id}")
    public ResponseEntity<Void> deleteItem(
            @PathVariable("id") Long id
    ) {
        itemService.deleteItem(id);

        ResponseEntity<Void> response = new ResponseEntity<>(HttpStatus.NO_CONTENT); // Create the response with 204 status
        return response; // Return the response later
    }
}
