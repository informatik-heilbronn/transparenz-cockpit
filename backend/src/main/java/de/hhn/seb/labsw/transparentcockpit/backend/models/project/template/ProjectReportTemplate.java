package de.hhn.seb.labsw.transparentcockpit.backend.models.project.template;

import de.hhn.seb.labsw.transparentcockpit.backend.models.project.base.group.Section;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.base.input.DataType;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.base.input.InputType;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.template.input.InputTemplate;

import java.util.*;

/**
 * Project Report Template.
 */
public class ProjectReportTemplate {

  // Identifier
  private final UUID templateId;

  // Content
  private final InputTemplate reportName;
  private final InputTemplate reportID;
  private final InputTemplate reportGroup;
  private final Map<String, Section> sections;


  /**
   * Constructor.
   *
   * @param templateId
   * @param reportID
   * @param reportName
   * @param reportGroup
   */
  public ProjectReportTemplate(UUID templateId, InputTemplate reportID, InputTemplate reportName, InputTemplate reportGroup) {
    this.templateId = templateId;
    this.reportID = reportID;
    this.reportName = reportName;
    this.reportGroup = reportGroup;
    this.sections = new TreeMap<>();
  }

  /**
   * Constructor.
   *
   * @param templateId TemplateId which the Report is base on
   */
  public ProjectReportTemplate(UUID templateId) {
    this.templateId = templateId;

    this.reportID = new InputTemplate("6.", "Vorhabennummer", true,
            DataType.STRING, InputType.SINGLE_INPUT);

    this.reportName = new InputTemplate("1.", "Vorhabentitle", true,
            DataType.STRING, InputType.SINGLE_INPUT);

    List<Object> allowedValuesInputC0 = new ArrayList<>();
    Collections.addAll(allowedValuesInputC0, "Verwaltung & Infrastruktur", "Bildungs- und Wissensstadt",
            "Teilhabe an der Stadtgesellschaft", "Zukunftsfähige Mobilität");
    this.reportGroup = new InputTemplate("0.", "Gruppe", true,
            DataType.STRING, InputType.SELECT_SINGLE_INPUT, allowedValuesInputC0);

    this.sections = new TreeMap<>();
  }

  public UUID getTemplateId() {
    return templateId;
  }

  public void addSection(Section section) throws IllegalArgumentException {

    if (sections.containsKey(section.getLetter())) {
      throw new IllegalArgumentException("GroupLetter is already in use");
    }
    sections.put(section.getLetter(), section);
  }

  public Map<String, Section> getSections() {
    return sections;
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

    if (!projectReportIdAvailable) {
      throw new IllegalArgumentException("Field with Modifier 'PROJECT_ID' is missing");
    } else if (!projectReportNameAvailable) {
      throw new IllegalArgumentException("Field with Modifier 'PROJECT_NAME' is missing");
    } else if (!projectReportGroupAvailable) {
      throw new IllegalArgumentException("Field with Modifier 'PROJECT_GROUP' is missing");
    }
  }

  public InputTemplate getReportName() {
    return reportName;
  }

  public InputTemplate getReportID() {
    return reportID;
  }

  public InputTemplate getReportGroup() {
    return reportGroup;
  }
}
