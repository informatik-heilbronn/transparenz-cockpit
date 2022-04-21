package de.hhn.seb.labsw.transparentcockpit.backend.postgres.config;

import java.util.HashMap;
import java.util.Map;


public class PostgresConfig {

    private final String jdbcUrl;
    private final String jdbcSchema;

    private final String jdbcUser;
    private final String jdbcPassword;

    public PostgresConfig(String jdbcUrl, String jdbcSchema, String jdbcUser, String jdbcPassword) {
        this.jdbcUrl = jdbcUrl;
        this.jdbcSchema = jdbcSchema;
        this.jdbcUser = jdbcUser;
        this.jdbcPassword = jdbcPassword;
    }


    public Map<String, String> getProperties() {
        Map<String, String> properties = new HashMap<>();

        properties.put("javax.persistence.jdbc.driver", "org.postgresql.Driver");

        properties.put("javax.persistence.jdbc.url", jdbcUrl);
        properties.put("hibernate.default_schema", jdbcSchema);
        properties.put("javax.persistence.jdbc.user", jdbcUser);
        properties.put("javax.persistence.jdbc.password", jdbcPassword);

        return properties;
    }
}
