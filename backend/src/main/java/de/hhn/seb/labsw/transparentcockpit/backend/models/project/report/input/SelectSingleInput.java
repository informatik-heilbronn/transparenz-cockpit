package de.hhn.seb.labsw.transparentcockpit.backend.models.project.report.input;

import de.hhn.seb.labsw.transparentcockpit.backend.exceptions.validation.ValueException;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.base.input.BaseInput;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.base.input.DataType;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.base.input.InputModifier;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.template.input.SelectSingleInputTemplate;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.util.ValidationUtil;

import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Project Report Input of Type SelectSingleInput.
 * SelectSingleInput:
 * - List of Allowed Values
 * - Single Input Value
 */
public class SelectSingleInput extends SelectSingleInputTemplate implements ValidateInput {
    private Object value;


    /**
     * Constructor.
     *
     * @param number        Number of the Input
     * @param name          Name of the Input
     * @param modifiers     Modifiers of the Input
     * @param type          Type of the Input
     * @param allowedValues Allowed Values of the SelectSingleInput
     * @param value         Value of the SelectSingleInput
     * @throws ValueException If something is wrong with the Value
     */
    public SelectSingleInput(String number, String name,
                             Set<InputModifier> modifiers,
                             DataType type, List<Object> allowedValues, Object value) throws ValueException {
        super(number, name, modifiers, type, allowedValues);
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) throws ValueException {
        this.value = value;
    }

    @Override
    public void validateInput(BaseInput baseInput) throws IllegalArgumentException, ValueException {
        if (!(baseInput instanceof SelectSingleInputTemplate)) {
            throw new IllegalArgumentException("TemplateInput and Input dont match."
                    + "\n Gotten " + baseInput.getClass() + " but expected " + SelectSingleInputTemplate.class);
        }
        ValidationUtil.validateFields(baseInput, this);
        ValidationUtil.validateValues(baseInput.getModifiers(),
                ((SelectSingleInputTemplate) baseInput).getAllowedValues(), Collections.singletonList(value));
    }
}
