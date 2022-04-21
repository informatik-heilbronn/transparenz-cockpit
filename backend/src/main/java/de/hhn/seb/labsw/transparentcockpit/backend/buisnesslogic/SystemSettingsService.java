package de.hhn.seb.labsw.transparentcockpit.backend.buisnesslogic;

import de.hhn.seb.labsw.transparentcockpit.backend.exceptions.reporitory.NotFoundException;
import de.hhn.seb.labsw.transparentcockpit.backend.models.system.SystemSetting;
import de.hhn.seb.labsw.transparentcockpit.backend.postgres.config.PostgresConfig;
import de.hhn.seb.labsw.transparentcockpit.backend.postgres.repository.system.SystemSettingRepositoryImpl;
import de.hhn.seb.labsw.transparentcockpit.backend.repository.system.SystemSettingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * SystemSettings Service.
 * Handles all SystemSettings Actions (Create, Read, Update).
 */
public class SystemSettingsService {
    private final SystemSettingRepository systemSettingRepository;

    /**
     * Constructor.
     *
     * @param postgresConfig PostgreSQL Configuration
     */
    public SystemSettingsService(PostgresConfig postgresConfig) {
        systemSettingRepository = new SystemSettingRepositoryImpl(postgresConfig);
    }

    /**
     * Save the SystemSettings.
     *
     * @param systemSetting SystemSettings to Save
     */
    public void saveSetting(SystemSetting systemSetting) {
        systemSettingRepository.saveSystemConfiguration(systemSetting);
    }

    /**
     * Updated the SystemSettings.
     *
     * @param systemSetting Updated SystemSettings
     */
    public void updateSetting(SystemSetting systemSetting) {
        systemSettingRepository.updateSystemConfiguration(systemSetting.getKey(), systemSetting.getValue());
    }

    /**
     * Get SystemSettings by Key.
     *
     * @param key SystemSettings Key
     * @return the SystemSettings
     * @throws NotFoundException if no SystemSetting has been found with the given Key
     */
    public SystemSetting loadSetting(String key) throws NotFoundException {
        return systemSettingRepository.getSystemConfigurationByKey(key);
    }
}
