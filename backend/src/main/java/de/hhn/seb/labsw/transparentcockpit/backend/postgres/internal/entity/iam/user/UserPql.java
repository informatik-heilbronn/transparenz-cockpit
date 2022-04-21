package de.hhn.seb.labsw.transparentcockpit.backend.postgres.internal.entity.iam.user;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Locale;
import java.util.UUID;

/**
 * User Postgres Object.
 */
@Entity(name = "labSW_User")
public class UserPql {

    @Id
    @Column(updatable = false)
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column
    private String name;

    @Column
    private String lastName;

    @Column
    private String email;

    @Column
    private Locale language;


    // Hibernate
    public UserPql() {
    }

    public UserPql(String name, String lastName, String email, Locale language) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.language = language;
    }


    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Locale getLanguage() {
        return language;
    }

    public void setLanguage(Locale language) {
        this.language = language;
    }
}
