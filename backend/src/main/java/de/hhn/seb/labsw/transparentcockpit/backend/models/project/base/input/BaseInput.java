package de.hhn.seb.labsw.transparentcockpit.backend.models.project.base.input;

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
    protected final Set<InputModifier> modifiers;

    // Content
    protected final DataType type;
    protected final InputType inputType;


    /**
     * Constructor.
     *
     * @param number    Number of the Input
     * @param name      Name of the Input
     * @param modifiers Modifiers of the Input
     * @param type      Type of the Input
     * @param inputType InputType
     */
    public BaseInput(String number, String name,
                     Set<InputModifier> modifiers,
                     DataType type, InputType inputType) {

        this.number = number;
        this.name = name;
        this.modifiers = modifiers;
        this.type = type;
        this.inputType = inputType;
    }

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public Set<InputModifier> getModifiers() {
        return modifiers;
    }

    public DataType getType() {
        return type;
    }

    public InputType getInputType() {
        return inputType;
    }
}
