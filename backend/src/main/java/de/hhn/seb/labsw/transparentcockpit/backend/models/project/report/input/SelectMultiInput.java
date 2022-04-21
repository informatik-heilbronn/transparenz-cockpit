package de.hhn.seb.labsw.transparentcockpit.backend.models.project.report.input;

import de.hhn.seb.labsw.transparentcockpit.backend.exceptions.validation.ValueException;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.base.input.BaseInput;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.base.input.DataType;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.base.input.InputModifier;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.template.input.SelectMultiInputTemplate;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.util.ValidationUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Project Report Input of Type SelectMultiInput.
 * SelectMultiInput:
 * - List of Allowed Values
 * - List of Input Values
 */
public class SelectMultiInput extends SelectMultiInputTemplate implements ValidateInput {

    // Content
    private final List<Object> values;

    /**
     * Constructor.
     *
     * @param number        Number of the Input
     * @param name          Name of the Input
     * @param modifiers     Modifiers of the Input
     * @param type          Type of the Input
     * @param allowedValues Allowed Values of the SelectMultiInput
     * @param values        Values of the SelectMultiInput
     * @throws ValueException If something is wrong with the Value
     */
    public SelectMultiInput(String number, String name,
                            Set<InputModifier> modifiers,
                            DataType type, List<Object> allowedValues, List<Object> values) throws ValueException {
        super(number, name, modifiers, type, allowedValues);

        ValidationUtil.validateValues(modifiers, values);
        this.values = values;
    }

    public void addValue(Object... values) throws ValueException {
        this.values.addAll(Arrays.asList(values));
        ValidationUtil.validateValues(modifiers, this.values);
    }

    public List<Object> getValues() {
        return values;
    }

    @Override
    public void validateInput(BaseInput baseInput) throws IllegalArgumentException, ValueException {

        if (!(baseInput instanceof SelectMultiInputTemplate)) {
            throw new IllegalArgumentException("TemplateInput and Input dont match."
                    + "\n Gotten " + baseInput.getClass() + " but expected " + SelectMultiInputTemplate.class);
        }
        ValidationUtil.validateFields(baseInput, this);
        ValidationUtil.validateValues(baseInput.getModifiers(),
                ((SelectMultiInputTemplate) baseInput).getAllowedValues(), values);
    }
}
