package com.mycompany.items_service.Service.Impl;

import com.mycompany.items_service.DTO.ItemDTO;
import com.mycompany.items_service.Entity.ItemEntity;
import com.mycompany.items_service.Repository.ItemRepository;
import com.mycompany.items_service.Service.ItemService;
import com.mycompany.items_service.Utilities.Common;
import com.mycompany.items_service.Utilities.ItemConverter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ItemConverter itemConverter;

    @Override
    public ItemDTO addItem(ItemDTO itemDTO) {
        ItemEntity itemEntity = new ItemEntity();
        BeanUtils.copyProperties(itemDTO, itemEntity);

        itemEntity.setCreatedAt(LocalDateTime.now());
        itemEntity.setUpdatedAt(LocalDateTime.now());

        itemEntity = itemRepository.save(itemEntity);
        BeanUtils.copyProperties(itemEntity, itemDTO);
        itemDTO.setId(itemEntity.getId());

        return itemDTO;
    }

    @Override
    public List<ItemDTO> allItems() {
        List<ItemEntity> itemEntities = itemRepository.findAll();
        List<ItemDTO> dtoList = null;

        if (!itemEntities.isEmpty()) {
            dtoList = new ArrayList<>();

            for (ItemEntity ie : itemEntities) {
                dtoList.add(itemConverter.convertEntity2DTO(ie));
            }
        }

        return dtoList;
    }

    @Override
    public List<ItemDTO> searchItemByTitle(String title) {
        List<ItemEntity> itemEntities = itemRepository.findAllByTitleContains(title);
        List<ItemDTO> dtoList = null;

        if (!itemEntities.isEmpty()) {
            dtoList = new ArrayList<>();

            for (ItemEntity ie : itemEntities) {
                dtoList.add(itemConverter.convertEntity2DTO(ie));
            }
        }

        return dtoList;
    }

    @Override
    public ItemDTO getDetail(Long id) {
        Optional<ItemEntity> optIe = itemRepository.findById(id);
        ItemDTO itemDTO = null;

        if (optIe.isPresent()) {
            itemDTO = itemConverter.convertEntity2DTO(optIe.get());
        }

        return itemDTO;
    }

    @Override
    public ItemDTO updateItem(Long id,ItemDTO itemDTO) {
        Optional<ItemEntity> optIe = itemRepository.findById(id);

        if (optIe.isPresent()) {
            ItemEntity ie = optIe.get();
            ie.setTitle(itemDTO.getTitle());
            ie.setDescription(itemDTO.getDescription());
            ie.setUpdatedAt(LocalDateTime.now());
            ie = itemRepository.save(ie);

            itemDTO = itemConverter.convertEntity2DTO(ie);
        }

        return itemDTO;
    }

    @Override
    public ItemDTO updateItemTitle(Long id, String title) {
        Optional<ItemEntity> optIe = itemRepository.findById(id);
        ItemDTO itemDTO = null;

        if (optIe.isPresent()) {
            ItemEntity ie = optIe.get();
            ie.setTitle(title);
            ie.setUpdatedAt(LocalDateTime.now());
            ie = itemRepository.save(ie);

            itemDTO = itemConverter.convertEntity2DTO(ie);
        }

        return itemDTO;
    }

    @Override
    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }
}

