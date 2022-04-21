package de.hhn.seb.labsw.transparentcockpit.backend.postgres.repository;

import de.hhn.seb.labsw.transparentcockpit.backend.postgres.config.PostgresConfig;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Base Postgres Repository.
 */
public abstract class RepositoryPql {

    public EntityManagerFactory init(String persistenceUnitName, PostgresConfig postgresConfig) {
        return Persistence.createEntityManagerFactory(persistenceUnitName, postgresConfig.getProperties());
    }

    public void destroy(EntityManagerFactory entityManagerFactory) {
        entityManagerFactory.close();
    }

    public EntityManager open(EntityManagerFactory entityManagerFactory) {
        return entityManagerFactory.createEntityManager();
    }

    public void close(EntityManager entityManager) {
        entityManager.close();
    }
}