package de.hhn.seb.labsw.transparentcockpit.backend.models.project.report;

import de.hhn.seb.labsw.transparentcockpit.backend.models.project.base.group.Group;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.base.input.BaseInput;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.base.input.InputModifier;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.report.input.SelectSingleInput;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.report.input.SingleInput;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.template.ProjectReportTemplate;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Project Report.
 */
public class ProjectReport {
  // Attribute
  private final UUID templateId;

  // Content
  private final Map<String, Group> groups;


  /**
   * Constructor.
   *
   * @param templateId TemplateId which the Report is base on
   */
  public ProjectReport(UUID templateId) {
    this.templateId = templateId;
    this.groups = new TreeMap<>();
  }

  public UUID getTemplateId() {
    return templateId;
  }

  public String getProjectNumber() throws IllegalArgumentException {
    for (Group group : groups.values()) {
      for (BaseInput inputTemplate : group.getFields().values()) {
        if (inputTemplate.getModifiers().contains(InputModifier.PROJECT_ID)) {
          return ((SingleInput) inputTemplate).getValue().toString();
        }
      }
    }
    throw new IllegalArgumentException("No ProjectReport Number found");
  }


  public String getName() throws IllegalArgumentException {
    for (Group group : groups.values()) {
      for (BaseInput inputTemplate : group.getFields().values()) {
        if (inputTemplate.getModifiers().contains(InputModifier.PROJECT_NAME)) {
          return ((SingleInput) inputTemplate).getValue().toString();
        }
      }
    }
    throw new IllegalArgumentException("No ProjectReport Name found");
  }

  public String getGroup() throws IllegalArgumentException {
    for (Group group : groups.values()) {
      for (BaseInput inputTemplate : group.getFields().values()) {
        if (inputTemplate.getModifiers().contains(InputModifier.PROJECT_GROUP)) {
          if (inputTemplate instanceof SingleInput) {
            return ((SingleInput) inputTemplate).getValue().toString();
          } else if (inputTemplate instanceof SelectSingleInput) {
            return ((SelectSingleInput) inputTemplate).getValue().toString();
          }
        }
      }
    }
    throw new IllegalArgumentException("No ProjectReport Group found");
  }
  public void addGroup(Group group) throws IllegalArgumentException {
    if (groups.containsKey(group.getLetter())) {
      throw new IllegalArgumentException("GroupLetter is already in use");
    }
    groups.put(group.getLetter(), group);
  }
  public Map<String, Group> getGroups() {
    return groups;
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
    projectReportTemplate.getGroups().forEach((groupKey, group) -> {
      if (!groups.containsKey(groupKey)) {
        throw new IllegalArgumentException("TemplateReport:Group and Report:Group dont match."
            + "\n Gotten Letter " + groupKey + " but does not exists");
      }
      groups.get(groupKey).validateGroup(group);
    });
  }
}
