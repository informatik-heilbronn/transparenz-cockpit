package de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.mapper;

import de.hhn.seb.labsw.transparentcockpit.backend.models.project.base.group.Group;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.report.ProjectReport;
import de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.entity.project.base.group.GroupMdb;
import de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.entity.project.report.ProjectReportMdb;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

/**
 * ProjectReport Mongo Mapper.
 */
public class ProjectReportMbdMapper {

    public static ProjectReportMdb toProjectReportMdb(ProjectReport projectReport) {
        ProjectReportMdb projectReportMdb = new ProjectReportMdb(projectReport.getProjectNumber(), projectReport.getName());

        projectReportMdb.setTemplateId(projectReport.getTemplateId().toString());

        for (Map.Entry<String, Group> entry : projectReport.getGroups().entrySet()) {
            String key = entry.getKey();
            Group value = entry.getValue();

            projectReportMdb.addGroup(key, GroupMdbMapper.toGroupMdb(value));
        }

        return projectReportMdb;
    }

    public static ProjectReport toProjectReport(ProjectReportMdb projectReportMdb) {
        ProjectReport projectReport = new ProjectReport(UUID.fromString(projectReportMdb.getTemplateId()));

        for (Map.Entry<String, GroupMdb> entry : projectReportMdb.getGroups().entrySet()) {
            GroupMdb value = entry.getValue();

            projectReport.addGroup(GroupMdbMapper.toGroup(value));
        }

        return projectReport;
    }

    public static ProjectReport sort(ProjectReport projectReport) {
        ProjectReport sortedProjectReport = new ProjectReport(projectReport.getTemplateId());

        Map<String, Group> sortedGroupMap = new TreeMap<>();
        projectReport.getGroups().entrySet().stream()
                .sorted(Comparator.comparing(o -> o.getValue().getLetter()))
                .forEachOrdered(groupEntry -> sortedGroupMap.put(groupEntry.getKey(), groupEntry.getValue()));

        for (Map.Entry<String, Group> groupEntry : sortedGroupMap.entrySet()) {
            Group sortedGroup = new Group(groupEntry.getValue().getLetter(), groupEntry.getValue().getName());

            groupEntry.getValue().getFields().entrySet().stream()
                    .sorted(Comparator.comparing(o -> o.getValue().getNumber()))
                    .forEachOrdered(baseInputEntry -> sortedGroup.addField(baseInputEntry.getValue()));

            sortedProjectReport.addGroup(sortedGroup);
        }

        return sortedProjectReport;
    }

}
