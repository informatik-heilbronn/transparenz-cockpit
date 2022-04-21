package de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.entity.project.template;

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
    private Map<String, GroupTemplateMdb> groups = new HashMap<>();


    // Morphia
    public ProjectReportTemplateMdb() {
    }

    public ProjectReportTemplateMdb(String templateId) {
        this(UUID.randomUUID().toString(), templateId, new HashMap<>());
    }

    public ProjectReportTemplateMdb(String projectReportTemplate,
                                    String templateId,
                                    Map<String, GroupTemplateMdb> groups) {
        this.projectReportTemplate = projectReportTemplate;
        this.templateId = templateId;
        this.groups = groups;
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

    public Map<String, GroupTemplateMdb> getGroups() {
        return groups;
    }

    public void addGroup(String groupLetter, GroupTemplateMdb groupMdb) {
        this.groups.put(groupLetter, groupMdb);
    }

    public void setGroups(Map<String, GroupTemplateMdb> groups) {
        this.groups = groups;
    }
}
