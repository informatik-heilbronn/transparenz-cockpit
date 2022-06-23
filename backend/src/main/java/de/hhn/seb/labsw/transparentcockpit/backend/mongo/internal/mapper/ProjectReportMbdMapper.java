package de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.mapper;

import de.hhn.seb.labsw.transparentcockpit.backend.models.project.base.group.Section;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.report.ProjectReport;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.report.input.Input;
import de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.entity.project.base.group.SectionMdb;
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
        projectReportMdb.setReportGroup(projectReport.getReportGroup());
        projectReportMdb.setReportID(projectReport.getReportID());

        for (Map.Entry<String, Section> entry : projectReport.getSections().entrySet()) {
            String key = entry.getKey();
            Section value = entry.getValue();

            projectReportMdb.addGroup(key, SectionMdbMapper.toSectionMdb(value));
        }

        return projectReportMdb;
    }

    public static ProjectReport toProjectReport(ProjectReportMdb projectReportMdb) {
        ProjectReport projectReport = new ProjectReport(UUID.fromString(projectReportMdb.getTemplateId()), projectReportMdb.getReportID(), projectReportMdb.getReportName(), projectReportMdb.getReportGroup());

        for (Map.Entry<String, SectionMdb> entry : projectReportMdb.getSections().entrySet()) {
            SectionMdb value = entry.getValue();

            projectReport.addSection(SectionMdbMapper.toGroup(value));
        }

        return projectReport;
    }

    public static ProjectReport sort(ProjectReport projectReport) {
        ProjectReport sortedProjectReport = new ProjectReport(projectReport.getTemplateId(), projectReport.getReportID(), projectReport.getReportName(), projectReport.getReportGroup());

        Map<String, Section> sortedGroupMap = new TreeMap<>();
        projectReport.getSections().entrySet().stream()
                .sorted(Comparator.comparing(o -> o.getValue().getLetter()))
                .forEachOrdered(groupEntry -> sortedGroupMap.put(groupEntry.getKey(), groupEntry.getValue()));

        for (Map.Entry<String, Section> groupEntry : sortedGroupMap.entrySet()) {
            Section sortedSectionA = new Section(groupEntry.getValue().getLetter(), groupEntry.getValue().getName());

            groupEntry.getValue().getFields().entrySet().stream()
                    .sorted(Comparator.comparing(o -> o.getValue().getNumber()))
                    .forEachOrdered(baseInputEntry -> sortedSectionA.addField(baseInputEntry.getValue()));

            sortedProjectReport.addSection(sortedSectionA);
        }

        return sortedProjectReport;
    }

}
