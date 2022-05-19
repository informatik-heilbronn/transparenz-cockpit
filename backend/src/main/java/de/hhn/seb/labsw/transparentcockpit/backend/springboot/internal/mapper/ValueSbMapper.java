package de.hhn.seb.labsw.transparentcockpit.backend.springboot.internal.mapper;

import de.hhn.seb.labsw.transparentcockpit.backend.models.project.base.input.DataType;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Value SpringBoot Mapper.
 */
public class ValueSbMapper {


    public static Object castValue(DataType dataType, Object object) {
        if (object == null) {
            return null;
        }
        switch (dataType) {
            case BOOLEAN:
                return Boolean.parseBoolean(object.toString());
            case INTEGER:
                return Integer.parseInt(object.toString());
            case STRING:
                return object.toString();
            case DATE:
                try {
                    return new SimpleDateFormat("dd.MM.yyyy").parse(object.toString());
                } catch (ParseException e) {
                    throw new IllegalArgumentException(object + " can not be converted into Date");
                }
            default:
                return null;
        }
    }
}
