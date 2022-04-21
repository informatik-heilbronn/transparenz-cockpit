package de.hhn.seb.labsw.transparentcockpit.backend.springboot.internal.models.project.report;

import de.hhn.seb.labsw.transparentcockpit.backend.models.project.template.ProjectReportTemplate;

import java.util.UUID;

/**
 * ProjectReportTemplate SpringBoot Object.
 */
public class ProjectReportSb extends ProjectReportTemplate {


    // Attribute
    private final String projectNumber;
    private final String projectName;
    private final String projectGroup;


    public ProjectReportSb(String projectNumber,
                           String projectName, String projectGroup,
                           UUID templateId) {
        super(templateId);

        this.projectNumber = projectNumber;
        this.projectName = projectName;
        this.projectGroup = projectGroup;
    }


    public String getProjectNumber() {
        return projectNumber;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

}
