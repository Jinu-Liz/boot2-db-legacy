package com.springboot2.legacy.repository.second;


import com.springboot2.legacy.entity.second.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long> {
}
