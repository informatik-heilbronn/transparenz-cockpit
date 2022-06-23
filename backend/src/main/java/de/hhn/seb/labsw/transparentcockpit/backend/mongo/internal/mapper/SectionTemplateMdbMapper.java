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

    public static SectionTemplateMdb toSectionTemplateMdb(Section sectionA) {
        SectionTemplateMdb groupMdb = new SectionTemplateMdb(sectionA.getLetter(), sectionA.getName());

        for (Map.Entry<String, BaseInput> entry : sectionA.getFields().entrySet()) {
            String key = entry.getKey();
            BaseInput value = entry.getValue();

            BaseInputTemplateMdb baseInputTemplateMdb = new BaseInputTemplateMdb(value.getNumber(), value.getName(),
                    value.getInputType(), value.getIsRequired(), value.getType(), Collections.emptyList());

            groupMdb.addField(key, baseInputTemplateMdb);
        }

        return groupMdb;
    }

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
