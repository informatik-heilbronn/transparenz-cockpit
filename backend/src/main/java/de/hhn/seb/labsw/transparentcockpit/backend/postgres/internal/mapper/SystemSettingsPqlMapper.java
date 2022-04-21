package de.hhn.seb.labsw.transparentcockpit.backend.postgres.internal.mapper;

import de.hhn.seb.labsw.transparentcockpit.backend.models.system.SystemSetting;
import de.hhn.seb.labsw.transparentcockpit.backend.postgres.internal.entity.system.SystemSettingsPql;


public class SystemSettingsPqlMapper {

    public static SystemSettingsPql toSystemSettingsPql(SystemSetting systemSetting) {
        return new SystemSettingsPql(systemSetting.getKey(), systemSetting.getValue());
    }

    public static SystemSetting toSystemSetting(SystemSettingsPql systemSettingsPql) {
        return new SystemSetting(systemSettingsPql.getKey(), systemSettingsPql.getValue());
    }
}
