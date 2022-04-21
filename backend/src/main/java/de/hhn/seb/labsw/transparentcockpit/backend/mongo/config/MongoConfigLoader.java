package de.hhn.seb.labsw.transparentcockpit.backend.mongo.config;

import java.util.Map;
import java.util.Properties;

public class MongoConfigLoader {
    private final Properties properties;


    public MongoConfigLoader(Properties properties) {
        this.properties = properties;

        check();
    }


    private void check() {
        int mongoConfigCount = 0;
        for (Map.Entry<Object, Object> property : properties.entrySet()) {
            if (property.getKey().toString().startsWith("mongo")) {
                mongoConfigCount++;
            }
        }

        if (mongoConfigCount != 5) {
            throw new IllegalArgumentException("Not all Mongo Configurations are configured");
        }
    }

    public MongoConfig loadConfig() {
        return new MongoConfig(properties.getProperty("mongo.url"), Integer.parseInt(properties.getProperty("mongo.port")),
                properties.getProperty("mongo.database"),
                properties.getProperty("mongo.user"), properties.getProperty("mongo.password")
        );
    }
}
