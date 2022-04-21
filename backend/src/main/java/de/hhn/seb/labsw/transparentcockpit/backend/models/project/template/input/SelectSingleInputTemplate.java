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
 * Project Report InputTemplate of Type SelectSingleInputTemplate.
 * SelectSingleInput:
 * - List of Allowed Values
 * - Single Input Value
 */
public class SelectSingleInputTemplate extends BaseInput {

    protected List<Object> allowedValues;


    /**
     * Constructor.
     *
     * @param number        Number of the Input
     * @param name          Name of the Input
     * @param modifiers     Modifiers of the Input
     * @param type          Type of the Input
     * @param allowedValues Allowed Values of the SelectSingleInputTemplate
     * @throws ValueException If something is wrong with the Value
     */
    public SelectSingleInputTemplate(String number, String name,
                                     Set<InputModifier> modifiers,
                                     DataType type, List<Object> allowedValues) {
        super(number, name, modifiers, type, InputType.SELECT_SINGLE_INPUT);

        this.allowedValues = allowedValues;

        ValidationUtil.validateModifier(this.getClass().getName(), modifiers, InputModifier.REQUIRED,
                InputModifier.PROJECT_GROUP);
        ValidationUtil.validateValues(modifiers, allowedValues);

    }

    public void addAllowedValue(Object... allowedValues) {

        this.allowedValues.addAll(Arrays.asList(allowedValues));

        ValidationUtil.validateValues(modifiers, this.allowedValues);
    }

    public List<Object> getAllowedValues() {

        return allowedValues;
    }

}
