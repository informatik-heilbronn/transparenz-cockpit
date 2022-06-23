package de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.entity.project.report;

import de.hhn.seb.labsw.transparentcockpit.backend.models.project.report.input.Input;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.template.input.InputTemplate;
import de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.entity.project.base.group.SectionMdb;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * ProjectReport Mongo Object.
 */
@Entity
public class ProjectReportMdb {

    // internal Identifier
    @Id
    private String projectReportId;

    // Identifier
    private String projectNumber;

    // Attribute
    private String templateId;

    // Content
    private Input reportName;
    private Input reportID;
    private Input reportGroup;
    private Map<String, SectionMdb> sections = new HashMap<>();


    // Morphia
    public ProjectReportMdb() {
    }

    public ProjectReportMdb(String projectNumber, String templateId) {
        this(UUID.randomUUID().toString(), projectNumber, templateId, new HashMap<>());
    }

    public ProjectReportMdb(String projectReportId,
                            String projectNumber, String templateId,
                            Map<String, SectionMdb> sections) {
        this.projectReportId = projectReportId;
        this.projectNumber = projectNumber;
        this.templateId = templateId;
        this.sections = sections;
    }


    public String getProjectReportId() {
        return projectReportId;
    }

    public void setProjectReportId(String projectReportId) {
        this.projectReportId = projectReportId;
    }

    public String getProjectNumber() {
        return projectNumber;
    }

    public void setProjectNumber(String projectNumber) {
        this.projectNumber = projectNumber;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public Map<String, SectionMdb> getSections() {
        return sections;
    }

    public void setSections(Map<String, SectionMdb> sections) {
        this.sections = sections;
    }

    public void addGroup(String groupLetter, SectionMdb sectionMdb) {
        this.sections.put(groupLetter, sectionMdb);
    }

    public Input getReportName() {
        return reportName;
    }

    public void setReportName(Input reportName) {
        this.reportName = reportName;
    }

    public Input getReportID() {
        return reportID;
    }

    public void setReportID(Input reportID) {
        this.reportID = reportID;
    }

    public Input getReportGroup() {
        return reportGroup;
    }

    public void setReportGroup(Input reportGroup) {
        this.reportGroup = reportGroup;
    }
}
