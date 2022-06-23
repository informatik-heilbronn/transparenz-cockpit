package de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.entity.project.base.group;

import de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.entity.project.report.BaseInputMdb;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Group Mongo Object.
 */
@Entity
public class SectionMdb {


    // internal Identifier
    @Id
    private String id;

    // Identifier
    private String letter;

    // Name
    private String name;

    // Content
    private Map<String, BaseInputMdb> fields = new HashMap<>();


    // Morphia
    public SectionMdb() {
    }

    public SectionMdb(String letter, String name) {
        this(UUID.randomUUID().toString(), letter, name, new HashMap<>());
    }

    public SectionMdb(String id, String letter, String name, Map<String, BaseInputMdb> fields) {
        this.id = id;
        this.letter = letter;
        this.name = name;
        this.fields = fields;
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

    public Map<String, BaseInputMdb> getFields() {
        return fields;
    }

    public void setFields(Map<String, BaseInputMdb> fields) {
        this.fields = fields;
    }

    public void addField(String identifier, BaseInputMdb baseInput) {
        fields.put(identifier, baseInput);
    }
}
