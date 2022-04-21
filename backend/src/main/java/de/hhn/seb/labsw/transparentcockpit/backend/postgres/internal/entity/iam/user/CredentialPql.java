package de.hhn.seb.labsw.transparentcockpit.backend.postgres.internal.entity.iam.user;

import javax.persistence.*;

/**
 * Credential Postgres Object.
 */
@Entity(name = "labSW_Credential")
public class CredentialPql {
    @Id
    @Column
    private String username;

    @Column
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(updatable = false)
    private UserPql user;


    // Hibernate
    public CredentialPql() {
    }

    public CredentialPql(String username, String password, UserPql user) {
        this.username = username;
        this.password = password;
        this.user = user;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserPql getUser() {
        return user;
    }

}
