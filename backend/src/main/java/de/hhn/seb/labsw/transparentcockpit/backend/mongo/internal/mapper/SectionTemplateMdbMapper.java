package de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.mapper;

import de.hhn.seb.labsw.transparentcockpit.backend.models.project.base.group.Section;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.base.input.BaseInput;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.template.input.InputTemplate;
import de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.entity.project.template.BaseInputTemplateMdb;
import de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.entity.project.template.SectionTemplateMdb;

import java.util.Collections;
import java.util.Map;

/**
 * Group Template Mongo Mapper.
 */
public class SectionTemplateMdbMapper {

    /**
     * Maps a Section to a SectionTemplateMdb object
     * @param section A section to map
     * @return the SectionTemplateMdb object that can be stored in the Mongo DB
     */
    public static SectionTemplateMdb toSectionTemplateMdb(Section section) {
        SectionTemplateMdb groupMdb = new SectionTemplateMdb(section.getLetter(), section.getName());

        for (Map.Entry<String, BaseInput> entry : section.getFields().entrySet()) {
            String key = entry.getKey();
            BaseInput value = entry.getValue();

            BaseInputTemplateMdb baseInputTemplateMdb = new BaseInputTemplateMdb(value.getNumber(), value.getName(),
                    value.getInputType(), value.getIsRequired(), value.getType(), Collections.emptyList());

            groupMdb.addField(key, baseInputTemplateMdb);
        }

        return groupMdb;
    }

    /**
     * Maps a SectionTemplateMdb to a Section object
     * @param groupMdb A SectionTemplateMdb object to map
     * @return the Section object that is used in the backend
     */
    public static Section toSection(SectionTemplateMdb groupMdb) {
        Section reportSectionA = new Section(groupMdb.getLetter(), groupMdb.getName());

        for (Map.Entry<String, BaseInputTemplateMdb> entry : groupMdb.getFields().entrySet()) {
            BaseInputTemplateMdb value = entry.getValue();

            InputTemplate inputTemplate = new InputTemplate(value.getNumber(), value.getName(),
                    value.getIsRequired(), value.getType(), value.getInputType(), value.getAllowedValues());
            reportSectionA.addField(inputTemplate);
        }

        return reportSectionA;
    }
}
