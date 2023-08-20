package com.springboot2.legacy.entity.first;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static javax.persistence.GenerationType.IDENTITY;


@Getter
@Entity
@Table(name = "pokemon")
public class PokemonEntity {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long pokemonId;

  private String name;

  private String type;

}
