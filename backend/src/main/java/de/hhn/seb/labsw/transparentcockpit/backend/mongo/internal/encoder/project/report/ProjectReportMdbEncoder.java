package de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.encoder.project.report;

import de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.encoder.Base64Encoder;
import de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.encoder.project.base.group.GroupMdbEncoder;
import de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.entity.project.base.group.SectionMdb;
import de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.entity.project.report.ProjectReportMdb;

import java.util.HashMap;
import java.util.Map;

/**
 * ProjectReport Mongo Encoder (REQUIRED because '.', ... are not Supported by default).
 */
public class ProjectReportMdbEncoder {

    public static ProjectReportMdb encode(ProjectReportMdb projectReportMdb) {
        ProjectReportMdb encodedProjectReportMdb = new ProjectReportMdb(
                Base64Encoder.encode(projectReportMdb.getProjectNumber()),
                Base64Encoder.encode(projectReportMdb.getTemplateId()));

        Map<String, SectionMdb> groups = new HashMap<>();
        for (Map.Entry<String, SectionMdb> entry : projectReportMdb.getSections().entrySet()) {
            groups.put(Base64Encoder.encode(entry.getKey()), GroupMdbEncoder.encode(entry.getValue()));
        }
        encodedProjectReportMdb.setSections(groups);

        return encodedProjectReportMdb;
    }

    public static ProjectReportMdb decode(ProjectReportMdb projectReportMdb) {
        ProjectReportMdb decodedProjectReportMdb = new ProjectReportMdb(
                Base64Encoder.decode(projectReportMdb.getProjectNumber()),
                Base64Encoder.decode(projectReportMdb.getTemplateId()));

        Map<String, SectionMdb> groups = new HashMap<>();
        for (Map.Entry<String, SectionMdb> entry : projectReportMdb.getSections().entrySet()) {
            groups.put(Base64Encoder.decode(entry.getKey()), GroupMdbEncoder.decode(entry.getValue()));
        }
        decodedProjectReportMdb.setSections(groups);

        return decodedProjectReportMdb;
    }
}
