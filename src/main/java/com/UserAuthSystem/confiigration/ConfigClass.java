package com.UserAuthSystem.confiigration;

import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
public class ConfigClass {

    @Bean
    PlatformTransactionManager transationalManager() {
        return new MongoTransactionManager();
    }
}
