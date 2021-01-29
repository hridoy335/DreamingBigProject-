package org.sr.project.core.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.sr.project.core.util.ProjectDataSource;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;


@Configuration
@EnableTransactionManagement
@PropertySources({
        @PropertySource("classpath:config/application-${envTargert:postgresql}.properties")
})
@ComponentScan("org.sr.project.core")
@EnableJpaRepositories(basePackages = "org.sr.project.core.repository")
public class AppConfig {

    //region PRIVATE VARIABLES
    private final static int CONN_PULL_SIZE = 5;

    @Autowired
    private Environment environment;
    //endregion

    //region PUBLIC METHODS
    public AppConfig(){
        super();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws IOException {
        final LocalContainerEntityManagerFactoryBean entityManagerFactoryBean =  new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPackagesToScan(new String[]{"org.sr.project.core.model"});

        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
        entityManagerFactoryBean.setJpaProperties(additionalProperties());
        return entityManagerFactoryBean;
    }

    @Bean
    public DataSource dataSource() throws IOException{
        ProjectDataSource projectDataSource = new ProjectDataSource();
        HikariConfig hikariConfig =  new HikariConfig();
        hikariConfig.setDriverClassName(environment.getProperty("jdbc.driverClass"));
        hikariConfig.setJdbcUrl(environment.getProperty("jdbc.url"));
        hikariConfig.setUsername(projectDataSource.getUserName(environment.getProperty("jdbc.userName")));
        hikariConfig.setPassword(projectDataSource.getPassword(environment.getProperty("jdbc.password")));
        hikariConfig.setAutoCommit(true);
        hikariConfig.setConnectionTimeout(34000);
        hikariConfig.setIdleTimeout(28740000);
        hikariConfig.setMaxLifetime(28740000);
        return new HikariDataSource(hikariConfig);
    }

    @Bean
    public PlatformTransactionManager transactionManager(final EntityManagerFactory emf){
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
        return new PersistenceExceptionTranslationPostProcessor();
    }
    //endregion

    //region PRIVATE METHODS
    final Properties  additionalProperties(){
        final Properties hibernateProperties =  new Properties();
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto",environment.getProperty("hibernate.hbm2ddl.auto"));
        hibernateProperties.setProperty("hibernate.dialect",environment.getProperty("hibernate.dialect"));
        hibernateProperties.setProperty("hibernate.cache.use_second_level_cache",environment.getProperty("hibernate.cache.use_second_level_cache"));
        hibernateProperties.setProperty("hibernate.cache.use_query_cache",environment.getProperty("hibernate.cache.use_query_cache"));
        hibernateProperties.setProperty("hibernate.show_sql",environment.getProperty("hibernate.show_sql"));
        hibernateProperties.setProperty("hibernate.format_sql",environment.getProperty("hibernate.format_sql"));
        hibernateProperties.setProperty("hibernate.jdbc.batch_size",environment.getProperty("hibernate.jdbc.batch_size"));

        //HikariCP setting
        // Maximum waiting time for a connection From the pool
        hibernateProperties.setProperty("hibernate.hikari.connectionTimeout","20000");
        //Minimum Number of ideal connection in the pool
        hibernateProperties.setProperty("hibernate.hikari.minimumIdle","150");
        //Maximum number of actual connection in the Pool
        hibernateProperties.setProperty("hibernate.hikari.maximumPoolSize","300");
        //Maximum time that a connection is allowed to sit idle in the pool
        hibernateProperties.setProperty("hibernate.hikari.idleTimeout","300000");
        return hibernateProperties;
    }
    //endregion
}
