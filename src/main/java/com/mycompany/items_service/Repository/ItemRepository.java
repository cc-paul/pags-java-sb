package com.mycompany.items_service.Repository;

import com.mycompany.items_service.Entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<ItemEntity,Long> {
    List<ItemEntity> findAllByTitleContains(String title);
}
