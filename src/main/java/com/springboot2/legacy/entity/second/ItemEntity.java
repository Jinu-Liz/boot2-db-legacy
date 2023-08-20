package com.springboot2.legacy.entity.second;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Entity
@Table(name = "item")
public class ItemEntity {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private int itemId;

  private String dtype;

  private int price;

  private int stockQuantity;

}
