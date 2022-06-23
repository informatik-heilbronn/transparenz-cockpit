package de.hhn.seb.labsw.transparentcockpit.backend.exceptions.validation;

import de.hhn.seb.labsw.transparentcockpit.backend.models.project.base.input.InputModifier;

/**
 * Exception if there is an error with modifiers.
 */
public class ModifierException extends RuntimeException {

    private final InputModifier invalidModifier;


    public ModifierException(String message,
                             InputModifier invalidModifier) {
        super(message);
        this.invalidModifier = invalidModifier;
    }


    public InputModifier getInvalidModifier() {
        return invalidModifier;
    }
}
