package de.hhn.seb.labsw.transparentcockpit.backend.mongo.repositorys.project.report;

import de.hhn.seb.labsw.transparentcockpit.backend.exceptions.reporitory.NotFoundException;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.report.ProjectReport;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.report.ProjectReportHistory;
import de.hhn.seb.labsw.transparentcockpit.backend.mongo.config.MongoConfig;
import de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.mapper.ProjectReportHistoryMbdMapper;
import de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.mapper.ProjectReportMbdMapper;
import de.hhn.seb.labsw.transparentcockpit.backend.repository.project.reports.ProjectReportHistoryRepository;

import java.time.OffsetDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementation of ProjectReportHistoryRepository.
 */
public class ProjectReportHistoryRepositoryImpl implements ProjectReportHistoryRepository {

    private final ProjectReportHistoryRepositoryMdb projectReportHistoryRepositoryMdb;


    public ProjectReportHistoryRepositoryImpl(MongoConfig mongoConfig) {
        projectReportHistoryRepositoryMdb = new ProjectReportHistoryRepositoryMdb(mongoConfig);
    }


    @Override
    public void saveProjectReportHistory(ProjectReportHistory projectReportHistory) {
        projectReportHistoryRepositoryMdb.saveProjectReportHistoryMdb(
                ProjectReportHistoryMbdMapper.toProjectReportHistoryMdb(projectReportHistory));
    }

    @Override
    public void updateProjectReportHistory(String projectReportId,
                                           ProjectReport projectReport)
            throws NotFoundException {
        projectReportHistoryRepositoryMdb.updateProjectReportHistoryMdb(projectReportId,
                ProjectReportMbdMapper.toProjectReportMdb(projectReport));
    }

    @Override
    public ProjectReportHistory getProjectReportHistoryById(String projectReportId)
            throws NotFoundException {
        return ProjectReportHistoryMbdMapper.toProjectReportHistory(
                projectReportHistoryRepositoryMdb.getProjectReportHistoryMdbById(projectReportId));
    }

    @Override
    public ProjectReport getProjectReportHistoryByIdAndRevision(String projectReportId, OffsetDateTime revision)
            throws NotFoundException {
        ProjectReportHistory projectReportHistory = getProjectReportHistoryById(projectReportId);

        if (!projectReportHistory.getHistoryMap().containsKey(revision.toLocalDateTime())) {
            throw new NotFoundException("There is no ProjectReportRevision with the Revision: " + revision);
        }

        return projectReportHistory.getHistoryMap().get(revision.toLocalDateTime());
    }
}
