package de.hhn.seb.labsw.transparentcockpit.backend.startup;

import de.hhn.seb.labsw.transparentcockpit.backend.buisnesslogic.ConfigurationService;
import de.hhn.seb.labsw.transparentcockpit.backend.buisnesslogic.ProjectReportTemplateService;
import de.hhn.seb.labsw.transparentcockpit.backend.buisnesslogic.SystemSettingsService;
import de.hhn.seb.labsw.transparentcockpit.backend.exceptions.reporitory.NotFoundException;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.template.ProjectReportTemplate;
import de.hhn.seb.labsw.transparentcockpit.backend.models.system.SystemSetting;

import java.util.UUID;

/**
 * Startup Checks if DEFAULT_TEMPLATE_ID exists and is accessible.
 */
public class Startup {


    private final SystemSettingsService systemSettingsService;
    private final ProjectReportTemplateService projectReportTemplateService;

    /**
     * Constructor.
     */
    public Startup() {
        ConfigurationService configurationService = new ConfigurationService();
        systemSettingsService = new SystemSettingsService(configurationService.getPostgresConfig());
        projectReportTemplateService = new ProjectReportTemplateService(configurationService.getMongoConfig());

        String defaultTemplateId;
        try {
            defaultTemplateId = systemSettingsService.loadSetting("DEFAULT_TEMPLATE_ID").getValue();
        } catch (NotFoundException e) {
            setup();
            return;
        }

        try {
            projectReportTemplateService.getProjectReportTemplateById(UUID.fromString(defaultTemplateId));
        } catch (NotFoundException e) {
            setup();
        }
    }

    private void setup() {
        ProjectReportTemplate defaultProjectReportTemplate = new DefaultProjectReportTemplate().getProjectReportTemplate();

        projectReportTemplateService.saveProjectReportTemplate(defaultProjectReportTemplate);

        SystemSetting systemSetting = new SystemSetting("DEFAULT_TEMPLATE_ID",
                defaultProjectReportTemplate.getTemplateId().toString());
        systemSettingsService.saveSetting(systemSetting);
    }
}
