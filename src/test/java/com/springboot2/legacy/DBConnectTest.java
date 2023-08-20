package com.springboot2.legacy;


import com.springboot2.legacy.dto.first.PokemonDto;
import com.springboot2.legacy.dto.second.ItemDto;
import com.springboot2.legacy.entity.first.PokemonEntity;
import com.springboot2.legacy.entity.second.ItemEntity;
import com.springboot2.legacy.mapper.first.PokemonMapper;
import com.springboot2.legacy.mapper.second.ItemMapper;
import com.springboot2.legacy.repository.first.PokemonRepository;
import com.springboot2.legacy.repository.second.ItemRepository;
import com.springboot2.legacy.service.first.PokemonService;
import com.springboot2.legacy.service.second.ItemService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@SpringBootTest
@Rollback
public class DBConnectTest {

  @Autowired
  PokemonRepository pokemonRepository;

  @Autowired
  PokemonMapper pokemonMapper;

  @Autowired
  ItemRepository itemRepository;

  @Autowired
  ItemMapper itemMapper;

  @Autowired
  PokemonService pokemonService;

  @Autowired
  ItemService itemService;

  @Test
//  @Disabled
  void firstDB() {
    List<PokemonEntity> all = pokemonRepository.findAll();
    all.forEach(ent -> System.out.println("FIRST DB JPA : " + ent.getPokemonId() + " : " + ent.getName()));

    List<PokemonDto> pokemons = pokemonMapper.getPokemons();
    pokemons.forEach(dto -> System.out.println("FIRST DB MyBatis : " + dto.getPokemonId() + " : " + dto.getName()));

    List<PokemonEntity> pokemonEntities = pokemonService.selectPokemon();
    pokemonEntities.forEach(ent -> System.out.println("FIRST DB QueryDSL : " + ent.getPokemonId() + " : " + ent.getName()));

    PokemonDto pokemonDto = new PokemonDto();
    pokemonDto.setName("Eevee");
    pokemonDto.setType("NORMAL");
    pokemonMapper.addPokemon(pokemonDto);
  }

  @Test
//  @Disabled
  void secondDB() {
    List<ItemEntity> all = itemRepository.findAll();
    all.forEach(ent -> System.out.println("SECOND DB JPA : " + ent.getPrice() + ", " + ent.getStockQuantity()));

    List<ItemDto> items = itemMapper.getItems();
    items.forEach(dto -> System.out.println("SECOND DB MyBatis : " + dto.getPrice() + ", " + dto.getStockQuantity()));

//    List<ItemEntity> itemEntities = itemService.selectItems();
//    itemEntities.forEach(ent -> System.out.println("SECOND DB QueryDSL : " + ent.getPrice() + ", " + ent.getStockQuantity()));
  }
}
