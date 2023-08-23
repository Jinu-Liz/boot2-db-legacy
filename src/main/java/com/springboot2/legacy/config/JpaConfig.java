package com.springboot2.legacy.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Configuration
public class JpaConfig {

  @PersistenceContext(unitName = "firstEntityManager")
  private EntityManager firstEntityManager;

  @PersistenceContext(unitName = "secondEntityManager")
  private EntityManager secondEntityManager;

  @Bean
  @Primary
  @Qualifier("FirstQF")
  JPAQueryFactory firstQueryFactory() {
    return new JPAQueryFactory(firstEntityManager);
  }

  @Bean
  @Qualifier("SecondQF")
  JPAQueryFactory secondQueryFactory() {
    return new JPAQueryFactory(secondEntityManager);
  }
}
