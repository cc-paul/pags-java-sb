package com.mycompany.items_service.Service;

import com.mycompany.items_service.DTO.ItemDTO;

import java.util.List;

public interface ItemService {
    ItemDTO addItem(ItemDTO itemDTO);
    List<ItemDTO> allItems();
    List<ItemDTO> searchItemByTitle(String title);
    ItemDTO getDetail(Long id);
    ItemDTO updateItem(Long id,ItemDTO itemDTO);
    ItemDTO updateItemTitle(Long id,String title);
    void deleteItem(Long id);
}
