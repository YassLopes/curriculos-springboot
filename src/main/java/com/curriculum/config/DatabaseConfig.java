package com.curriculum.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@Profile("production")
public class DatabaseConfig {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseConfig.class);

    @Value("${SPRING_DATASOURCE_URL}")
    private String dbUrl;

    @Value("${SPRING_DATASOURCE_USERNAME}")
    private String dbUsername;

    @Value("${SPRING_DATASOURCE_PASSWORD}")
    private String dbPassword;

    @Bean
    public HikariDataSource dataSource() {
        logger.info("Database URL: {}", dbUrl);
        logger.info("Database Username: {}", dbUsername);
        
        HikariConfig config = new HikariConfig();
        
        // Ensure the URL starts with jdbc:postgresql://
        String jdbcUrl = dbUrl.startsWith("jdbc:postgresql://") ? dbUrl : "jdbc:postgresql://" + dbUrl;
        config.setJdbcUrl(jdbcUrl);
        
        config.setUsername(dbUsername);
        config.setPassword(dbPassword);
        config.setDriverClassName("org.postgresql.Driver");
        config.setMaximumPoolSize(5);
        config.setMinimumIdle(2);
        config.setConnectionTimeout(30000);
        config.setIdleTimeout(300000);
        
        return new HikariDataSource(config);
    }
} 