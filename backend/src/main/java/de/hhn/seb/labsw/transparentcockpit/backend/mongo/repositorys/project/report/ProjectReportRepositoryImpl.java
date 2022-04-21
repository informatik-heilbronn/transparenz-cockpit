package de.hhn.seb.labsw.transparentcockpit.backend.mongo.repositorys.project.report;

import de.hhn.seb.labsw.transparentcockpit.backend.exceptions.reporitory.NotFoundException;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.report.ProjectReport;
import de.hhn.seb.labsw.transparentcockpit.backend.mongo.config.MongoConfig;
import de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.mapper.ProjectReportMbdMapper;
import de.hhn.seb.labsw.transparentcockpit.backend.repository.project.reports.ProjectReportRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of ProjectReportRepository.
 */
public class ProjectReportRepositoryImpl implements ProjectReportRepository {

    private final ProjectReportRepositoryMdb projectReportRepositoryMdb;


    public ProjectReportRepositoryImpl(MongoConfig mongoConfig) {
        projectReportRepositoryMdb = new ProjectReportRepositoryMdb(mongoConfig);
    }


    @Override
    public void saveProjectReport(ProjectReport projectReport) {
        projectReportRepositoryMdb.saveProjectReportMdb(ProjectReportMbdMapper.toProjectReportMdb(projectReport));
    }

    @Override
    public void updateProjectReport(String projectReportId, ProjectReport projectReport) {
        projectReportRepositoryMdb.updateProjectReportMdb(projectReportId,
                ProjectReportMbdMapper.toProjectReportMdb(projectReport));
    }

    @Override
    public ProjectReport getProjectReportById(String projectReportId)
            throws NotFoundException {
        return ProjectReportMbdMapper.sort(ProjectReportMbdMapper.toProjectReport(
                projectReportRepositoryMdb.getProjectReportMbdById(projectReportId)));
    }

    @Override
    public List<ProjectReport> getAllProjectReports() {
        return projectReportRepositoryMdb.getAllProjectReportMdb()
                .stream()
                .map(projectReportMdb -> ProjectReportMbdMapper.sort(ProjectReportMbdMapper.toProjectReport(projectReportMdb)))
                .collect(Collectors.toList());
    }
}
