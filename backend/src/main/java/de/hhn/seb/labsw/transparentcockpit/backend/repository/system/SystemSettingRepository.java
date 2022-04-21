package de.hhn.seb.labsw.transparentcockpit.backend.repository.system;

import de.hhn.seb.labsw.transparentcockpit.backend.exceptions.reporitory.NotFoundException;
import de.hhn.seb.labsw.transparentcockpit.backend.models.system.SystemSetting;

/**
 * SystemSettings Repository.
 */
public interface SystemSettingRepository {

    /**
     * Save SystemSettings.
     *
     * @param systemConfiguration SystemSettings
     */
    void saveSystemConfiguration(SystemSetting systemConfiguration);

    /**
     * Updated SystemSettings.
     *
     * @param key          Key of the SystemSetting
     * @param updatedValue Updated Value of the SystemSetting
     * @throws NotFoundException if SystemSetting has been found
     */
    void updateSystemConfiguration(String key, String updatedValue)
            throws NotFoundException;

    /**
     * Get SystemSettings by Key.
     *
     * @param key Key of the SystemSetting
     * @return SystemSetting
     * @throws NotFoundException if SystemSetting has been found
     */
    SystemSetting getSystemConfigurationByKey(String key)
            throws NotFoundException;
}
