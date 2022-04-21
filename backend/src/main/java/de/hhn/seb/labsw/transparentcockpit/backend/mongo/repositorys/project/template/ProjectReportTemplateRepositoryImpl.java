package de.hhn.seb.labsw.transparentcockpit.backend.mongo.repositorys.project.template;

import de.hhn.seb.labsw.transparentcockpit.backend.exceptions.reporitory.NotFoundException;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.template.ProjectReportTemplate;
import de.hhn.seb.labsw.transparentcockpit.backend.mongo.config.MongoConfig;
import de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.mapper.ProjectReportTemplateMbdMapper;
import de.hhn.seb.labsw.transparentcockpit.backend.repository.project.template.ProjectReportTemplateRepository;

import java.util.UUID;

/**
 * Implementation of ProjectReportTemplateRepository.
 */
public class ProjectReportTemplateRepositoryImpl implements ProjectReportTemplateRepository {

    private final ProjectReportTemplateRepositoryMdb projectReportTemplateRepositoryMdb;


    public ProjectReportTemplateRepositoryImpl(MongoConfig mongoConfig) {
        projectReportTemplateRepositoryMdb = new ProjectReportTemplateRepositoryMdb(mongoConfig);
    }


    @Override
    public void saveProjectReportTemplate(ProjectReportTemplate projectReportTemplate) {
        projectReportTemplateRepositoryMdb.saveProjectReportTemplateMdb(
                ProjectReportTemplateMbdMapper.toProjectReportTemplateMdb(projectReportTemplate));
    }

    @Override
    public ProjectReportTemplate getProjectReportTemplateById(UUID projectReportTemplateId)
            throws NotFoundException {
        return ProjectReportTemplateMbdMapper.sort(ProjectReportTemplateMbdMapper.toProjectReportTemplate(
                projectReportTemplateRepositoryMdb.getProjectReportTemplateMdbById(projectReportTemplateId.toString())));
    }
}
