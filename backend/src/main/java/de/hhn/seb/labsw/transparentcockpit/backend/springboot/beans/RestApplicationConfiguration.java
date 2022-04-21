package de.hhn.seb.labsw.transparentcockpit.backend.springboot.beans;

import de.hhn.seb.labsw.transparentcockpit.backend.buisnesslogic.ConfigurationService;
import de.hhn.seb.labsw.transparentcockpit.backend.buisnesslogic.ProjectReportService;
import de.hhn.seb.labsw.transparentcockpit.backend.buisnesslogic.ProjectReportTemplateService;
import de.hhn.seb.labsw.transparentcockpit.backend.buisnesslogic.SystemSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RestApplicationConfiguration {


    @Bean
    @ConditionalOnMissingBean
    public ConfigurationService configurationService() {
        return new ConfigurationService();
    }


    @Bean
    @ConditionalOnMissingBean
    public ProjectReportService projectReportService(@Autowired
                                                             ConfigurationService configurationService) {
        return new ProjectReportService(configurationService.getMongoConfig());
    }

    @Bean
    @ConditionalOnMissingBean
    public ProjectReportTemplateService projectReportTemplateService(@Autowired
                                                                             ConfigurationService configurationService) {
        return new ProjectReportTemplateService(configurationService.getMongoConfig());
    }


    @Bean
    @ConditionalOnMissingBean
    public SystemSettingsService systemSettingsService(@Autowired
                                                               ConfigurationService configurationService) {
        return new SystemSettingsService(configurationService.getPostgresConfig());
    }


}
