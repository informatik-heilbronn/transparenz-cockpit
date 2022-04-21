package de.hhn.seb.labsw.transparentcockpit.backend.postgres.internal.entity.system;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * SystemSetting Postgres Object.
 */
@Entity(name = "labSW_SystemSettings")
public class SystemSettingsPql {
    @Id
    @Column
    private String key;

    @Column
    private String value;


    // Hibernate
    public SystemSettingsPql() {
    }

    public SystemSettingsPql(String key, String value) {
        this.key = key;
        this.value = value;
    }


    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}



