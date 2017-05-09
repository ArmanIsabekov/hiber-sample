/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myexercises.hiber.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.myexercises.hiber.Application;
import com.myexercises.hiber.entity.Data;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.util.Properties;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author Acer
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackageClasses = Application.class)
@PropertySource("classpath:/com/myexercises/hiber/config/application.properties")
public class JpaConfig {
    
    @Value("${dataSource.driverClassName}")
    private String driver;
    @Value("${dataSource.url}")
    private String url;
    @Value("${dataSource.username}")
    private String username;
    @Value("${dataSource.password}")
    private String password;
    @Value("${hibernate.dialect}")
    private String dialect;
    @Value("${hibernate.hbm2ddl.auto}")
    private String hbm2ddlAuto;
    @Value("${hibernate.show_sql}")
    private Boolean showSql;
    
    @Bean
    @Profile("hikari")
    public DataSource hikariDataSource() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(driver);
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        return new HikariDataSource(config);
    }
    
    @Bean
    @Profile({"default","cpds"})
    public DataSource cpds() throws Exception {
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        cpds.setDriverClass(driver);
        cpds.setJdbcUrl(url);
        cpds.setUser(username);
        cpds.setPassword(password);
        return cpds;
    }
    
    @Bean
    public PlatformTransactionManager transactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
        
    }
    
    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setAnnotatedClasses(Data.class);
        Properties hibernateProperties = new Properties();
        hibernateProperties.put(org.hibernate.cfg.AvailableSettings.HBM2DDL_AUTO, hbm2ddlAuto);
        hibernateProperties.put(org.hibernate.cfg.AvailableSettings.DIALECT, dialect);
        hibernateProperties.put(org.hibernate.cfg.AvailableSettings.SHOW_SQL, showSql);
        hibernateProperties.put(org.hibernate.cfg.AvailableSettings.FORMAT_SQL, showSql);
        hibernateProperties.put(org.hibernate.cfg.AvailableSettings.USE_SQL_COMMENTS, showSql);
        sessionFactory.setHibernateProperties(hibernateProperties);
        return sessionFactory;
    }
    
    @Bean
    public HibernateTemplate hibernateTemplate(SessionFactory sessionFactory) {
        HibernateTemplate hibernateTemplate = new HibernateTemplate(sessionFactory);
        return hibernateTemplate;
    }
    
}
