package de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.mapper;

import de.hhn.seb.labsw.transparentcockpit.backend.models.project.base.group.Group;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.template.ProjectReportTemplate;
import de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.entity.project.template.GroupTemplateMdb;
import de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.entity.project.template.ProjectReportTemplateMdb;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

/**
 * ProjectReportTemplate Mongo Mapper.
 */
public class ProjectReportTemplateMbdMapper {

    public static ProjectReportTemplateMdb toProjectReportTemplateMdb(ProjectReportTemplate projectReportTemplate) {
        ProjectReportTemplateMdb projectReportTemplateMdb = new ProjectReportTemplateMdb(
                projectReportTemplate.getTemplateId().toString());

        for (Map.Entry<String, Group> entry : projectReportTemplate.getGroups().entrySet()) {
            String key = entry.getKey();
            Group value = entry.getValue();

            projectReportTemplateMdb.addGroup(key, GroupTemplateMdbMapper.toGroupTemplateMdb(value));
        }
        return projectReportTemplateMdb;
    }

    public static ProjectReportTemplate toProjectReportTemplate(ProjectReportTemplateMdb projectReportTemplateMdb) {
        ProjectReportTemplate projectReportTemplate = new ProjectReportTemplate(
                UUID.fromString(projectReportTemplateMdb.getTemplateId()));

        for (Map.Entry<String, GroupTemplateMdb> entry : projectReportTemplateMdb.getGroups().entrySet()) {
            GroupTemplateMdb value = entry.getValue();

            projectReportTemplate.addGroup(GroupTemplateMdbMapper.toGroup(value));
        }
        return projectReportTemplate;
    }

    public static ProjectReportTemplate sort(ProjectReportTemplate projectReportTemplate) {
        ProjectReportTemplate sortedProjectReportTemplate = new ProjectReportTemplate(projectReportTemplate.getTemplateId());

        Map<String, Group> sortedGroupMap = new TreeMap<>();
        projectReportTemplate.getGroups().entrySet().stream()
                .sorted(Comparator.comparing(o -> o.getValue().getLetter()))
                .forEachOrdered(groupEntry -> sortedGroupMap.put(groupEntry.getKey(), groupEntry.getValue()));

        for (Map.Entry<String, Group> groupEntry : sortedGroupMap.entrySet()) {
            Group sortedGroup = new Group(groupEntry.getValue().getLetter(), groupEntry.getValue().getName());

            groupEntry.getValue().getFields().entrySet().stream()
                    .sorted(Comparator.comparing(o -> o.getValue().getNumber()))
                    .forEachOrdered(baseInputEntry -> sortedGroup.addField(baseInputEntry.getValue()));

            sortedProjectReportTemplate.addGroup(sortedGroup);
        }

        return sortedProjectReportTemplate;
    }
}
