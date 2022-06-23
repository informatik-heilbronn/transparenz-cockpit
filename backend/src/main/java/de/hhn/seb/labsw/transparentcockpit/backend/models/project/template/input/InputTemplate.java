package de.hhn.seb.labsw.transparentcockpit.backend.models.project.template.input;

import de.hhn.seb.labsw.transparentcockpit.backend.exceptions.validation.ValueException;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.base.input.BaseInput;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.base.input.DataType;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.base.input.InputType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Project Report InputTemplate
 */
public class InputTemplate extends BaseInput {

    private List<Object> allowedValues;

    /**
     * Constructor.
     *
     * @param number    Number of the Input
     * @param name      Name of the Input
     * @param isRequired Is Input required
     * @param type      Type of the Input
     * @throws ValueException If something is wrong with the Value
     */
    public InputTemplate(String number, String name,
                         boolean isRequired,
                         DataType type, InputType inputType) throws ValueException {
        super(number, name, isRequired, type, inputType);
        this.allowedValues =  new ArrayList<>();
    }

    public InputTemplate(String number, String name,
                         boolean isRequired,
                         DataType type, InputType inputType, List<Object> allowedValues) throws ValueException {
        super(number, name, isRequired, type, inputType);
        this.allowedValues =  allowedValues;
    }


    public void addAllowedValue(Object... allowedValues) {

        this.allowedValues.addAll(Arrays.asList(allowedValues));
    }

    public List<Object> getAllowedValues() {

        return allowedValues;
    }
}