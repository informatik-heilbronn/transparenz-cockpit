package de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.mapper;

import de.hhn.seb.labsw.transparentcockpit.backend.models.project.base.group.Section;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.template.ProjectReportTemplate;
import de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.entity.project.template.ProjectReportTemplateMdb;
import de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.entity.project.template.SectionTemplateMdb;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

/**
 * ProjectReportTemplate Mongo Mapper.
 */
public class ProjectReportTemplateMbdMapper {

    /**
     * Maps a ProjectReportTemplate to a ProjectReportTemplateMdb object
     * @param projectReportTemplate A ProjectReportTemplate to map
     * @return the ProjectReportTemplateMdb object that can be stored in the Mongo DB
     */
    public static ProjectReportTemplateMdb toProjectReportTemplateMdb(ProjectReportTemplate projectReportTemplate) {
        ProjectReportTemplateMdb projectReportTemplateMdb = new ProjectReportTemplateMdb(
                projectReportTemplate.getTemplateId().toString());

        projectReportTemplateMdb.setTemplateId(projectReportTemplate.getTemplateId().toString());
        projectReportTemplateMdb.setReportGroup(projectReportTemplate.getReportGroup());
        projectReportTemplateMdb.setReportID(projectReportTemplate.getReportID());
        projectReportTemplateMdb.setReportName(projectReportTemplate.getReportName());

        for (Map.Entry<String, Section> entry : projectReportTemplate.getSections().entrySet()) {
            String key = entry.getKey();
            Section value = entry.getValue();

            projectReportTemplateMdb.addGroup(key, SectionTemplateMdbMapper.toSectionTemplateMdb(value));
        }
        return projectReportTemplateMdb;
    }

    /**
     * Maps a ProjectReportTemplateMdb to a ProjectReportTemplate object
     * @param projectReportTemplateMdb A ProjectReportTemplateMdb to map
     * @return the ProjectReportTemplate object that is used in the backend
     */
    public static ProjectReportTemplate toProjectReportTemplate(ProjectReportTemplateMdb projectReportTemplateMdb) {
        ProjectReportTemplate projectReportTemplate = new ProjectReportTemplate(
                UUID.fromString(projectReportTemplateMdb.getTemplateId()), projectReportTemplateMdb.getReportID(), projectReportTemplateMdb.getReportName(), projectReportTemplateMdb.getReportGroup());

        for (Map.Entry<String, SectionTemplateMdb> entry : projectReportTemplateMdb.getSections().entrySet()) {
            SectionTemplateMdb value = entry.getValue();

            projectReportTemplate.addSection(SectionTemplateMdbMapper.toSection(value));
        }
        return projectReportTemplate;
    }

    /**
     * Sorts the section-map based on the letter
     * @param projectReportTemplate A ProjectReportTemplate to be sorted
     * @return A sorted ProjectReportTemplate that can be used in the backend
     */
    public static ProjectReportTemplate sort(ProjectReportTemplate projectReportTemplate) {
        ProjectReportTemplate sortedProjectReportTemplate = new ProjectReportTemplate(projectReportTemplate.getTemplateId());

        Map<String, Section> sortedGroupMap = new TreeMap<>();
        projectReportTemplate.getSections().entrySet().stream()
                .sorted(Comparator.comparing(o -> o.getValue().getLetter()))
                .forEachOrdered(groupEntry -> sortedGroupMap.put(groupEntry.getKey(), groupEntry.getValue()));

        for (Map.Entry<String, Section> groupEntry : sortedGroupMap.entrySet()) {
            Section sortedSectionA = new Section(groupEntry.getValue().getLetter(), groupEntry.getValue().getName());

            groupEntry.getValue().getFields().entrySet().stream()
                    .sorted(Comparator.comparing(o -> o.getValue().getNumber()))
                    .forEachOrdered(baseInputEntry -> sortedSectionA.addField(baseInputEntry.getValue()));

            sortedProjectReportTemplate.addSection(sortedSectionA);
        }

        return sortedProjectReportTemplate;
    }
}
