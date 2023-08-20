package com.springboot2.legacy.repository.first;


import com.springboot2.legacy.entity.first.PokemonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonRepository extends JpaRepository<PokemonEntity, Long> {
}
