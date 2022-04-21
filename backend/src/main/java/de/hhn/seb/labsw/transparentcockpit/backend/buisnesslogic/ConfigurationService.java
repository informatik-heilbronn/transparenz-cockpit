package de.hhn.seb.labsw.transparentcockpit.backend.buisnesslogic;

import de.hhn.seb.labsw.transparentcockpit.backend.configloader.ConfigLoader;
import de.hhn.seb.labsw.transparentcockpit.backend.mongo.config.MongoConfig;
import de.hhn.seb.labsw.transparentcockpit.backend.mongo.config.MongoConfigLoader;
import de.hhn.seb.labsw.transparentcockpit.backend.postgres.config.PostgresConfig;
import de.hhn.seb.labsw.transparentcockpit.backend.postgres.config.PostgresConfigLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Configuration Service.
 * Handles Loading the Config from the File and Distributing it.
 */
public class ConfigurationService {
    private final ConfigLoader configLoader;

    /**
     * Create the Config Loader and Loads the Config.
     *
     * @throws RuntimeException If there is no Config or not a valid Config.
     */
    public ConfigurationService() throws RuntimeException {

        configLoader = new ConfigLoader();
    }


    /**
     * Create the MongoDB Configuration.
     *
     * @return MongoDB Configuration
     */
    public MongoConfig getMongoConfig() {

        MongoConfigLoader mongoConfigLoader = new MongoConfigLoader(configLoader.getProperties());
        return mongoConfigLoader.loadConfig();
    }

    /**
     * Create the PostgreSQL Configuration.
     *
     * @return PostgreSQL Configuration
     */
    public PostgresConfig getPostgresConfig() {

        PostgresConfigLoader postgresConfigLoader = new PostgresConfigLoader(configLoader.getProperties());
        return postgresConfigLoader.loadConfig();
    }
}
