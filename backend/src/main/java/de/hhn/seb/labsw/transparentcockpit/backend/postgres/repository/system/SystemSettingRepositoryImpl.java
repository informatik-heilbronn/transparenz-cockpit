package de.hhn.seb.labsw.transparentcockpit.backend.postgres.repository.system;

import de.hhn.seb.labsw.transparentcockpit.backend.exceptions.reporitory.NotFoundException;
import de.hhn.seb.labsw.transparentcockpit.backend.models.system.SystemSetting;
import de.hhn.seb.labsw.transparentcockpit.backend.postgres.config.PostgresConfig;
import de.hhn.seb.labsw.transparentcockpit.backend.postgres.internal.mapper.SystemSettingsPqlMapper;
import de.hhn.seb.labsw.transparentcockpit.backend.repository.system.SystemSettingRepository;

/**
 * SystemSettingRepository of ProjectReportRepository.
 */
public class SystemSettingRepositoryImpl implements SystemSettingRepository {

    private final SystemSettingRepositoryPql systemSettingRepositoryPql;


    public SystemSettingRepositoryImpl(PostgresConfig postgresConfig) {
        systemSettingRepositoryPql = new SystemSettingRepositoryPql(postgresConfig);
    }

    @Override
    public void saveSystemConfiguration(SystemSetting systemConfiguration) {
        systemSettingRepositoryPql.saveSystemConfiguration(
                SystemSettingsPqlMapper.toSystemSettingsPql(systemConfiguration));
    }

    @Override
    public void updateSystemConfiguration(String key, String updatedValue)
            throws NotFoundException {
        systemSettingRepositoryPql.updateSystemConfiguration(key, updatedValue);
    }

    @Override
    public SystemSetting getSystemConfigurationByKey(String key)
            throws NotFoundException {
        return SystemSettingsPqlMapper.toSystemSetting(
                systemSettingRepositoryPql.getSystemConfigurationByKey(key));
    }
}
