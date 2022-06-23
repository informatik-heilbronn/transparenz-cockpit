package de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.encoder.project.base.group;

import de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.encoder.Base64Encoder;
import de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.encoder.project.report.BaseInputMdbEncoder;
import de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.entity.project.base.group.SectionMdb;
import de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.entity.project.report.BaseInputMdb;

import java.util.HashMap;
import java.util.Map;

/**
 * Group Mongo Encoder (REQUIRED because '.', ... are not Supported by default).
 */
public class GroupMdbEncoder {
    public static SectionMdb encode(SectionMdb sectionMdb) {
        SectionMdb encodedSectionMdb = new SectionMdb(
                Base64Encoder.encode(sectionMdb.getLetter()), Base64Encoder.encode(sectionMdb.getName()));

        Map<String, BaseInputMdb> fields = new HashMap<>();
        for (Map.Entry<String, BaseInputMdb> entry : sectionMdb.getFields().entrySet()) {
            fields.put(Base64Encoder.encode(entry.getKey()), BaseInputMdbEncoder.encode(entry.getValue()));
        }
        encodedSectionMdb.setFields(fields);

        return encodedSectionMdb;
    }

    public static SectionMdb decode(SectionMdb sectionMdb) {
        SectionMdb decodedSectionMdb = new SectionMdb(
                Base64Encoder.decode(sectionMdb.getLetter()), Base64Encoder.decode(sectionMdb.getName()));

        Map<String, BaseInputMdb> fields = new HashMap<>();
        for (Map.Entry<String, BaseInputMdb> entry : sectionMdb.getFields().entrySet()) {
            fields.put(Base64Encoder.decode(entry.getKey()), BaseInputMdbEncoder.decode(entry.getValue()));
        }
        decodedSectionMdb.setFields(fields);

        return decodedSectionMdb;
    }
}
