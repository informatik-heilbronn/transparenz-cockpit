package de.hhn.seb.labsw.transparentcockpit.backend.models.project.template.input;

import de.hhn.seb.labsw.transparentcockpit.backend.exceptions.validation.ValueException;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.base.input.BaseInput;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.base.input.DataType;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.base.input.InputModifier;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.base.input.InputType;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.util.ValidationUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Project Report InputTemplate of Type SelectMultiInputTemplate.
 * SelectMultiInput:
 * - List of Allowed Values
 * - List of Input Values
 */
public class SelectMultiInputTemplate extends BaseInput {
    // Content
    protected List<Object> allowedValues;


    /**
     * Constructor.
     *
     * @param number        Number of the Input
     * @param name          Name of the Input
     * @param modifiers     Modifiers of the Input
     * @param type          Type of the Input
     * @param allowedValues Allowed Values of the SelectMultiInputTemplate
     * @throws ValueException If something is wrong with the Value
     */
    public SelectMultiInputTemplate(String number, String name,
                                    Set<InputModifier> modifiers,
                                    DataType type, List<Object> allowedValues) throws ValueException {
        super(number, name, modifiers, type, InputType.SELECT_MULTI_INPUT);

        // Validation
        ValidationUtil.validateModifier(this.getClass().getName(), modifiers, InputModifier.REQUIRED);
        ValidationUtil.validateAllowedValues(allowedValues);

        this.allowedValues = allowedValues;
    }


    /**
     * Setter of the AllowedValues.
     *
     * @param allowedValues allowedValues which will be added to AllowedValueList
     * @throws ValueException If something is wrong with the AllowedValue
     */
    public void addAllowedValue(Object... allowedValues) {

        this.allowedValues.addAll(Arrays.asList(allowedValues));

        ValidationUtil.validateAllowedValues(this.allowedValues);
    }

    public List<Object> getAllowedValues() {

        return allowedValues;
    }


}
