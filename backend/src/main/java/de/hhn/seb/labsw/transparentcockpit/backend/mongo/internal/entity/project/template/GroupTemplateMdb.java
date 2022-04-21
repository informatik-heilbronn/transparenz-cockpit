package de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.entity.project.template;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * GroupTemplate Mongo Object.
 */
@Entity
public class GroupTemplateMdb {


    // internal Identifier
    @Id
    private String id;

    // Identifier
    private String letter;

    // Name
    private String name;

    // Content
    private Map<String, BaseInputTemplateMdb> fields = new HashMap<>();


    // Morphia
    public GroupTemplateMdb() {
    }

    public GroupTemplateMdb(String letter, String name) {
        this.id = UUID.randomUUID().toString();

        this.letter = letter;
        this.name = name;

        this.fields = new HashMap<>();
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, BaseInputTemplateMdb> getFields() {
        return fields;
    }

    public void addField(String identifier, BaseInputTemplateMdb baseInput) {
        fields.put(identifier, baseInput);
    }

    public void setFields(Map<String, BaseInputTemplateMdb> fields) {
        this.fields = fields;
    }
}
