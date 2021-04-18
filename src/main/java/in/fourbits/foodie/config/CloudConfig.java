package in.fourbits.foodie.config;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariDataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.cloud.CloudException;
import org.springframework.cloud.app.ApplicationInstanceInfo;
import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.cloud.service.ServiceConnectorCreationException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("cloud")
public class CloudConfig extends AbstractCloudConfig {

    @Bean()
    public ApplicationInstanceInfo applicationInfo() {
        System.out.println("#### Instance Id - " + cloud().getApplicationInstanceInfo().getInstanceId());
        return cloud().getApplicationInstanceInfo();
    }

    @Bean
    public DataSource dataSource(@Value("${vcap.services.foodie-hana-service.credentials.url}") final String url,
            @Value("${vcap.services.foodie-hana-service.credentials.user}") final String user,
            @Value("${vcap.services.foodie-hana-service.credentials.password}") final String password) {
        try {
            // https://stackoverflow.com/questions/54670970/sap-dbtech-jdbc-4321-only-secure-connections-are-allowed
            // return connectionFactory().dataSource();
            return DataSourceBuilder.create().type(HikariDataSource.class)
                    .driverClassName(com.sap.db.jdbc.Driver.class.getName()).url(url).username(user).password(password)
                    .build();
        } catch (CloudException | ServiceConnectorCreationException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
