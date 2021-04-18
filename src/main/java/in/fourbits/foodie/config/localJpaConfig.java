package in.fourbits.foodie.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.eclipse.persistence.config.PersistenceUnitProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.persistenceunit.DefaultPersistenceUnitManager;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;

@Configuration
@Profile("local")
public class localJpaConfig {

     @Autowired
    DataSource dataSource;

    @Bean
    protected EclipseLinkJpaVendorAdapter createJpaVendorAdapter() {
        EclipseLinkJpaVendorAdapter adapter = new EclipseLinkJpaVendorAdapter();
			adapter.setDatabase(Database.H2);
			adapter.setShowSql(true);
			adapter.setGenerateDdl(true);
			return adapter;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
            JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean lef = builder.dataSource(dataSource).packages("").build();
        lef.setJpaVendorAdapter(jpaVendorAdapter);
        lef.setJpaPropertyMap(getVendorProperties());
        DefaultPersistenceUnitManager dpm;
        return lef;
    }

    protected Map<String, Object> getVendorProperties() {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put(PersistenceUnitProperties.WEAVING, "false");
        return map;
    }

    @Bean
	public JdbcTemplate jdbcTemplate(DataSource ds) {
		return new JdbcTemplate(ds);
    }
    

	@Bean(destroyMethod = "shutdown")
	public DataSource h2Embedded() {
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).
				setName("H2DB").build();
	}
    
}
