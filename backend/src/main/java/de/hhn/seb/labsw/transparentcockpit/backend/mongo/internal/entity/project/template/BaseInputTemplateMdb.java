package de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.entity.project.template;

import de.hhn.seb.labsw.transparentcockpit.backend.models.project.base.input.DataType;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.base.input.InputModifier;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.base.input.InputType;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * BaseInputTemplate Mongo Object.
 */
@Entity
public class BaseInputTemplateMdb {

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


    // Morphia
    public BaseInputTemplateMdb() {
    }

    public BaseInputTemplateMdb(String number, String name,
                                InputType inputType, boolean isRequired,
                                DataType type, List<Object> allowedValues) {
        this(UUID.randomUUID().toString(), number, name, inputType, isRequired, type, allowedValues);
    }

    public BaseInputTemplateMdb(String id,
                                String number, String name,
                                InputType inputType, boolean isRequired,
                                DataType type, List<Object> allowedValues) {
        this.id = id;
        this.number = number;
        this.name = name;
        this.inputType = inputType;
        this.isRequired = isRequired;
        this.type = type;
        this.allowedValues = allowedValues;
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

    public void addAllowedValues(Object... allowedValues) {
        this.allowedValues.addAll(Arrays.asList(allowedValues));
    }
}
