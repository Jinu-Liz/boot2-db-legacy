package com.springboot2.legacy.config;

import com.zaxxer.hikari.HikariConfig;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Map;

@Configuration
public class DBConfig extends HikariConfig {

  @Autowired
  private HibernateProperties hibernateProperties;

  @Autowired
  private JpaProperties jpaProperties;

  protected void setConfigEntityManagerFactory(LocalContainerEntityManagerFactoryBean factory) {
    JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    Map<String, Object> properties = hibernateProperties.determineHibernateProperties(
      jpaProperties.getProperties(), new HibernateSettings()
    );

    factory.setJpaVendorAdapter(vendorAdapter);
    factory.setJpaPropertyMap(properties);
    factory.afterPropertiesSet();
  }

  protected void setConfigSqlSessionFactory(SqlSessionFactoryBean sessionFactoryBean, DataSource dataSource) throws IOException {
    sessionFactoryBean.setDataSource(dataSource);
    sessionFactoryBean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("classpath:/mybatis/config/mybatis-config.xml"));
    sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:/mybatis/mapper/*.xml"));
  }
}
