package de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.mapper;

import de.hhn.seb.labsw.transparentcockpit.backend.models.project.report.ProjectReport;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.report.ProjectReportHistory;
import de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.entity.project.report.ProjectReportHistoryMdb;
import de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.entity.project.report.ProjectReportMdb;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Map;

/**
 * ProjectReportHistory Mongo Mapper.
 */
public class ProjectReportHistoryMbdMapper {

    /**
     * Maps a ProjectReportHistory to a ProjectReportHistoryMdb object
     * @param projectReportHistory A ProjectReportHistory to map
     * @return the ProjectReportHistoryMdb object that can be stored in the Mongo DB
     */
    public static ProjectReportHistoryMdb toProjectReportHistoryMdb(ProjectReportHistory projectReportHistory) {
        ProjectReportHistoryMdb projectReportHistoryMdb = new ProjectReportHistoryMdb(
                projectReportHistory.getProjectNumber(),
                projectReportHistory.getLastEdit().atOffset(ZoneOffset.UTC).toString());

        for (Map.Entry<LocalDateTime, ProjectReport> entry : projectReportHistory.getHistoryMap().entrySet()) {
            LocalDateTime key = entry.getKey();
            ProjectReport value = entry.getValue();

            projectReportHistoryMdb.addHistory(key.atOffset(ZoneOffset.UTC).toString(),
                    ProjectReportMbdMapper.toProjectReportMdb(value));
        }
        return projectReportHistoryMdb;
    }

    /**
     * Maps a ProjectReportHistoryMdb to a ProjectReportHistory object
     * @param projectReportHistoryMdb A ProjectReportHistoryMdb object to map
     * @return the ProjectReportHistory object that is used in the backend
     */
    public static ProjectReportHistory toProjectReportHistory(ProjectReportHistoryMdb projectReportHistoryMdb) {
        ProjectReportHistory projectReportHistory = new ProjectReportHistory(projectReportHistoryMdb.getProjectNumber());

        for (Map.Entry<String, ProjectReportMdb> entry : projectReportHistoryMdb.getHistoryMap().entrySet()) {
            String key = entry.getKey();
            ProjectReportMdb value = entry.getValue();

            projectReportHistory.addHistory(OffsetDateTime.parse(key).toLocalDateTime(),
                    ProjectReportMbdMapper.toProjectReport(value));
        }

        return projectReportHistory;
    }

}
