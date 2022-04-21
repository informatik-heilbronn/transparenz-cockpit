package de.hhn.seb.labsw.transparentcockpit.backend.models.project.report.input;

import de.hhn.seb.labsw.transparentcockpit.backend.exceptions.validation.ValueException;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.base.input.BaseInput;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.base.input.DataType;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.base.input.InputModifier;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.template.input.SingleInputTemplate;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.util.ValidationUtil;

import java.util.Collections;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Project Report Input of Type SingleInput.
 * SingleInput:
 * - Single Input Value
 */
public class SingleInput extends SingleInputTemplate implements ValidateInput {

    private Object value;

    /**
     * Constructor.
     *
     * @param number    Number of the Input
     * @param name      Name of the Input
     * @param modifiers Modifiers of the Input
     * @param type      Type of the Input
     * @param value     Value of the SingleInput
     * @throws ValueException If something is wrong with the Value
     */
    public SingleInput(String number, String name,
                       Set<InputModifier> modifiers,
                       DataType type, Object value) throws ValueException {
        super(number, name, modifiers, type);
        ValidationUtil.validateValues(modifiers, Collections.singletonList(value));

        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public void validateInput(BaseInput baseInput) throws IllegalArgumentException, ValueException {
        if (!(baseInput instanceof SingleInputTemplate)) {
            throw new IllegalArgumentException("TemplateInput and Input dont match."
                    + "\n Gotten " + baseInput.getClass() + " but expected " + SingleInputTemplate.class);
        }
        ValidationUtil.validateFields(baseInput, this);
        ValidationUtil.validateValues(baseInput.getModifiers(), Collections.singletonList(value));
    }
}