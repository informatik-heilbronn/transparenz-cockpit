package de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.entity.project.template;

import de.hhn.seb.labsw.transparentcockpit.backend.models.project.template.input.InputTemplate;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * ProjectReportTemplate Mongo Object.
 */
@Entity
public class ProjectReportTemplateMdb {


    // internal Identifier
    @Id
    private String projectReportTemplate;

    // Identifier
    private String templateId;

    // Content
    private InputTemplate reportName;
    private InputTemplate reportID;
    private InputTemplate reportGroup;
    private Map<String, SectionTemplateMdb> sections = new HashMap<>();


    // Morphia
    public ProjectReportTemplateMdb() {
    }

    public ProjectReportTemplateMdb(String templateId) {
        this(UUID.randomUUID().toString(), templateId, new HashMap<>());
    }

    public ProjectReportTemplateMdb(String projectReportTemplate,
                                    String templateId,
                                    Map<String, SectionTemplateMdb> sections) {
        this.projectReportTemplate = projectReportTemplate;
        this.templateId = templateId;
        this.sections = sections;
    }


    public String getProjectReportTemplate() {
        return projectReportTemplate;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public Map<String, SectionTemplateMdb> getSections() {
        return sections;
    }

    public void addGroup(String groupLetter, SectionTemplateMdb groupMdb) {
        this.sections.put(groupLetter, groupMdb);
    }

    public void setSections(Map<String, SectionTemplateMdb> sections) {
        this.sections = sections;
    }

    public InputTemplate getReportName() {
        return reportName;
    }

    public void setReportName(InputTemplate reportName) {
        this.reportName = reportName;
    }

    public InputTemplate getReportID() {
        return reportID;
    }

    public void setReportID(InputTemplate reportID) {
        this.reportID = reportID;
    }

    public InputTemplate getReportGroup() {
        return reportGroup;
    }

    public void setReportGroup(InputTemplate reportGroup) {
        this.reportGroup = reportGroup;
    }
}
