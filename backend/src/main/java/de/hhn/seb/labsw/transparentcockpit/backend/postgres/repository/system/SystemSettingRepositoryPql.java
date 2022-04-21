package de.hhn.seb.labsw.transparentcockpit.backend.postgres.repository.system;

import de.hhn.seb.labsw.transparentcockpit.backend.exceptions.reporitory.NotFoundException;
import de.hhn.seb.labsw.transparentcockpit.backend.postgres.config.PostgresConfig;
import de.hhn.seb.labsw.transparentcockpit.backend.postgres.internal.entity.system.SystemSettingsPql;
import de.hhn.seb.labsw.transparentcockpit.backend.postgres.repository.RepositoryPql;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.RollbackException;

/**
 * Postgres Implementation SystemSettings Repository.
 */
public class SystemSettingRepositoryPql extends RepositoryPql {

    private final PostgresConfig repositoryConfig;
    private EntityManagerFactory entityManagerFactory;

    public SystemSettingRepositoryPql(PostgresConfig repositoryConfig) {
        this.repositoryConfig = repositoryConfig;
        setupEntityManagerFactory();
    }

    private void setupEntityManagerFactory() {
        entityManagerFactory = init("LabSW", repositoryConfig);
    }


    public SystemSettingsPql saveSystemConfiguration(SystemSettingsPql systemSettingsPql) {
        EntityManager manager = null;
        SystemSettingsPql resultSystemSettingsPql = null;

        try {

            try {
                manager = open(entityManagerFactory);
            } catch (IllegalStateException e) {
                setupEntityManagerFactory();
                return saveSystemConfiguration(systemSettingsPql);
            }

            manager.getTransaction().begin();
            resultSystemSettingsPql = manager.merge(systemSettingsPql);
            manager.getTransaction().commit();

        } catch (RollbackException e) {
            manager.getTransaction().rollback();
        } finally {
            close(manager);
        }

        return resultSystemSettingsPql;
    }

    public SystemSettingsPql updateSystemConfiguration(String key, String updatedValue)
            throws NotFoundException {
        EntityManager manager = null;
        SystemSettingsPql resultSystemSettingsPql = null;

        try {

            try {
                manager = open(entityManagerFactory);
            } catch (IllegalStateException e) {
                setupEntityManagerFactory();
                return updateSystemConfiguration(key, updatedValue);
            }

            manager.getTransaction().begin();
            resultSystemSettingsPql = manager.createQuery("SELECT settings FROM labSW_SystemSettings settings "
                            + "WHERE settings.id = :key", SystemSettingsPql.class)
                    .setParameter("key", key)
                    .getSingleResult();

            resultSystemSettingsPql.setValue(updatedValue);

            resultSystemSettingsPql = manager.merge(resultSystemSettingsPql);
            manager.getTransaction().commit();

        } catch (NoResultException e) {
            throw new NotFoundException("There is no SystemSettings with the Key: " + key);
        } catch (RollbackException e) {
            manager.getTransaction().rollback();
        } finally {
            close(manager);
        }

        return resultSystemSettingsPql;
    }

    public SystemSettingsPql getSystemConfigurationByKey(String key)
            throws NotFoundException {
        EntityManager manager = null;
        SystemSettingsPql resultSystemSettingsPql = null;

        try {

            try {
                manager = open(entityManagerFactory);
            } catch (IllegalStateException e) {
                setupEntityManagerFactory();
                return getSystemConfigurationByKey(key);
            }

            manager.getTransaction().begin();
            resultSystemSettingsPql = manager.createQuery("SELECT settings FROM labSW_SystemSettings settings "
                            + "WHERE settings.id = :key", SystemSettingsPql.class)
                    .setParameter("key", key)
                    .getSingleResult();

            manager.getTransaction().commit();

        } catch (NoResultException e) {
            throw new NotFoundException("There is no SystemSettings with the Key: " + key);
        } catch (RollbackException e) {
            manager.getTransaction().rollback();
        } finally {
            close(manager);
        }

        return resultSystemSettingsPql;
    }
}
