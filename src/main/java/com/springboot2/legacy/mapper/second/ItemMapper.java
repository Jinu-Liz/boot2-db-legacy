package com.springboot2.legacy.mapper.second;


import com.springboot2.legacy.dto.second.ItemDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ItemMapper {

  List<ItemDto> getItems();
}
