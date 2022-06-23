package de.hhn.seb.labsw.transparentcockpit.backend.models.project.base.input;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Project Report Base Input.
 * BaseInput witch all other Inputs extend from.
 */
public abstract class BaseInput {

    // Identifier
    protected final String number;

    protected final String name;

    // Attribute
    protected final InputType inputType;
    protected final boolean isRequired;

    // Content
    protected final DataType type;


    /**
     * Constructor.
     *
     * @param number    Number of the Input
     * @param name      Name of the Input
     * @param isRequired Is Input required
     * @param type      Type of the Input
     * @param inputType InputType
     */
    public BaseInput(String number, String name,
                     boolean isRequired,
                     DataType type, InputType inputType) {

        this.number = number;
        this.name = name;
        this.isRequired = isRequired;
        this.type = type;
        this.inputType = inputType;
    }

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public boolean getIsRequired() {
        return isRequired;
    }

    public DataType getType() {
        return type;
    }

    public InputType getInputType() {
        return inputType;
    }
}
