package com.mycompany.items_service.Utilities;

import com.mycompany.items_service.DTO.ItemDTO;
import com.mycompany.items_service.Entity.ItemEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ItemConverter {
    public ItemDTO convertEntity2DTO(ItemEntity ie) {
        ItemDTO itemDTO = new ItemDTO();
        BeanUtils.copyProperties(ie,itemDTO);
        itemDTO.setId(ie.getId());
        return itemDTO;
    }
}
