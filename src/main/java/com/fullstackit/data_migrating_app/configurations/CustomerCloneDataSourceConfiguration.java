package com.fullstackit.data_migrating_app.configurations;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.fullstackit.data_migrating_app.customer_clone.Customer;
import com.zaxxer.hikari.HikariDataSource;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.fullstackit.data_migrating_app.customer_clone",
        entityManagerFactoryRef = "customerCloneEntityManagerFactory",
        transactionManagerRef = "customerCloneTransactionManager")
public class CustomerCloneDataSourceConfiguration {
    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.customerclone")
    public DataSourceProperties customerCloneDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.customerclone.configuration")
    public DataSource customerCloneDataSource() {
        return customerCloneDataSourceProperties().initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }

    @Primary
    @Bean(name = "customerCloneEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean customerCloneEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(customerCloneDataSource())
                .packages( Customer.class)
                .build();
    }


    @Primary
    @Bean(name = "customerCloneTransactionManager")
    public PlatformTransactionManager customerTransactionManager(
            final @Qualifier("customerCloneEntityManagerFactory") LocalContainerEntityManagerFactoryBean customerCloneEntityManagerFactory) {
        return new JpaTransactionManager(customerCloneEntityManagerFactory.getObject());
    }
}

