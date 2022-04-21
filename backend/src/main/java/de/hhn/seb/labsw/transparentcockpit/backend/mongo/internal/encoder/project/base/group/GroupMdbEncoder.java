package de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.encoder.project.base.group;

import de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.encoder.Base64Encoder;
import de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.encoder.project.report.BaseInputMdbEncoder;
import de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.entity.project.base.group.GroupMdb;
import de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.entity.project.report.BaseInputMdb;

import java.util.HashMap;
import java.util.Map;

/**
 * Group Mongo Encoder (REQUIRED because '.', ... are not Supported by default).
 */
public class GroupMdbEncoder {
    public static GroupMdb encode(GroupMdb groupMdb) {
        GroupMdb encodedGroupMdb = new GroupMdb(
                Base64Encoder.encode(groupMdb.getLetter()), Base64Encoder.encode(groupMdb.getName()));

        Map<String, BaseInputMdb> fields = new HashMap<>();
        for (Map.Entry<String, BaseInputMdb> entry : groupMdb.getFields().entrySet()) {
            fields.put(Base64Encoder.encode(entry.getKey()), BaseInputMdbEncoder.encode(entry.getValue()));
        }
        encodedGroupMdb.setFields(fields);

        return encodedGroupMdb;
    }

    public static GroupMdb decode(GroupMdb groupMdb) {
        GroupMdb decodedGroupMdb = new GroupMdb(
                Base64Encoder.decode(groupMdb.getLetter()), Base64Encoder.decode(groupMdb.getName()));

        Map<String, BaseInputMdb> fields = new HashMap<>();
        for (Map.Entry<String, BaseInputMdb> entry : groupMdb.getFields().entrySet()) {
            fields.put(Base64Encoder.decode(entry.getKey()), BaseInputMdbEncoder.decode(entry.getValue()));
        }
        decodedGroupMdb.setFields(fields);

        return decodedGroupMdb;
    }
}
