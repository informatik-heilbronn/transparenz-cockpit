package de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.encoder.project.report;

import de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.encoder.Base64Encoder;
import de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.entity.project.report.BaseInputMdb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Base Input Master Mongo Encoder (REQUIRED because '.', ... are not Supported by default).
 */
public class BaseInputMdbEncoder {


    public static BaseInputMdb encode(BaseInputMdb baseInputMdb) {
        BaseInputMdb encodedBaseInputMdb = new BaseInputMdb(
                Base64Encoder.encode(baseInputMdb.getNumber()), Base64Encoder.encode(baseInputMdb.getName()),
                baseInputMdb.getInputType(), baseInputMdb.getModifiers(),
                baseInputMdb.getType(), Collections.emptyList(), Collections.emptyList());

        List<Object> allowedValues = new ArrayList<>();
        for (Object allowedValue : baseInputMdb.getAllowedValues()) {
            if (allowedValue != null) {
                allowedValues.add(Base64Encoder.encode(allowedValue.toString()));
            }
        }
        encodedBaseInputMdb.setAllowedValues(allowedValues);

        List<Object> values = new ArrayList<>();
        for (Object value : baseInputMdb.getValues()) {
            if (value != null) {
                values.add(Base64Encoder.encode(value.toString()));
            }
        }
        encodedBaseInputMdb.setValues(values);

        return encodedBaseInputMdb;
    }

    public static BaseInputMdb decode(BaseInputMdb baseInputMdb) {
        BaseInputMdb decodeBaseInputMdb = new BaseInputMdb(
                Base64Encoder.decode(baseInputMdb.getNumber()), Base64Encoder.decode(baseInputMdb.getName()),
                baseInputMdb.getInputType(), baseInputMdb.getModifiers(),
                baseInputMdb.getType(), Collections.emptyList(), Collections.emptyList());

        List<Object> allowedValues = new ArrayList<>();
        for (Object allowedValue : baseInputMdb.getAllowedValues()) {
            if (allowedValue != null) {
                allowedValues.add(Base64Encoder.decode(allowedValue.toString()));
            }
        }
        decodeBaseInputMdb.setAllowedValues(allowedValues);

        List<Object> values = new ArrayList<>();
        for (Object value : baseInputMdb.getValues()) {
            if (value != null) {
                values.add(Base64Encoder.decode(value.toString()));
            }
        }
        decodeBaseInputMdb.setValues(values);

        return decodeBaseInputMdb;
    }
}
