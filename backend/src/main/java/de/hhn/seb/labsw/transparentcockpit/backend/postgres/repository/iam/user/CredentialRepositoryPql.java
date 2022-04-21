package de.hhn.seb.labsw.transparentcockpit.backend.postgres.repository.iam.user;


import de.hhn.seb.labsw.transparentcockpit.backend.exceptions.reporitory.NotFoundException;
import de.hhn.seb.labsw.transparentcockpit.backend.postgres.config.PostgresConfig;
import de.hhn.seb.labsw.transparentcockpit.backend.postgres.internal.entity.iam.user.CredentialPql;
import de.hhn.seb.labsw.transparentcockpit.backend.postgres.repository.RepositoryPql;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.RollbackException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Postgres Implementation Credential Repository.
 */
public class CredentialRepositoryPql extends RepositoryPql {

  private final PostgresConfig repositoryConfig;
  private EntityManagerFactory entityManagerFactory;

  public CredentialRepositoryPql(PostgresConfig repositoryConfig) {
    this.repositoryConfig = repositoryConfig;
    setupEntityManagerFactory();
  }

  private void setupEntityManagerFactory() {
    entityManagerFactory = init("LabSW", repositoryConfig);
  }

  public void saveCredential(CredentialPql credentialPql) {
    EntityManager manager = null;

    try {

      try {
        manager = open(entityManagerFactory);
      } catch (IllegalStateException e) {
        setupEntityManagerFactory();
        saveCredential(credentialPql);
        return;
      }

      manager.getTransaction().begin();
      manager.merge(credentialPql);
      manager.getTransaction().commit();

    } catch (RollbackException e) {
      manager.getTransaction().rollback();
    } finally {
      close(manager);
    }
  }

  public CredentialPql getCredentialId(String username, String password) {
    EntityManager manager = null;
    CredentialPql resultCredentialPql = null;

    try {

      try {
        manager = open(entityManagerFactory);
      } catch (IllegalStateException e) {
        setupEntityManagerFactory();
        return getCredentialId(username, password);
      }

      manager.getTransaction().begin();
      resultCredentialPql = manager.createQuery("SELECT credential FROM labSW_Credential credential "
              + "WHERE credential.username = :username AND credential.password = :password", CredentialPql.class)
          .setParameter("username", username)
          .setParameter("password", password)
          .getSingleResult();
      manager.getTransaction().commit();

    } catch (NoResultException e) {
      throw new NotFoundException("There is no Credentials with the username and Password: " + username + ", "
          + password);
    } catch (RollbackException e) {
      manager.getTransaction().rollback();
    } finally {
      close(manager);
    }

    return resultCredentialPql;
  }

  public CredentialPql updateCredential(String username, String password,
                                        String newUsername, String newPassword) {
    EntityManager manager = null;
    CredentialPql resultCredentialPql = null;

    try {

      try {
        manager = open(entityManagerFactory);
      } catch (IllegalStateException e) {
        setupEntityManagerFactory();
        return updateCredential(username, password, newUsername, newPassword);
      }

      manager.getTransaction().begin();
      resultCredentialPql = manager.createQuery("SELECT credential FROM labSW_Credential credential "
              + "WHERE credential.username = :username AND credential.password = :password", CredentialPql.class)
          .setParameter("username", username)
          .setParameter("password", password)
          .getSingleResult();

      resultCredentialPql.setUsername(newUsername);
      resultCredentialPql.setPassword(newPassword);

      manager.merge(resultCredentialPql);
      manager.getTransaction().commit();

    } catch (NoResultException e) {
      throw new NotFoundException("There is no Credentials with the username and Password: " + username + ", "
          + password);
    } catch (RollbackException e) {
      manager.getTransaction().rollback();
    } finally {
      close(manager);
    }

    return resultCredentialPql;
  }

}
