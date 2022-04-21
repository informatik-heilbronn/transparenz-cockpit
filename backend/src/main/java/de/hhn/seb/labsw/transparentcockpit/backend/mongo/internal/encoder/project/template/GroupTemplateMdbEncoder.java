package de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.encoder.project.template;

import de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.encoder.Base64Encoder;
import de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.entity.project.template.BaseInputTemplateMdb;
import de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.entity.project.template.GroupTemplateMdb;

import java.util.HashMap;
import java.util.Map;

/**
 * Group Mongo Encoder (REQUIRED because '.', ... are not Supported by default).
 */
public class GroupTemplateMdbEncoder {

    public static GroupTemplateMdb encode(GroupTemplateMdb groupTemplateMdb) {
        GroupTemplateMdb encodedGroupMdb = new GroupTemplateMdb(
                Base64Encoder.encode(groupTemplateMdb.getLetter()), Base64Encoder.encode(groupTemplateMdb.getName()));

        Map<String, BaseInputTemplateMdb> fields = new HashMap<>();
        for (Map.Entry<String, BaseInputTemplateMdb> entry : groupTemplateMdb.getFields().entrySet()) {
            fields.put(Base64Encoder.encode(entry.getKey()), BaseInputTemplateMdbEncoder.encode(entry.getValue()));
        }
        encodedGroupMdb.setFields(fields);

        return encodedGroupMdb;
    }

    public static GroupTemplateMdb decode(GroupTemplateMdb groupTemplateMdb) {
        GroupTemplateMdb decodedGroupMdb = new GroupTemplateMdb(
                Base64Encoder.decode(groupTemplateMdb.getLetter()), Base64Encoder.decode(groupTemplateMdb.getName()));

        Map<String, BaseInputTemplateMdb> fields = new HashMap<>();
        for (Map.Entry<String, BaseInputTemplateMdb> entry : groupTemplateMdb.getFields().entrySet()) {
            fields.put(Base64Encoder.decode(entry.getKey()), BaseInputTemplateMdbEncoder.decode(entry.getValue()));
        }
        decodedGroupMdb.setFields(fields);

        return decodedGroupMdb;
    }
}
