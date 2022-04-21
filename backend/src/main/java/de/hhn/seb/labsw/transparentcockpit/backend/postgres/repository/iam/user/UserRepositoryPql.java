package de.hhn.seb.labsw.transparentcockpit.backend.postgres.repository.iam.user;


import de.hhn.seb.labsw.transparentcockpit.backend.exceptions.reporitory.NotFoundException;
import de.hhn.seb.labsw.transparentcockpit.backend.postgres.config.PostgresConfig;
import de.hhn.seb.labsw.transparentcockpit.backend.postgres.internal.entity.iam.user.UserPql;
import de.hhn.seb.labsw.transparentcockpit.backend.postgres.repository.RepositoryPql;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.RollbackException;
import java.util.UUID;

/**
 * Postgres Implementation User Repository.
 */
public class UserRepositoryPql extends RepositoryPql {

    private final PostgresConfig repositoryConfig;
    private EntityManagerFactory entityManagerFactory;


    public UserRepositoryPql(PostgresConfig repositoryConfig) {
        this.repositoryConfig = repositoryConfig;
        setupEntityManagerFactory();
    }


    private void setupEntityManagerFactory() {
        entityManagerFactory = init("LabSW", repositoryConfig);
    }

    public UserPql saveUser(UserPql userPql) {
        EntityManager manager = null;
        UserPql resultUserPql = null;

        try {

            try {
                manager = open(entityManagerFactory);
            } catch (IllegalStateException e) {
                setupEntityManagerFactory();
                return saveUser(userPql);
            }

            manager.getTransaction().begin();
            resultUserPql = manager.merge(userPql);
            manager.getTransaction().commit();

        } catch (RollbackException e) {
            manager.getTransaction().rollback();
        } finally {
            close(manager);
        }

        return resultUserPql;
    }

    public UserPql getUserById(UUID userId) {
        EntityManager manager = null;
        UserPql resultUserPql = null;

        try {

            try {
                manager = open(entityManagerFactory);
            } catch (IllegalStateException e) {
                setupEntityManagerFactory();
                return getUserById(userId);
            }

            manager.getTransaction().begin();
            resultUserPql = manager.createQuery("SELECT user FROM labSW_User user "
                            + "WHERE user.id = :userId", UserPql.class)
                    .setParameter("userId", userId)
                    .getSingleResult();
            manager.getTransaction().commit();

        } catch (NoResultException e) {
            throw new NotFoundException("There is no User with the UserId: " + userId);
        } catch (RollbackException e) {
            manager.getTransaction().rollback();
        } finally {
            close(manager);
        }

        return resultUserPql;
    }

    public UserPql updateUser(UUID userId, UserPql updatedUserPql) {
        EntityManager manager = null;
        UserPql resultUserPql = null;

        try {

            try {
                manager = open(entityManagerFactory);
            } catch (IllegalStateException e) {
                setupEntityManagerFactory();
                return updateUser(userId, updatedUserPql);
            }

            manager.getTransaction().begin();
            resultUserPql = manager.createQuery("SELECT user FROM labSW_User user "
                            + "WHERE user.id = :userId", UserPql.class)
                    .setParameter("userId", userId)
                    .getSingleResult();

            resultUserPql.setName(updatedUserPql.getName());
            resultUserPql.setLastName(updatedUserPql.getLastName());
            resultUserPql.setEmail(updatedUserPql.getEmail());
            resultUserPql.setLanguage(updatedUserPql.getLanguage());

            manager.merge(resultUserPql);
            manager.getTransaction().commit();

        } catch (NoResultException e) {
            throw new NotFoundException("There is no User with the UserId: " + userId);
        } catch (RollbackException e) {
            manager.getTransaction().rollback();
        } finally {
            close(manager);
        }

        return resultUserPql;
    }
}
