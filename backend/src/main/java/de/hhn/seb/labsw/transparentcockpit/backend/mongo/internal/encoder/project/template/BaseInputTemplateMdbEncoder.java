package de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.encoder.project.template;

import de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.encoder.Base64Encoder;
import de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.entity.project.template.BaseInputTemplateMdb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Base Input Template Mongo Encoder (REQUIRED because '.', ... are not Supported by default).
 */
public class BaseInputTemplateMdbEncoder {


    public static BaseInputTemplateMdb encode(BaseInputTemplateMdb baseInputTemplateMdb) {
        BaseInputTemplateMdb encodedBaseInputTemplateMdb = new BaseInputTemplateMdb(
                Base64Encoder.encode(baseInputTemplateMdb.getNumber()), Base64Encoder.encode(baseInputTemplateMdb.getName()),
                baseInputTemplateMdb.getInputType(), baseInputTemplateMdb.getModifiers(),
                baseInputTemplateMdb.getType(), Collections.emptyList());

        List<Object> allowedValues = new ArrayList<>();
        for (Object allowedValue : baseInputTemplateMdb.getAllowedValues()) {
            allowedValues.add(Base64Encoder.encode(allowedValue.toString()));
        }
        encodedBaseInputTemplateMdb.setAllowedValues(allowedValues);


        return encodedBaseInputTemplateMdb;
    }

    public static BaseInputTemplateMdb decode(BaseInputTemplateMdb baseInputTemplateMdb) {
        BaseInputTemplateMdb decodedBaseInputTemplateMdb = new BaseInputTemplateMdb(
                Base64Encoder.decode(baseInputTemplateMdb.getNumber()), Base64Encoder.decode(baseInputTemplateMdb.getName()),
                baseInputTemplateMdb.getInputType(), baseInputTemplateMdb.getModifiers(),
                baseInputTemplateMdb.getType(), Collections.emptyList());

        List<Object> allowedValues = new ArrayList<>();
        for (Object allowedValue : baseInputTemplateMdb.getAllowedValues()) {
            allowedValues.add(Base64Encoder.decode(allowedValue.toString()));
        }
        decodedBaseInputTemplateMdb.setAllowedValues(allowedValues);


        return decodedBaseInputTemplateMdb;
    }
}
