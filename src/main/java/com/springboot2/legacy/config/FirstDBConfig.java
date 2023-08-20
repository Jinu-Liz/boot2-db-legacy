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
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
  basePackages = "com.springboot2.legacy.repository.first",
  entityManagerFactoryRef = "firstEntityManagerFactory",
  transactionManagerRef = "firstTransactionManager"
)
@MapperScan(value = "com.springboot2.legacy.mapper.first", sqlSessionFactoryRef = "firstSessionFactory")
public class FirstDBConfig extends DBConfig {

  private final String FIRST_DATA_SOURCE = "firstDataSource";

  private final String FIRST_MANAGER_FACTORY = "firstEntityManagerFactory";

  private final String FIRST_TRANSACTION_MANAGER = "firstTransactionManager";

  private final String FIRST_SESSION_FACTORY = "firstSessionFactory";

  private final String FIRST_SESSION_TEMPLATE = "firstSessionTemplate";

  @ConfigurationProperties(prefix = "spring.first-db.datasource")
  @Bean(name = FIRST_DATA_SOURCE)
  @Primary
  public DataSource dataSource() {
    return DataSourceBuilder.create()
      .type(HikariDataSource.class)
      .build();
  }

  @Bean(name = FIRST_MANAGER_FACTORY)
  @Primary
  public EntityManagerFactory entityManagerFactory(@Qualifier(FIRST_DATA_SOURCE) DataSource dataSource) {
    LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
    factory.setDataSource(dataSource);
    factory.setPackagesToScan("com.springboot2.legacy.entity.first");
    factory.setPersistenceUnitName("firstEntityManager");
    setConfigEntityManagerFactory(factory);

    return factory.getObject();
  }

  @Bean(name = FIRST_TRANSACTION_MANAGER)
  @Primary
  public PlatformTransactionManager transactionManager(@Qualifier(FIRST_MANAGER_FACTORY) EntityManagerFactory entityManagerFactory) {
    JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(entityManagerFactory);

    return transactionManager;
  }

  @Bean(name = FIRST_SESSION_FACTORY)
  @Primary
  public SqlSessionFactory sqlSessionFactory(@Qualifier(FIRST_DATA_SOURCE) DataSource dataSource) throws Exception {
    SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
    setConfigSqlSessionFactory(sessionFactoryBean, dataSource);

    return sessionFactoryBean.getObject();
  }

  @Bean(name = FIRST_SESSION_TEMPLATE)
  @Primary
  public SqlSessionTemplate sqlSessionTemplate(@Qualifier(FIRST_SESSION_FACTORY) SqlSessionFactory sqlSessionFactory) {
    return new SqlSessionTemplate(sqlSessionFactory);
  }
}