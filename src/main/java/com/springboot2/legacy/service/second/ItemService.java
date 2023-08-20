package com.springboot2.legacy.service.second;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.springboot2.legacy.entity.second.ItemEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.springboot2.legacy.entity.second.QItemEntity.itemEntity;


@Service
@RequiredArgsConstructor
public class ItemService {

  private final JPAQueryFactory queryFactory;

  public List<ItemEntity> selectItems() {

    return queryFactory
      .selectFrom(itemEntity)
      .fetch();
  }

}
