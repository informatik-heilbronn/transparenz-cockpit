package de.hhn.seb.labsw.transparentcockpit.backend.buisnesslogic;

import de.hhn.seb.labsw.transparentcockpit.backend.exceptions.reporitory.NotFoundException;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.report.ProjectReport;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.report.ProjectReportHistory;
import de.hhn.seb.labsw.transparentcockpit.backend.mongo.config.MongoConfig;
import de.hhn.seb.labsw.transparentcockpit.backend.mongo.repositorys.project.report.ProjectReportHistoryRepositoryImpl;
import de.hhn.seb.labsw.transparentcockpit.backend.mongo.repositorys.project.report.ProjectReportRepositoryImpl;
import de.hhn.seb.labsw.transparentcockpit.backend.repository.project.reports.ProjectReportHistoryRepository;
import de.hhn.seb.labsw.transparentcockpit.backend.repository.project.reports.ProjectReportRepository;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ProjectReport Service.
 * Handles all ProjectReport Actions (Create, Read, Update).
 */
public class ProjectReportService {

    private final ProjectReportRepository projectReportRepository;
    private final ProjectReportHistoryRepository projectReportHistoryRepository;


    /**
     * Constructor.
     *
     * @param mongoConfig MongoDB Configuration
     */
    public ProjectReportService(MongoConfig mongoConfig) {
        projectReportRepository = new ProjectReportRepositoryImpl(mongoConfig);
        projectReportHistoryRepository = new ProjectReportHistoryRepositoryImpl(mongoConfig);
    }


    /**
     * Save the ProjectReport.
     *
     * @param projectReport ProjectReport to Save
     */
    public void saveProjectReport(ProjectReport projectReport) {
        projectReportRepository.saveProjectReport(projectReport);

        ProjectReportHistory projectReportHistory = new ProjectReportHistory(projectReport.getProjectNumber());
        projectReportHistory.addHistory(LocalDateTime.now(), projectReport);
        projectReportHistoryRepository.saveProjectReportHistory(projectReportHistory);
    }

    /**
     * Update the ProjectReport.
     *
     * @param projectReport Updated ProjectReport
     */
    public void updateProjectReport(ProjectReport projectReport) {
        projectReportRepository.updateProjectReport(projectReport.getProjectNumber(), projectReport);
        projectReportHistoryRepository.updateProjectReportHistory(projectReport.getProjectNumber(), projectReport);
    }

    /**
     * Get all ProjectReports.
     *
     * @return List with all current ProjectReports
     */
    public List<ProjectReport> getAllProjectReports() {
        return projectReportRepository.getAllProjectReports();
    }

    /**
     * Get ProjectReport by ID.
     *
     * @param projectReportId ProjectReport ID
     * @return the ProjectReport
     * @throws NotFoundException if on ProjectReport has been found with the given ID
     */
    public ProjectReport getProjectReportById(String projectReportId) throws NotFoundException {
        return projectReportRepository.getProjectReportById(projectReportId);
    }

}
