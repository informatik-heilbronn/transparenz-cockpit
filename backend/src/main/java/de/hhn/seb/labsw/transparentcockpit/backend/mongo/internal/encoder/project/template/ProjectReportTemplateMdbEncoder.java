package de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.encoder.project.template;

import de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.encoder.Base64Encoder;
import de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.entity.project.template.GroupTemplateMdb;
import de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.entity.project.template.ProjectReportTemplateMdb;

import java.util.HashMap;
import java.util.Map;

/**
 * ProjectReport Template Mongo Encoder (REQUIRED because '.', ... are not Supported by default).
 */
public class ProjectReportTemplateMdbEncoder {

    public static ProjectReportTemplateMdb encode(ProjectReportTemplateMdb projectReportTemplateMdb) {
        ProjectReportTemplateMdb encodedProjectReportTemplateMdb = new ProjectReportTemplateMdb(
                Base64Encoder.encode(projectReportTemplateMdb.getTemplateId()));

        Map<String, GroupTemplateMdb> groups = new HashMap<>();
        for (Map.Entry<String, GroupTemplateMdb> entry : projectReportTemplateMdb.getGroups().entrySet()) {
            groups.put(Base64Encoder.encode(entry.getKey()), GroupTemplateMdbEncoder.encode(entry.getValue()));
        }
        encodedProjectReportTemplateMdb.setGroups(groups);

        return encodedProjectReportTemplateMdb;
    }

    public static ProjectReportTemplateMdb decode(ProjectReportTemplateMdb projectReportTemplateMdb) {
        ProjectReportTemplateMdb decodedProjectReportMdb = new ProjectReportTemplateMdb(
                Base64Encoder.decode(projectReportTemplateMdb.getTemplateId()));

        Map<String, GroupTemplateMdb> groups = new HashMap<>();
        for (Map.Entry<String, GroupTemplateMdb> entry : projectReportTemplateMdb.getGroups().entrySet()) {
            groups.put(Base64Encoder.decode(entry.getKey()), GroupTemplateMdbEncoder.decode(entry.getValue()));
        }
        decodedProjectReportMdb.setGroups(groups);

        return decodedProjectReportMdb;
    }
}
