package de.hhn.seb.labsw.transparentcockpit.backend.repository.project.reports;

import de.hhn.seb.labsw.transparentcockpit.backend.exceptions.reporitory.NotFoundException;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.report.ProjectReport;

import java.util.List;

/**
 * ProjectReport Repository.
 */
public interface ProjectReportRepository {

    /**
     * Save the ProjectReport.
     *
     * @param projectReport ProjectReport
     */
    void saveProjectReport(ProjectReport projectReport);

    /**
     * Updated the ProjectReport.
     *
     * @param projectReportId ProjectReport ID
     * @param projectReport   updated ProjectReport
     * @throws NotFoundException if no ProjectReport has been found
     */
    void updateProjectReport(String projectReportId, ProjectReport projectReport) throws NotFoundException;

    /**
     * Get the ProjectReport by ID.
     *
     * @param projectReportId ProjectReport ID
     * @return ProjectReport
     * @throws NotFoundException if no ProjectReport has been found
     */
    ProjectReport getProjectReportById(String projectReportId)
            throws NotFoundException;

    /**
     * Get all ProjectReports.
     *
     * @return List of all ProjectReports
     */
    List<ProjectReport> getAllProjectReports();

}
