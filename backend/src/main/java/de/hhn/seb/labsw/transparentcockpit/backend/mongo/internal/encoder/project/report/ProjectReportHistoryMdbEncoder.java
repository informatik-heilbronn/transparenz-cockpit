package de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.encoder.project.report;

import de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.encoder.Base64Encoder;
import de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.entity.project.report.ProjectReportHistoryMdb;
import de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.entity.project.report.ProjectReportMdb;

import java.util.HashMap;
import java.util.Map;

/**
 * ProjectReport History Mongo Encoder (REQUIRED because '.', ... are not Supported by default).
 */
public class ProjectReportHistoryMdbEncoder {


    public static ProjectReportHistoryMdb encode(ProjectReportHistoryMdb projectReportHistoryMdb) {
        ProjectReportHistoryMdb encodedProjectReportHistoryMdb = new ProjectReportHistoryMdb(
                Base64Encoder.encode(projectReportHistoryMdb.getProjectNumber()),
                Base64Encoder.encode(projectReportHistoryMdb.getLastEdit()));

        Map<String, ProjectReportMdb> historyMap = new HashMap<>();
        for (Map.Entry<String, ProjectReportMdb> entry : projectReportHistoryMdb.getHistoryMap().entrySet()) {
            historyMap.put(Base64Encoder.encode(entry.getKey()), ProjectReportMdbEncoder.encode(entry.getValue()));
        }
        encodedProjectReportHistoryMdb.setHistoryMap(historyMap);

        return encodedProjectReportHistoryMdb;
    }

    public static ProjectReportHistoryMdb decode(ProjectReportHistoryMdb projectReportHistoryMdb) {
        ProjectReportHistoryMdb decodedProjectReportHistoryMdb = new ProjectReportHistoryMdb(
                Base64Encoder.decode(projectReportHistoryMdb.getProjectNumber()),
                Base64Encoder.decode(projectReportHistoryMdb.getLastEdit()));

        Map<String, ProjectReportMdb> historyMap = new HashMap<>();
        for (Map.Entry<String, ProjectReportMdb> entry : projectReportHistoryMdb.getHistoryMap().entrySet()) {
            historyMap.put(Base64Encoder.decode(entry.getKey()), ProjectReportMdbEncoder.decode(entry.getValue()));
        }
        decodedProjectReportHistoryMdb.setHistoryMap(historyMap);

        return decodedProjectReportHistoryMdb;
    }
}
