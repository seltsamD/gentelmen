package com.gent.config;

import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by daria on 30.09.2016.
 */
@Configuration
@EnableTransactionManagement
@PropertySource("classpath:database.properties")
public class DBConfig {
    @Autowired
    private Environment env;
    @Bean
    public HibernateTemplate hibernateTemplate() { //РІСЃРїРѕРјРѕРіР°С‚РµР»СЊРЅС‹Р№ РєР»Р°СЃСЃ РґР»СЏ РІС‹РїРѕР»РЅРµРЅРёСЏ hibernate-РјРµС‚РѕРґРѕРІ
        return new HibernateTemplate(sessionFactory());
    }
    @Bean
    public SessionFactory sessionFactory() {
        LocalSessionFactoryBean lsfb = new LocalSessionFactoryBean();
        lsfb.setDataSource(getDataSource());
        lsfb.setPackagesToScan("com.gent.model");
        lsfb.setHibernateProperties(hibernateProperties());
        try {
            lsfb.afterPropertiesSet();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lsfb.getObject();
    }

    @Bean
    public DataSource getDataSource() {

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(env.getProperty("database.driver"));
        dataSource.setUrl(env.getProperty("database.url"));
        dataSource.setUsername(env.getProperty("database.root"));
        dataSource.setPassword(env.getProperty("database.password"));
        return dataSource;
    }
    @Bean
    public HibernateTransactionManager hibTransMan(){
        return new HibernateTransactionManager(sessionFactory());
    }
    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        properties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        properties.put("hibernate.connection.CharSet", "UTF-8");
        properties.put("hibernate.connection.useUnicode", true);
        properties.put("hibernate.connection.characterEncoding", "UTF-8");


        properties.put("mapping.class", "com.gent.model.Orders");
        properties.put("mapping.class", "com.gent.model.Good");
        return properties;
    }
}