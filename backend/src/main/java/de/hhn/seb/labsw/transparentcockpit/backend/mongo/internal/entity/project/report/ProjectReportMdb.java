package de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.entity.project.report;

import de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.entity.project.base.group.GroupMdb;
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
    private Map<String, GroupMdb> groups = new HashMap<>();


    // Morphia
    public ProjectReportMdb() {
    }

    public ProjectReportMdb(String projectNumber, String templateId) {
        this(UUID.randomUUID().toString(), projectNumber, templateId, new HashMap<>());
    }

    public ProjectReportMdb(String projectReportId,
                            String projectNumber, String templateId,
                            Map<String, GroupMdb> groups) {
        this.projectReportId = projectReportId;
        this.projectNumber = projectNumber;
        this.templateId = templateId;
        this.groups = groups;
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

    public Map<String, GroupMdb> getGroups() {
        return groups;
    }

    public void setGroups(Map<String, GroupMdb> groups) {
        this.groups = groups;
    }

    public void addGroup(String groupLetter, GroupMdb groupMdb) {
        this.groups.put(groupLetter, groupMdb);
    }
}
