package de.hhn.seb.labsw.transparentcockpit.backend.models.project.base.group;

import de.hhn.seb.labsw.transparentcockpit.backend.models.project.base.input.BaseInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/**
 * Project Report Group.
 */
public class Section {

    private final Logger methodeLogger =
            LoggerFactory.getLogger(Section.class.getName() + "Methods");


    // Identifier
    private final String letter;

    // Name
    private final String name;

    // Content
    private final Map<String, BaseInput> fields;


    /**
     * Constructor.
     *
     * @param letter Letter of the Group
     * @param name   Name of the Group
     */
    public Section(String letter, String name) {
        methodeLogger.debug("call Group(letter: '{}', name: '{}')", letter, name);

        this.letter = letter;
        this.name = name;
        this.fields = new TreeMap<>();
    }

    public String getLetter() {
        methodeLogger.debug("call getLetter()");

        return letter;
    }

    public String getName() {
        methodeLogger.debug("call getName()");

        return name;
    }

    public void addField(BaseInput baseInput) throws IllegalArgumentException {
        methodeLogger.debug("call addField(baseInput: '{}')", baseInput);

        if (fields.containsKey(baseInput.getNumber())) {
            throw new IllegalArgumentException("FieldNumber is already in use");
        }

        fields.put(baseInput.getNumber(), baseInput);
    }

    public Map<String, BaseInput> getFields() {
        methodeLogger.debug("call getFields()");

        return fields;
    }

    /**
     * Validates the Group and all their Fields.
     *
     * @param groupTemplate Template witch the Input is validated against
     * @throws IllegalArgumentException text
     */
    public void validateGroup(Section groupTemplate) throws IllegalArgumentException {
        if (!Objects.equals(letter, groupTemplate.getLetter())) {
            throw new IllegalArgumentException("TemplateGroup and Group dont match."
                    + "\n Gotten Letter" + groupTemplate.getLetter() + " but expected " + this.getLetter());
        }
        if (!Objects.equals(name, groupTemplate.getName())) {
            throw new IllegalArgumentException("TemplateGroup and Group dont match."
                    + "\n Gotten Letter" + groupTemplate.getName() + " but expected " + this.getName());
        }
        groupTemplate.getFields().forEach((fieldNumber, field) -> {
            if (!this.fields.containsKey(field.getNumber())) {
                throw new IllegalArgumentException("TemplateGroup:Input and Group:Input dont match."
                        + "\n Gotten FieldNumber " + fieldNumber + " but does not exists");
            }
        });
    }
}
