package org.cybertop.back.config.root;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.apache.log4j.Logger;
import org.cybertop.back.app.utils.FileUtils;
import org.cybertop.back.config.exception.DatabaseConfigurationException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * Development specific configuration 
 * datasource, sets hibernate on create drop mode and inserts some test data on
 * the database.
 *
 * Set -Dspring.profiles.active=development to activate this config.
 *
 */
@Configuration
@EnableJpaRepositories(
			basePackages = { "org.cybertop.back.app" },
			entityManagerFactoryRef="webEntityManagerFactory", 
			transactionManagerRef = "webTransactionManager")
@EnableTransactionManagement
public class DatabaseConfiguration {
	
	private static final Logger log = Logger.getLogger(DatabaseConfiguration.class);

	private Properties props;
	private boolean showSql = false;
	private boolean generateSql = false;
	private Database databaseType;
	private String databaseUrl;
	private String databaseDriver;
	private String databaseUser;
	private String databasePassword;

	public DatabaseConfiguration() throws DatabaseConfigurationException {
		props = new Properties();
		try {
			
			InputStream stream = FileUtils.getResources("application.properties");
			if(stream != null){
				props.load(stream);
				
				showSql 			= Boolean.parseBoolean(props.getProperty("db.showsql","false"));
				generateSql 		= Boolean.parseBoolean(props.getProperty("db.generateSql","false"));
				databaseType 		= Database.valueOf(props.getProperty("db.type"));
				databaseUrl 		= props.getProperty("db.url");
				databaseDriver 		= props.getProperty("db.driver");
				databaseUser 		= props.getProperty("db.user");
				databasePassword 	= props.getProperty("db.password");
				log.info("db.properties loaded");
				
			}
			else
				log.error("File not found: application.properties");
						
		} catch (FileNotFoundException e) {
			throw new DatabaseConfigurationException(e,"File application.properties not found.");
		} catch (IOException e) {
			throw new DatabaseConfigurationException(e,"I/O input error.");
		}
	}

	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		jpaVendorAdapter.setShowSql(showSql);
		jpaVendorAdapter.setGenerateDdl(generateSql);	
		jpaVendorAdapter.setDatabase(databaseType);
		return jpaVendorAdapter;
	}

	@Bean
	public HibernateExceptionTranslator hibernateExceptionTranslator() {
		return new HibernateExceptionTranslator();
	}

	@Bean(name = "datasource")
	public DriverManagerDataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(databaseDriver);
		dataSource.setUrl(databaseUrl);
		dataSource.setUsername(databaseUser);
		dataSource.setPassword(databasePassword);
		return dataSource;
	}

	@Bean(name = "webEntityManager")
    public EntityManager entityManager() {
        return entityManagerFactory().createEntityManager();
    }

	@Bean(name = "webEntityManagerFactory")
	public EntityManagerFactory entityManagerFactory() {
	    LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();
	    lef.setDataSource(dataSource());
	    lef.setJpaVendorAdapter(jpaVendorAdapter());
	    lef.setPackagesToScan("org.cybertop.back.app.model");
	    lef.setPersistenceUnitName("webPersistenceUnit");
	    lef.afterPropertiesSet();
	    return lef.getObject();
	}

    @Bean(name = "webTransactionManager")
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager(entityManagerFactory());
    }

}
