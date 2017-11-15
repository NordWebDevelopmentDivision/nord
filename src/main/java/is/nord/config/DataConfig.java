package is.nord.config;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import java.util.Properties;

/*
 * Author:
 *       Chris Ramacciotti, a teacher at teamtreehouse.com
 * Altered by:
 *       Ólafur Georg Gylfason (ogg4@hi.is)
*/

@Configuration
@EnableJpaRepositories(basePackages = "is.nord.repository")
@PropertySource(value="application.properties", ignoreResourceNotFound=true)
public class DataConfig {
    @Autowired
    private Environment env;

    @Bean
    @PersistenceContext
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

        //factory.setDataSource(dataSource());
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan(env.getProperty("nord.entity.package"));
        //factory.setJpaProperties(getHibernateProperties());

        return factory;
    }
 /*
    @Bean
    public DataSource dataSource() {
        BasicDataSource ds = new BasicDataSource();
       ds.setDriverClassName(env.getProperty("nord.db.driver"));
        ds.setUrl(env.getProperty("nord.db.url"));
        ds.setUsername(env.getProperty("nord.db.username"));
        ds.setPassword(env.getProperty("nord.db.password"));
        return ds;

    }*/
/*
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    private Properties getHibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        properties.put("hibernate.implicit_naming_strategy",env.getProperty("hibernate.implicit_naming_strategy"));
        properties.put("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
        properties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        return properties;
    }*/
}
