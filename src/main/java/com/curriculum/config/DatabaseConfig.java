package com.curriculum.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import javax.sql.DataSource;

@Configuration
@Profile("production")
public class DatabaseConfig {
    
    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create()
            .driverClassName("org.postgresql.Driver")
            .url(System.getenv("DATABASE_URL"))
            .username(System.getenv("DATABASE_USERNAME"))
            .password(System.getenv("DATABASE_PASSWORD"))
            .build();
    }
} 