package de.hhn.seb.labsw.transparentcockpit.backend.models.project.report.input;

import de.hhn.seb.labsw.transparentcockpit.backend.exceptions.validation.ValueException;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.base.input.BaseInput;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.base.input.DataType;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.base.input.InputType;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.template.input.InputTemplate;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.util.ValidationUtil;

import java.util.Arrays;
import java.util.List;

/**
 * Project Report Input
 */
public class Input extends InputTemplate implements ValidateInput {

    private final List<Object> values;

    /**
     * Constructor.
     *
     * @param number    Number of the Input
     * @param name      Name of the Input
     * @param isRequired Is Input required
     * @param type      Type of the Input
     * @param values    Values of the MultiInput
     * @throws ValueException If something is wrong with the Value
     */
    public Input(String number, String name,
                 boolean isRequired,
                 DataType type, InputType inputType, List<Object> values) throws ValueException {
        super(number, name, isRequired, type, inputType);
        this.values = values;
    }

    /**
     * Constructor.
     *
     * @param number    Number of the Input
     * @param name      Name of the Input
     * @param isRequired Is Input required
     * @param type      Type of the Input
     * @param values    Values of the MultiInput
     * @throws ValueException If something is wrong with the Value
     */
    public Input(String number, String name,
                 boolean isRequired,
                 DataType type, InputType inputType, List<Object> allowedValues, List<Object> values) throws ValueException {
        super(number, name, isRequired, type, inputType, allowedValues);
        this.values = values;
    }

    public List<Object> getValues() {
        return this.values;
    }

    public void addValues(Object... values) throws ValueException {
        this.values.addAll(Arrays.asList(values));
    }


    @Override
    public void validateInput(BaseInput baseInput) throws IllegalArgumentException, ValueException {
        ValidationUtil.validateFields(baseInput, this);
    }
}