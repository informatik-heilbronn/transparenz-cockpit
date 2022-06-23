package de.hhn.seb.labsw.transparentcockpit.backend.springboot.internal.mapper;

import de.hhn.seb.labsw.transparentcockpit.backend.models.project.base.group.Section;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.base.input.DataType;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.base.input.InputType;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.template.ProjectReportTemplate;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.template.input.InputTemplate;

import java.util.*;

/**
 * ProjectReport SpringBoot Mapper.
 */
public class ProjectReportTemplateSbMapper {

    public static ProjectReportTemplate toProjectReportTemplate(UUID templateId, Map<String, Object> input) {

        ProjectReportTemplate projectReportTemplate = new ProjectReportTemplate(templateId);

        LinkedHashMap<String, Object> castedInputSections = (LinkedHashMap<String, Object>) input.get("sections");
        for (Map.Entry<String, Object> inputGroupEntry : castedInputSections.entrySet()) {
            LinkedHashMap<String, Object> castedInputsection = (LinkedHashMap<String, Object>) inputGroupEntry.getValue();

            Section section = new Section(castedInputsection.get("letter").toString(), castedInputsection.get("name").toString());

            LinkedHashMap<String, Object> castedInputFields = (LinkedHashMap<String, Object>) castedInputsection.get("fields");
            for (Map.Entry<String, Object> castedInputFieldEntry : castedInputFields.entrySet()) {
                LinkedHashMap<String, Object> castedInputField =
                        (LinkedHashMap<String, Object>) castedInputFieldEntry.getValue();


                DataType dataType = DataType.valueOf(castedInputField.get("type").toString());

                InputType inputType = InputType.valueOf(castedInputField.get("inputType").toString());
                Boolean isRequired = Boolean.getBoolean(castedInputField.get("isRequired").toString());

                InputTemplate inputTemplate = new InputTemplate(castedInputField.get("number").toString(), castedInputField.get("name").toString(), isRequired, dataType, inputType,
                        new ArrayList<>());

                ArrayList<Object> castedInputValues = (ArrayList<Object>) castedInputField.get("allowedValues");
                for (Object inputValue : castedInputValues) {
                    if (inputValue instanceof List | inputValue instanceof Map) {
                        throw new IllegalArgumentException("'" + inputGroupEntry.getKey() + "." + castedInputFieldEntry.getKey()
                                + ".values' can not be an List or Map");
                    }

                    inputTemplate.addAllowedValue(ValueSbMapper.castValue(dataType, inputValue));
                }
                section.addField(inputTemplate);
            }
            projectReportTemplate.addSection(section);
        }
        return projectReportTemplate;
    }
}
