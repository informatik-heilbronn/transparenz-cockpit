package de.hhn.seb.labsw.transparentcockpit.backend.models.project.template.input;

import de.hhn.seb.labsw.transparentcockpit.backend.models.project.base.input.BaseInput;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.base.input.DataType;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.base.input.InputModifier;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.base.input.InputType;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.util.ValidationUtil;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Project Report InputTemplate of Type MultiInputTemplate.
 * MultiInput:
 * - List of Input Values
 */
public class MultiInputTemplate extends BaseInput {

    public MultiInputTemplate(String number, String name,
                              Set<InputModifier> modifiers,
                              DataType type) {
        super(number, name, modifiers, type, InputType.MULTI_INPUT);

        ValidationUtil.validateModifier(this.getClass().getName(), modifiers, InputModifier.REQUIRED);
    }

}