package de.hhn.seb.labsw.transparentcockpit.backend.springboot.internal.mapper;

import de.hhn.seb.labsw.transparentcockpit.backend.models.project.base.group.Section;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.base.input.DataType;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.base.input.InputType;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.report.ProjectReport;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.report.input.Input;
import de.hhn.seb.labsw.transparentcockpit.backend.springboot.internal.models.project.report.ProjectReportSb;

import java.util.*;

/**
 * ProjectReport SpringBoot Mapper.
 */
public class ProjectReportSbMapper {

    public static ProjectReport toProjectReport(Map<String, Object> input, String projectReportId) {
        List<Object> list = new ArrayList<>();//TODO needs to be changed to Values coming from frontend
        list.add("");

        Input inputReportID = new Input("6.", "Vorhabennummer", true,
                DataType.STRING, InputType.SINGLE_INPUT, list);

        list = new ArrayList<>();
        list.add("");
        Input inputReportName = new Input("1.", "Vorhabentitle", true,
                DataType.STRING, InputType.SINGLE_INPUT, list);

        list = new ArrayList<>();
        list.add("");
        List<Object> allowedValuesInputC0 = new ArrayList<>();
        Collections.addAll(allowedValuesInputC0, "Verwaltung & Infrastruktur", "Bildungs- und Wissensstadt",
                "Teilhabe an der Stadtgesellschaft", "Zukunftsfähige Mobilität");
        Input inputReportGroup = new Input("0.", "Gruppe", true,
                DataType.STRING, InputType.SELECT_SINGLE_INPUT, allowedValuesInputC0, list);

        ProjectReport projectReport = new ProjectReport(UUID.fromString(input.get("templateId").toString()), inputReportID, inputReportName, inputReportGroup);

        LinkedHashMap<String, Object> castedInputSections = (LinkedHashMap<String, Object>) input.get("sections");
        for (Map.Entry<String, Object> inputGroupEntry : castedInputSections.entrySet()) {
            LinkedHashMap<String, Object> castedInputSection = (LinkedHashMap<String, Object>) inputGroupEntry.getValue();

            Section section = new Section(castedInputSection.get("letter").toString(), castedInputSection.get("name").toString());

            LinkedHashMap<String, Object> castedInputFields = (LinkedHashMap<String, Object>) castedInputSection.get("fields");
            for (Map.Entry<String, Object> castedInputFieldEntry : castedInputFields.entrySet()) {
                LinkedHashMap<String, Object> castedInputField =
                        (LinkedHashMap<String, Object>) castedInputFieldEntry.getValue();


                DataType dataType = DataType.valueOf(castedInputField.get("type").toString());

                InputType inputType = InputType.valueOf(castedInputField.get("inputType").toString());
                Boolean isRequired = Boolean.getBoolean(castedInputField.get("isRequired").toString());

                Input inputOut = new Input(castedInputField.get("number").toString(),
                        castedInputField.get("name").toString(), isRequired, dataType, inputType, new ArrayList<>(), new ArrayList<>());

                ArrayList<Object> castedInputValues = (ArrayList<Object>) castedInputField.getOrDefault("values",
                        new ArrayList<>());
                for (Object inputValue : castedInputValues) {
                    if (inputValue instanceof List | inputValue instanceof Map) {
                        throw new IllegalArgumentException("'" + inputGroupEntry.getKey() + "." + castedInputFieldEntry.getKey()
                                + ".values' can not be an List or Map");
                    }

                    inputOut.addValues(ValueSbMapper.castValue(dataType, inputValue));
                }
                section.addField(inputOut);
            }
            projectReport.addSection(section);
        }
        return projectReport;
    }

    public static ProjectReportSb convertToSb(ProjectReport projectReport) {
        ProjectReportSb convertedProjectReport = new ProjectReportSb(projectReport.getProjectNumber(),
                projectReport.getName(), projectReport.getGroup(), projectReport.getTemplateId());

        for (Map.Entry<String, Section> groupEntry : projectReport.getSections().entrySet()) {
            convertedProjectReport.addSection(groupEntry.getValue());
        }

        return convertedProjectReport;
    }

}
