package de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.mapper;

import de.hhn.seb.labsw.transparentcockpit.backend.models.project.base.group.Section;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.base.input.BaseInput;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.report.input.Input;
import de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.entity.project.base.group.SectionMdb;
import de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.entity.project.report.BaseInputMdb;

import java.util.Collections;
import java.util.Map;

/**
 * Group Mongo Mapper.
 */
public class SectionMdbMapper {

    /**
     * Maps a Section to a SectionMdb object
     * @param  section A section to map
     * @returnthe SectionMdb object that can be stored in the Mongo DB
     */
    public static SectionMdb toSectionMdb(Section section) {
        SectionMdb sectionMdb = new SectionMdb(section.getLetter(), section.getName());

        for (Map.Entry<String, BaseInput> entry : section.getFields().entrySet()) {
            String key = entry.getKey();
            BaseInput value = entry.getValue();
            Input inputValue = (Input) value;

            if (value instanceof Input) {
                BaseInputMdb baseInputMdb = new BaseInputMdb(value.getNumber(), value.getName(),
                        value.getInputType(), value.getIsRequired(), value.getType(), Collections.emptyList(),
                        Collections.singletonList(inputValue.getValues()));

                sectionMdb.addField(key, baseInputMdb);
            }
        }

        return sectionMdb;
    }

    /**
     * Maps a SectionMdb to a Section object
     * @param sectionMdb A SectionMdb object to map
     * @return the Section object that is used in the backend
     */
    public static Section toGroup(SectionMdb sectionMdb) {
        Section reportSectionA = new Section(sectionMdb.getLetter(), sectionMdb.getName());

        for (Map.Entry<String, BaseInputMdb> entry : sectionMdb.getFields().entrySet()) {
            BaseInputMdb value = entry.getValue();

            Input multiInput = new Input(value.getNumber(), value.getName(),
                    value.getIsRequired(), value.getType(), value.getInputType(), value.getAllowedValues(), value.getValues());

            reportSectionA.addField(multiInput);
        }
        return reportSectionA;
    }
}
