package de.hhn.seb.labsw.transparentcockpit.backend.models.project.report.input;

import de.hhn.seb.labsw.transparentcockpit.backend.exceptions.validation.ValueException;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.base.input.BaseInput;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.base.input.DataType;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.base.input.InputModifier;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.template.input.MultiInputTemplate;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.util.ValidationUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Project Report Input of Type MultiInput.
 * MultiInput:
 * - List of Input Values
 */
public class MultiInput extends MultiInputTemplate implements ValidateInput {
    // Content
    private final List<Object> values;


    /**
     * Constructor.
     *
     * @param number    Number of the Input
     * @param name      Name of the Input
     * @param modifiers Modifiers of the Input
     * @param type      Type of the Input
     * @param values    Values of the MultiInput
     * @throws ValueException If something is wrong with the Value
     */
    public MultiInput(String number, String name,
                      Set<InputModifier> modifiers,
                      DataType type, List<Object> values) throws ValueException {
        super(number, name, modifiers, type);
        ValidationUtil.validateValues(modifiers, values);
        this.values = values;
    }

    public List<Object> getValues() {
        return this.values;
    }

    public void addValues(Object... values) throws ValueException {
        this.values.addAll(Arrays.asList(values));
        ValidationUtil.validateValues(modifiers, this.values);
    }


    @Override
    public void validateInput(BaseInput baseInput) throws IllegalArgumentException, ValueException {

        if (!(baseInput instanceof MultiInputTemplate)) {
            throw new IllegalArgumentException("TemplateInput and Input dont match."
                    + "\n Gotten " + baseInput.getClass() + " but expected " + MultiInputTemplate.class);
        }
        ValidationUtil.validateFields(baseInput, this);
        ValidationUtil.validateValues(baseInput.getModifiers(), values);
    }
}