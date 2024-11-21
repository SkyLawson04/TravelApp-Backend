package com.travelapp.backend.common.database;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
@ConfigurationProperties(prefix = "primary.mongodb")
public class PrimaryMongoConfig {
    @Value("${primary.mongodb.uri}")
    private String uri;
    @Value("${primary.mongodb.database}")
    private String database;

    private MongoClient client;

    @Bean(name = "mongoTemplate")
    public MongoTemplate getMongoTemplate() {
        client = MongoClients.create(uri);
        return new MongoTemplate(client, database);
    }

    @PreDestroy
    public void cleanup() {
        if (client != null) {
            client.close();
        }
    }
}