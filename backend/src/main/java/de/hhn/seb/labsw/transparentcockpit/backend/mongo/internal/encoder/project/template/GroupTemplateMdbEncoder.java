package de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.encoder.project.template;

import de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.encoder.Base64Encoder;
import de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.entity.project.template.BaseInputTemplateMdb;
import de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.entity.project.template.SectionTemplateMdb;

import java.util.HashMap;
import java.util.Map;

/**
 * Group Mongo Encoder (REQUIRED because '.', ... are not Supported by default).
 */
public class GroupTemplateMdbEncoder {

    public static SectionTemplateMdb encode(SectionTemplateMdb sectionTemplateMdb) {
        SectionTemplateMdb encodedGroupMdb = new SectionTemplateMdb(
                Base64Encoder.encode(sectionTemplateMdb.getLetter()), Base64Encoder.encode(sectionTemplateMdb.getName()));

        Map<String, BaseInputTemplateMdb> fields = new HashMap<>();
        for (Map.Entry<String, BaseInputTemplateMdb> entry : sectionTemplateMdb.getFields().entrySet()) {
            fields.put(Base64Encoder.encode(entry.getKey()), BaseInputTemplateMdbEncoder.encode(entry.getValue()));
        }
        encodedGroupMdb.setFields(fields);

        return encodedGroupMdb;
    }

    public static SectionTemplateMdb decode(SectionTemplateMdb sectionTemplateMdb) {
        SectionTemplateMdb decodedGroupMdb = new SectionTemplateMdb(
                Base64Encoder.decode(sectionTemplateMdb.getLetter()), Base64Encoder.decode(sectionTemplateMdb.getName()));

        Map<String, BaseInputTemplateMdb> fields = new HashMap<>();
        for (Map.Entry<String, BaseInputTemplateMdb> entry : sectionTemplateMdb.getFields().entrySet()) {
            fields.put(Base64Encoder.decode(entry.getKey()), BaseInputTemplateMdbEncoder.decode(entry.getValue()));
        }
        decodedGroupMdb.setFields(fields);

        return decodedGroupMdb;
    }
}
