package de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.mapper;

import de.hhn.seb.labsw.transparentcockpit.backend.models.project.base.group.Group;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.base.input.BaseInput;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.base.input.InputType;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.report.input.MultiInput;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.report.input.SelectMultiInput;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.report.input.SelectSingleInput;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.report.input.SingleInput;
import de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.entity.project.base.group.GroupMdb;
import de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.entity.project.report.BaseInputMdb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;

/**
 * Group Mongo Mapper.
 */
public class GroupMdbMapper {

    public static GroupMdb toGroupMdb(Group group) {
        GroupMdb groupMdb = new GroupMdb(group.getLetter(), group.getName());

        for (Map.Entry<String, BaseInput> entry : group.getFields().entrySet()) {
            String key = entry.getKey();
            BaseInput value = entry.getValue();

            if (value instanceof SingleInput) {
                BaseInputMdb baseInputMdb = new BaseInputMdb(value.getNumber(), value.getName(),
                        InputType.SINGLE_INPUT, new ArrayList<>(value.getModifiers()), value.getType(), Collections.emptyList(),
                        Collections.singletonList(((SingleInput) value).getValue()));

                groupMdb.addField(key, baseInputMdb);
            } else if (value instanceof MultiInput) {
                BaseInputMdb baseInputMdb = new BaseInputMdb(value.getNumber(), value.getName(),
                        InputType.MULTI_INPUT, new ArrayList<>(value.getModifiers()), value.getType(), Collections.emptyList(),
                        ((MultiInput) value).getValues());

                groupMdb.addField(key, baseInputMdb);
            } else if (value instanceof SelectSingleInput) {
                BaseInputMdb baseInputMdb = new BaseInputMdb(value.getNumber(), value.getName(),
                        InputType.SELECT_SINGLE_INPUT, new ArrayList<>(value.getModifiers()), value.getType(),
                        ((SelectSingleInput) value).getAllowedValues(),
                        Collections.singletonList(((SelectSingleInput) value).getValue()));

                groupMdb.addField(key, baseInputMdb);
            } else if (value instanceof SelectMultiInput) {
                BaseInputMdb baseInputMdb = new BaseInputMdb(value.getNumber(), value.getName(),
                        InputType.SELECT_MULTI_INPUT, new ArrayList<>(value.getModifiers()), value.getType(),
                        ((SelectMultiInput) value).getAllowedValues(), ((SelectMultiInput) value).getValues());

                groupMdb.addField(key, baseInputMdb);
            }
        }

        return groupMdb;
    }

    public static Group toGroup(GroupMdb groupMdb) {
        Group reportGroup = new Group(groupMdb.getLetter(), groupMdb.getName());

        for (Map.Entry<String, BaseInputMdb> entry : groupMdb.getFields().entrySet()) {
            BaseInputMdb value = entry.getValue();

            switch (value.getInputType()) {
                case SINGLE_INPUT:

                    SingleInput singleInput;
                    if (value.getValues().isEmpty()) {
                        singleInput = new SingleInput(value.getNumber(), value.getName(), new HashSet<>(value.getModifiers()),
                                value.getType(), null);
                    } else {
                        singleInput = new SingleInput(value.getNumber(), value.getName(), new HashSet<>(value.getModifiers()),
                                value.getType(), value.getValues().get(0));
                    }

                    reportGroup.addField(singleInput);
                    break;
                case MULTI_INPUT:
                    MultiInput multiInput = new MultiInput(value.getNumber(), value.getName(),
                            new HashSet<>(value.getModifiers()), value.getType(), value.getValues());

                    reportGroup.addField(multiInput);
                    break;
                case SELECT_MULTI_INPUT:
                    SelectMultiInput selectMultiInput = new SelectMultiInput(value.getNumber(), value.getName(),
                            new HashSet<>(value.getModifiers()), value.getType(), value.getAllowedValues(), value.getValues());

                    reportGroup.addField(selectMultiInput);
                    break;
                case SELECT_SINGLE_INPUT:
                    SelectSingleInput selectSingleInput = new SelectSingleInput(value.getNumber(), value.getName(),
                            new HashSet<>(value.getModifiers()), value.getType(), value.getAllowedValues(), value.getValues());

                    reportGroup.addField(selectSingleInput);
                    break;
                default:
                    break;
            }
        }

        return reportGroup;
    }
}
