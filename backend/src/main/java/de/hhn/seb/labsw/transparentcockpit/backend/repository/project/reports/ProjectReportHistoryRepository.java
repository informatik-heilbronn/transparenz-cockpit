package de.hhn.seb.labsw.transparentcockpit.backend.repository.project.reports;

import de.hhn.seb.labsw.transparentcockpit.backend.exceptions.reporitory.NotFoundException;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.report.ProjectReport;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.report.ProjectReportHistory;

import java.time.OffsetDateTime;

/**
 * ProjectReportHistory Repository.
 */
public interface ProjectReportHistoryRepository {

    /**
     * Save the ProjectReportHistory.
     *
     * @param projectReportHistory ProjectReportHistory
     */
    void saveProjectReportHistory(ProjectReportHistory projectReportHistory);

    /**
     * Updates the ProjectReportHistory.
     *
     * @param projectReportId ProjectReport ID
     * @param projectReport   updated ProjectReport
     * @throws NotFoundException if no ProjectReportHistory has been found
     */
    void updateProjectReportHistory(String projectReportId, ProjectReport projectReport) throws NotFoundException;

    /**
     * Gets the ProjectReportHistory by ID.
     *
     * @param projectReportId ProjectReport ID
     * @return ProjectReportHistory
     * @throws NotFoundException if no ProjectReportHistory has been found
     */
    ProjectReportHistory getProjectReportHistoryById(String projectReportId) throws NotFoundException;

    /**
     * Get the ProjectReport by ID and Revision.
     *
     * @param projectReportId ProjectReport ID
     * @param revision        ProjectReport Revision
     * @return ProjectReport
     * @throws NotFoundException if no ProjectReportHistory has been found or
     *                           if no ProjectReportHistory with Revision has been found
     */
    ProjectReport getProjectReportHistoryByIdAndRevision(String projectReportId, OffsetDateTime revision)
            throws NotFoundException;

}
