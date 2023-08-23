package com.springboot2.legacy.service.first;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.springboot2.legacy.entity.first.PokemonEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.springboot2.legacy.entity.first.QPokemonEntity.pokemonEntity;


@Service
@RequiredArgsConstructor
public class PokemonService {

  @Qualifier("FirstQF")
  private final JPAQueryFactory queryFactory;

  public List<PokemonEntity> selectPokemon() {

    return queryFactory
      .selectFrom(pokemonEntity)
      .fetch();
  }

}
