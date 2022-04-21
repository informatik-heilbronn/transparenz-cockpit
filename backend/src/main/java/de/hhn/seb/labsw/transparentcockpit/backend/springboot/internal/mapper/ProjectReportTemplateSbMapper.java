package de.hhn.seb.labsw.transparentcockpit.backend.springboot.internal.mapper;

import de.hhn.seb.labsw.transparentcockpit.backend.models.project.base.group.Group;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.base.input.DataType;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.base.input.InputModifier;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.base.input.InputType;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.template.ProjectReportTemplate;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.template.input.MultiInputTemplate;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.template.input.SelectMultiInputTemplate;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.template.input.SelectSingleInputTemplate;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.template.input.SingleInputTemplate;

import java.util.*;


public class ProjectReportTemplateSbMapper {

    public static ProjectReportTemplate toProjectReportTemplate(UUID templateId, Map<String, Object> input) {
        if (input.size() != 1) {
            throw new IllegalArgumentException("Too much or too little Field found in Input expected '1'");
        }
        ProjectReportTemplate projectReportTemplate = new ProjectReportTemplate(templateId);

        LinkedHashMap<String, Object> castedInputGroups = (LinkedHashMap<String, Object>) input.get("groups");
        for (Map.Entry<String, Object> inputGroupEntry : castedInputGroups.entrySet()) {
            LinkedHashMap<String, Object> castedInputGroup = (LinkedHashMap<String, Object>) inputGroupEntry.getValue();

            if (castedInputGroup.size() != 3) {
                throw new IllegalArgumentException("Too much or too little Field found in Group '" + inputGroupEntry.getKey()
                        + "' expected '3'");
            }

            Group group = new Group(castedInputGroup.get("letter").toString(), castedInputGroup.get("name").toString());

            LinkedHashMap<String, Object> castedInputFields = (LinkedHashMap<String, Object>) castedInputGroup.get("fields");
            for (Map.Entry<String, Object> castedInputFieldEntry : castedInputFields.entrySet()) {
                LinkedHashMap<String, Object> castedInputField =
                        (LinkedHashMap<String, Object>) castedInputFieldEntry.getValue();

                ArrayList<String> modifierList = (ArrayList<String>) castedInputField.get("modifiers");
                Set<InputModifier> modifiers = new HashSet<>();
                for (String modifier : modifierList) {
                    modifiers.add(InputModifier.valueOf(modifier));
                }

                DataType dataType = DataType.valueOf(castedInputField.get("type").toString());

                InputType type = InputType.valueOf(castedInputField.get("inputType").toString());
                if (type == InputType.SINGLE_INPUT) {
                    if (castedInputField.size() != 5) {
                        throw new IllegalArgumentException("Too much or too little Field found in Field '"
                                + inputGroupEntry.getKey() + "." + castedInputFieldEntry.getKey() + "' expected '5'");
                    }


                    SingleInputTemplate singleInputTemplate = new SingleInputTemplate(castedInputField.get("number").toString(),
                            castedInputField.get("name").toString(), modifiers, dataType);

                    group.addField(singleInputTemplate);
                } else if (type == InputType.MULTI_INPUT) {
                    if (castedInputField.size() != 5) {
                        throw new IllegalArgumentException("Too much or too little Field found in Field '"
                                + inputGroupEntry.getKey() + "." + castedInputFieldEntry.getKey() + "' expected '5'");
                    }

                    MultiInputTemplate multiInputTemplate = new MultiInputTemplate(castedInputField.get("number").toString(),
                            castedInputField.get("name").toString(), modifiers, dataType);

                    group.addField(multiInputTemplate);
                } else if (type == InputType.SELECT_SINGLE_INPUT) {
                    if (castedInputField.size() != 6) {
                        throw new IllegalArgumentException("Too much or too little Field found in Field '"
                                + inputGroupEntry.getKey() + "." + castedInputFieldEntry.getKey() + "' expected '6'");
                    }

                    SelectSingleInputTemplate singleInputTemplate = new SelectSingleInputTemplate(
                            castedInputField.get("number").toString(), castedInputField.get("name").toString(), modifiers, dataType,
                            new ArrayList<>());

                    ArrayList<Object> castedInputValues = (ArrayList<Object>) castedInputField.get("allowedValues");
                    for (Object inputValue : castedInputValues) {
                        if (inputValue instanceof List | inputValue instanceof Map) {
                            throw new IllegalArgumentException("'" + inputGroupEntry.getKey() + "." + castedInputFieldEntry.getKey()
                                    + ".values' can not be an List or Map");
                        }

                        singleInputTemplate.addAllowedValue(ValueSbMapper.castValue(dataType, inputValue));
                    }

                    group.addField(singleInputTemplate);
                } else if (type == InputType.SELECT_MULTI_INPUT) {
                    if (castedInputField.size() != 6) {
                        throw new IllegalArgumentException("Too much or too little Field found in Field '"
                                + inputGroupEntry.getKey() + "." + castedInputFieldEntry.getKey() + "' expected '6'");
                    }

                    SelectMultiInputTemplate selectMultiInputTemplate = new SelectMultiInputTemplate(
                            castedInputField.get("number").toString(), castedInputField.get("name").toString(), modifiers, dataType,
                            new ArrayList<>());

                    ArrayList<Object> castedInputValues = (ArrayList<Object>) castedInputField.get("allowedValues");
                    for (Object inputValue : castedInputValues) {
                        if (inputValue instanceof List | inputValue instanceof Map) {
                            throw new IllegalArgumentException("'" + inputGroupEntry.getKey() + "." + castedInputFieldEntry.getKey()
                                    + ".values' can not be an List or Map");
                        }

                        selectMultiInputTemplate.addAllowedValue(ValueSbMapper.castValue(dataType, inputValue));
                    }

                    group.addField(selectMultiInputTemplate);
                }
            }
            projectReportTemplate.addGroup(group);
        }

        return projectReportTemplate;
    }

}
