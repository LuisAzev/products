package com.mercadona.products.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@TestConfiguration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.mercadona.products.repository")
public class PersistenceConfig {

    @Autowired
    private ApplicationContext appContext;

    /**
     * Creates and returns a datasource
     * (for testing environment)
     *
     * @return the new datasource
     */
    @Bean(destroyMethod="")
    public DataSource dataSource() {

        final HikariConfig config = new HikariConfig();
        config.setDriverClassName("org.h2.Driver");
        config.setJdbcUrl("jdbc:h2:mem:myDb;DB_CLOSE_DELAY=-1");
        config.setUsername("sa");
        config.setPassword("sa");
        config.setAutoCommit(false);

        return new HikariDataSource( config );
    }

    /**
     * Creates and returns an entity manager factory
     *
     * @return the new entity manager factory
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        final LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        final JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

        entityManagerFactory.setPackagesToScan("com.mercadona.products.entity");
        entityManagerFactory.setPersistenceUnitName("products-pu");
        entityManagerFactory.setDataSource( dataSource() );
        entityManagerFactory.setJpaProperties( getHibernateProperties() );
        entityManagerFactory.setJpaVendorAdapter(vendorAdapter);

        return entityManagerFactory;
    }

    /**
     * Creates and returns a transaction manager
     *
     * @return the new transaction manager
     */
    @Bean
    public PlatformTransactionManager transactionManager() {

        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory( entityManagerFactory().getObject() );

        return transactionManager;
    }

    /**
     * Sets every hibernate database connection parameter
     *
     * @return hibernate properties to be use during the database connection setup
     */
    private final Properties getHibernateProperties() {

        final Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");

        return hibernateProperties;
    }

}