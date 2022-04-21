package de.hhn.seb.labsw.transparentcockpit.backend.buisnesslogic;

import de.hhn.seb.labsw.transparentcockpit.backend.exceptions.reporitory.NotFoundException;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.template.ProjectReportTemplate;
import de.hhn.seb.labsw.transparentcockpit.backend.mongo.config.MongoConfig;
import de.hhn.seb.labsw.transparentcockpit.backend.mongo.repositorys.project.template.ProjectReportTemplateRepositoryImpl;
import de.hhn.seb.labsw.transparentcockpit.backend.repository.project.template.ProjectReportTemplateRepository;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ProjectReportTemplate Service.
 * Handles all ProjectReportTemplate Actions (Create, Read).
 */
public class ProjectReportTemplateService {

    private final ProjectReportTemplateRepository projectReportTemplateRepository;

    /**
     * Constructor.
     *
     * @param mongoConfig MongoDB Configuration
     */
    public ProjectReportTemplateService(MongoConfig mongoConfig) {
        projectReportTemplateRepository = new ProjectReportTemplateRepositoryImpl(mongoConfig);
    }


    /**
     * Save the ProjectReportTemplate.
     *
     * @param projectReportTemplate ProjectReportTemplate to Save
     */
    public void saveProjectReportTemplate(ProjectReportTemplate projectReportTemplate) {
        projectReportTemplateRepository.saveProjectReportTemplate(projectReportTemplate);

    }

    /**
     * Get ProjectReportTemplate by ID.
     *
     * @param projectReportTemplateId ProjectReportTemplate ID
     * @return the ProjectReportTemplate
     * @throws NotFoundException if on ProjectReportTemplate has been found with the given ID
     */
    public ProjectReportTemplate getProjectReportTemplateById(UUID projectReportTemplateId) throws NotFoundException {
        return projectReportTemplateRepository.getProjectReportTemplateById(projectReportTemplateId);
    }

}
