package de.hhn.seb.labsw.transparentcockpit.backend.models.project.template;

import de.hhn.seb.labsw.transparentcockpit.backend.models.project.base.group.Group;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.base.input.BaseInput;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.base.input.InputModifier;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Project Report Template.
 */
public class ProjectReportTemplate {

  // Identifier
  private final UUID templateId;

  // Content
  private final Map<String, Group> groups;


  /**
   * Constructor.
   *
   * @param templateId TemplateId
   */
  public ProjectReportTemplate(UUID templateId) {
    this.templateId = templateId;
    this.groups = new TreeMap<>();
  }

  public UUID getTemplateId() {
    return templateId;
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
   * Validates the Report if all required Fields exist.
   *
   * @throws IllegalArgumentException if a Field is missing or wrong configured
   */
  public void validateTemplate() throws IllegalArgumentException {

    boolean projectReportIdAvailable = false;
    boolean projectReportNameAvailable = false;
    boolean projectReportGroupAvailable = false;

    for (Map.Entry<String, Group> groupEntry : groups.entrySet()) {
      for (Map.Entry<String, BaseInput> baseInputEntry : groupEntry.getValue().getFields().entrySet()) {
        BaseInput baseInput = baseInputEntry.getValue();

        if (baseInput.getModifiers().contains(InputModifier.PROJECT_ID)) {
          if (projectReportIdAvailable) {
            throw new IllegalArgumentException("Modifier 'PROJECT_ID' can only be added Once");
          } else {
            projectReportIdAvailable = true;
          }
        }

        if (baseInput.getModifiers().contains(InputModifier.PROJECT_NAME)) {
          if (projectReportNameAvailable) {
            throw new IllegalArgumentException("Modifier 'PROJECT_NAME' can only be added Once");
          } else {
            projectReportNameAvailable = true;
          }
        }

        if (baseInput.getModifiers().contains(InputModifier.PROJECT_GROUP)) {
          if (projectReportGroupAvailable) {
            throw new IllegalArgumentException("Modifier 'PROJECT_GROUP' can only be added Once");
          } else {
            projectReportGroupAvailable = true;
          }
        }

      }
    }

    if (!projectReportIdAvailable) {
      throw new IllegalArgumentException("Field with Modifier 'PROJECT_ID' is missing");
    } else if (!projectReportNameAvailable) {
      throw new IllegalArgumentException("Field with Modifier 'PROJECT_NAME' is missing");
    } else if (!projectReportGroupAvailable) {
      throw new IllegalArgumentException("Field with Modifier 'PROJECT_GROUP' is missing");
    }
  }
}
