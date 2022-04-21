package de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.mapper;

import de.hhn.seb.labsw.transparentcockpit.backend.models.project.base.group.Group;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.base.input.BaseInput;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.base.input.InputType;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.template.input.MultiInputTemplate;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.template.input.SelectMultiInputTemplate;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.template.input.SelectSingleInputTemplate;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.template.input.SingleInputTemplate;
import de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.entity.project.template.BaseInputTemplateMdb;
import de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.entity.project.template.GroupTemplateMdb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;

/**
 * Group Template Mongo Mapper.
 */
public class GroupTemplateMdbMapper {

    public static GroupTemplateMdb toGroupTemplateMdb(Group group) {
        GroupTemplateMdb groupMdb = new GroupTemplateMdb(group.getLetter(), group.getName());

        for (Map.Entry<String, BaseInput> entry : group.getFields().entrySet()) {
            String key = entry.getKey();
            BaseInput value = entry.getValue();

            if (value instanceof SingleInputTemplate) {
                BaseInputTemplateMdb baseInputTemplateMdb = new BaseInputTemplateMdb(value.getNumber(), value.getName(),
                        InputType.SINGLE_INPUT, new ArrayList<>(value.getModifiers()), value.getType(), Collections.emptyList());

                groupMdb.addField(key, baseInputTemplateMdb);
            } else if (value instanceof MultiInputTemplate) {
                BaseInputTemplateMdb baseInputTemplateMdb = new BaseInputTemplateMdb(value.getNumber(), value.getName(),
                        InputType.MULTI_INPUT, new ArrayList<>(value.getModifiers()), value.getType(), Collections.emptyList());

                groupMdb.addField(key, baseInputTemplateMdb);
            } else if (value instanceof SelectSingleInputTemplate) {
                BaseInputTemplateMdb baseInputTemplateMdb = new BaseInputTemplateMdb(value.getNumber(), value.getName(),
                        InputType.SELECT_SINGLE_INPUT, new ArrayList<>(value.getModifiers()), value.getType(),
                        ((SelectSingleInputTemplate) value).getAllowedValues());

                groupMdb.addField(key, baseInputTemplateMdb);
            } else if (value instanceof SelectMultiInputTemplate) {
                BaseInputTemplateMdb baseInputTemplateMdb = new BaseInputTemplateMdb(value.getNumber(), value.getName(),
                        InputType.SELECT_MULTI_INPUT, new ArrayList<>(value.getModifiers()), value.getType(),
                        ((SelectMultiInputTemplate) value).getAllowedValues());

                groupMdb.addField(key, baseInputTemplateMdb);
            }
        }

        return groupMdb;
    }

    public static Group toGroup(GroupTemplateMdb groupMdb) {
        Group reportGroup = new Group(groupMdb.getLetter(), groupMdb.getName());

        for (Map.Entry<String, BaseInputTemplateMdb> entry : groupMdb.getFields().entrySet()) {
            BaseInputTemplateMdb value = entry.getValue();

            switch (value.getInputType()) {
                case SINGLE_INPUT:
                    SingleInputTemplate singleInputTemplate = new SingleInputTemplate(value.getNumber(), value.getName(),
                            new HashSet<>(value.getModifiers()), value.getType());

                    reportGroup.addField(singleInputTemplate);
                    break;
                case MULTI_INPUT:
                    MultiInputTemplate multiInputTemplate = new MultiInputTemplate(value.getNumber(), value.getName(),
                            new HashSet<>(value.getModifiers()), value.getType());

                    reportGroup.addField(multiInputTemplate);
                    break;
                case SELECT_MULTI_INPUT:
                    SelectMultiInputTemplate selectMultiInputTemplate = new SelectMultiInputTemplate(value.getNumber(),
                            value.getName(), new HashSet<>(value.getModifiers()), value.getType(), value.getAllowedValues());

                    reportGroup.addField(selectMultiInputTemplate);
                    break;
                case SELECT_SINGLE_INPUT:
                    SelectSingleInputTemplate selectSingleInputTemplate = new SelectSingleInputTemplate(value.getNumber(),
                            value.getName(), new HashSet<>(value.getModifiers()), value.getType(), value.getAllowedValues());

                    reportGroup.addField(selectSingleInputTemplate);
                    break;
                default:
                    break;
            }
        }

        return reportGroup;
    }
}
