package de.hhn.seb.labsw.transparentcockpit.backend.models.project.report.input;

import de.hhn.seb.labsw.transparentcockpit.backend.exceptions.validation.ValueException;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.base.input.BaseInput;

/**
 * Input Validation.
 */
public interface ValidateInput {

    /**
     * Validates the Input against the given Template.
     *
     * @param baseInput Template with the Input will Validate Against.
     * @throws IllegalArgumentException If something is wrong with the Value
     * @throws ValueException           If something is wrong with the Input itself
     */
    void validateInput(BaseInput baseInput) throws IllegalArgumentException, ValueException;
}
