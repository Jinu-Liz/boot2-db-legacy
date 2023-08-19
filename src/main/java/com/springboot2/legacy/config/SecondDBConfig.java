package com.springboot2.legacy.config;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
  basePackages = "com.springboot2.legacy.repository.second",
  entityManagerFactoryRef = "secondEntityManagerFactory",
  transactionManagerRef = "secondTransactionManager"
)
@MapperScan(value = "com.springboot2.legacy.mapper.second", sqlSessionFactoryRef = "secondSessionFactory")
public class SecondDBConfig extends DBConfig {

  private final String SECOND_DATA_SOURCE = "secondDataSource";

  private final String SECOND_MANAGER_FACTORY = "secondEntityManagerFactory";

  private final String SECOND_TRANSACTION_MANAGER = "secondTransactionManager";

  private final String SECOND_SESSION_FACTORY = "secondSessionFactory";

  private final String SECOND_SESSION_TEMPLATE = "secondSessionTemplate";

  @ConfigurationProperties(prefix = "spring.second-db.datasource")
  @Bean(name = SECOND_DATA_SOURCE)
  public DataSource ajpDataSource() {
    return DataSourceBuilder.create().type(HikariDataSource.class).build();
  }

  @Bean(name = SECOND_MANAGER_FACTORY)
  public EntityManagerFactory entityManagerFactory(@Qualifier(SECOND_DATA_SOURCE) DataSource dataSource) {
    LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
    factory.setDataSource(dataSource);
    factory.setPackagesToScan("com.springboot2.legacy.entity.second");
    factory.setPersistenceUnitName("secondEntityManager");
    setConfigEntityManagerFactory(factory);

    return factory.getObject();
  }

  @Bean(name = SECOND_TRANSACTION_MANAGER)
  public PlatformTransactionManager transactionManager(@Qualifier(SECOND_MANAGER_FACTORY) EntityManagerFactory entityManagerFactory) {
    JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(entityManagerFactory);

    return transactionManager;
  }

  @Bean(name = SECOND_SESSION_FACTORY)
  public SqlSessionFactory sqlSessionFactory(@Qualifier(SECOND_DATA_SOURCE) DataSource dataSource) throws Exception {
    SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
    setConfigSqlSessionFactory(sessionFactoryBean, dataSource);
    return sessionFactoryBean.getObject();
  }

  @Bean(name = SECOND_SESSION_TEMPLATE)
  public SqlSessionTemplate ajpSqlSessionTemplate(@Qualifier(SECOND_SESSION_FACTORY) SqlSessionFactory sqlSessionFactory) {
    return new SqlSessionTemplate(sqlSessionFactory);
  }
}
