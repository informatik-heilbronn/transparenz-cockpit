package de.hhn.seb.labsw.transparentcockpit.backend.repository.project.template;

import de.hhn.seb.labsw.transparentcockpit.backend.exceptions.reporitory.NotFoundException;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.template.ProjectReportTemplate;

import java.util.UUID;

/**
 * ProjectReportTemplate Repository.
 */
public interface ProjectReportTemplateRepository {

    /**
     * Save the PProjectReportTemplate.
     *
     * @param projectReportTemplate ProjectReportTemplate
     */
    void saveProjectReportTemplate(ProjectReportTemplate projectReportTemplate);

    /**
     * Get the ProjectReportTemplate by ID.
     *
     * @param projectReportTemplateId ProjectReportTemplate ID
     * @return ProjectReportTemplate
     * @throws NotFoundException if no ProjectReportTemplate has been found
     */
    ProjectReportTemplate getProjectReportTemplateById(UUID projectReportTemplateId) throws NotFoundException;

}
