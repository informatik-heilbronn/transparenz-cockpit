package de.hhn.seb.labsw.transparentcockpit.backend.mongo.repositorys;

import com.mongodb.client.MongoClients;
import de.hhn.seb.labsw.transparentcockpit.backend.mongo.config.MongoConfig;
import dev.morphia.Datastore;
import dev.morphia.Morphia;

/**
 * Base Mongo Repository.
 */
public abstract class RepositoryMdb {

    public Datastore init(MongoConfig mongoConfig) {
        String connectionString = "mongodb://" + mongoConfig.getUser() + ":" + mongoConfig.getPassword()
                + "@" + mongoConfig.getUrl() + ":" + mongoConfig.getPort() + "/";

        Datastore datastore = Morphia.createDatastore(MongoClients.create(connectionString), mongoConfig.getDatabase());
        datastore.getMapper().mapPackage("de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.entity");
        datastore.ensureIndexes();

        return datastore;
    }

}