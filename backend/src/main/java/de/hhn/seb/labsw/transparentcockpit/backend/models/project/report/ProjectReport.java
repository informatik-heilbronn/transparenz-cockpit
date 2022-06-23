package de.hhn.seb.labsw.transparentcockpit.backend.models.project.report;

import de.hhn.seb.labsw.transparentcockpit.backend.models.project.base.group.Section;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.base.input.DataType;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.base.input.InputType;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.report.input.Input;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.template.ProjectReportTemplate;

import java.util.*;

/**
 * Project Report.
 */
public class ProjectReport {
    // Attribute
    private final UUID templateId;

    // Content
    private final Input reportName;
    private final Input reportID;
    private final Input reportGroup;
    private final Map<String, Section> sections;

    /**
     * Constructor.
     *
     * @param templateId    templateId TemplateId which the Report is base on
     * @param reportID
     * @param reportName
     * @param reportGroup
     */
    public ProjectReport(UUID templateId, Input reportID, Input reportName, Input reportGroup) {
        this.templateId = templateId;
        this.reportID = reportID;
        this.reportName = reportName;
        this.reportGroup = reportGroup;
        this.sections = new TreeMap<>();
    }

    public UUID getTemplateId() {
        return templateId;
    }

    public String getProjectNumber() {
        return getReportID().getValues().get(0).toString();
    }


    public String getName() {
        return getReportName().getValues().get(0).toString();
    }

    public String getGroup(){
        return getReportGroup().getValues().get(0).toString();
    }

    public void addSection(Section section) throws IllegalArgumentException {
        if (this.sections.containsKey(section.getLetter())) {
            throw new IllegalArgumentException("GroupLetter is already in use");
        }
        this.sections.put(section.getLetter(), section);
    }

    public Map<String, Section> getSections() {
        return sections;
    }


    /**
     * Validates the whole ProjectReport against the given Template.
     *
     * @param projectReportTemplate Template which the ProjectReport is Validated against
     */
    public void validateReport(ProjectReportTemplate projectReportTemplate) {
        if (!templateId.equals(projectReportTemplate.getTemplateId())) {
            throw new IllegalArgumentException("TemplateReport and Report dont match."
                    + "\n Gotten TemplateID " + projectReportTemplate.getTemplateId() + " but expected " + this.getTemplateId());
        }
        projectReportTemplate.getSections().forEach((groupKey, group) -> {
            if (!sections.containsKey(groupKey)) {
                throw new IllegalArgumentException("TemplateReport:Group and Report:Group dont match."
                        + "\n Gotten Letter " + groupKey + " but does not exists");
            }
            sections.get(groupKey).validateGroup(group);
        });
    }

    public Input getReportName() {
        return reportName;
    }

    public Input getReportID() {
        return reportID;
    }

    public Input getReportGroup() {
        return reportGroup;
    }
}
