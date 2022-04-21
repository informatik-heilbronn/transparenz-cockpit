package de.hhn.seb.labsw.transparentcockpit.backend.postgres.config;

import java.util.Map;
import java.util.Properties;


public class PostgresConfigLoader {

    private final Properties properties;


    public PostgresConfigLoader(Properties properties) {
        this.properties = properties;

        check();
    }


    private void check() {
        int postgresConfigCount = 0;
        for (Map.Entry<Object, Object> property : properties.entrySet()) {
            if (property.getKey().toString().startsWith("postgres")) {
                postgresConfigCount++;
            }
        }

        if (postgresConfigCount != 6) {
            throw new IllegalArgumentException("Not all Postgres Configurations are configured");
        }
    }

    public PostgresConfig loadConfig() {
        return new PostgresConfig(
                "jdbc:postgresql://" + properties.getProperty("postgres.url") + ":"
                        + properties.getProperty("postgres.port") + "/" + properties.getProperty("postgres.database"),
                properties.getProperty("postgres.jdbcSchema"), properties.getProperty("postgres.jdbcUser"),
                properties.getProperty("postgres.jdbcPassword")
        );
    }
}
