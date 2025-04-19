package com.curriculum.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import javax.sql.DataSource;

@Configuration
@Profile("production")
public class DatabaseConfig {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseConfig.class);
    
    @Bean
    public DataSource dataSource() {
        String dbUrl = System.getenv("DATABASE_URL");
        String dbUsername = System.getenv("DATABASE_USERNAME");
        String dbPassword = System.getenv("DATABASE_PASSWORD");
        
        logger.info("Database URL: {}", dbUrl);
        logger.info("Database Username: {}", dbUsername);
        
        // Ensure the URL starts with jdbc:postgresql://
        if (dbUrl != null && !dbUrl.startsWith("jdbc:postgresql://")) {
            dbUrl = "jdbc:postgresql://" + dbUrl;
        }
        
        return DataSourceBuilder.create()
            .driverClassName("org.postgresql.Driver")
            .url(dbUrl)
            .username(dbUsername)
            .password(dbPassword)
            .build();
    }
} 