package de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.entity.project.report;

import de.hhn.seb.labsw.transparentcockpit.backend.models.project.base.input.DataType;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.base.input.InputType;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * BaseInput Mongo Object.
 */
@Entity
public class BaseInputMdb {

    // internal Identifier
    @Id
    private String id;

    // Identifier
    private String number;

    // Name
    private String name;

    // Attribute
    private InputType inputType;
    private boolean isRequired;

    // Content
    private DataType type;
    private List<Object> allowedValues = new ArrayList<>();
    private List<Object> values = new ArrayList<>();


    // Morphia
    public BaseInputMdb() {
    }

    public BaseInputMdb(String number, String name,
                        InputType inputType, boolean isRequired,
                        DataType type, List<Object> allowedValues, List<Object> values) {
        this(UUID.randomUUID().toString(), number, name, inputType, isRequired, type, allowedValues, values);
    }

    public BaseInputMdb(String id,
                        String number, String name,
                        InputType inputType, boolean isRequired,
                        DataType type, List<Object> allowedValues, List<Object> values) {
        this.id = id;
        this.number = number;
        this.name = name;
        this.inputType = inputType;
        this.isRequired = isRequired;
        this.type = type;
        this.allowedValues = allowedValues;
        this.values = values;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public InputType getInputType() {
        return inputType;
    }

    public void setInputType(InputType inputType) {
        this.inputType = inputType;
    }

    public boolean getIsRequired() {
        return isRequired;
    }

    public void setIsRequired(boolean modifiers) {
        this.isRequired = isRequired;
    }

    public DataType getType() {
        return type;
    }

    public void setType(DataType type) {
        this.type = type;
    }

    public List<Object> getAllowedValues() {
        return allowedValues;
    }

    public void setAllowedValues(List<Object> allowedValues) {
        this.allowedValues = allowedValues;
    }

    public List<Object> getValues() {
        return values;
    }

    public void setValues(List<Object> values) {
        this.values = values;
    }
}
