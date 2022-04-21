package de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.entity.project.report;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * ProjectReportHistory Mongo Object.
 */
@Entity
public class ProjectReportHistoryMdb {


    // internal Identifier
    @Id
    private String projectReportHistoryId;

    // Identifier
    private String projectNumber;

    // Content
    private String lastEdit;
    private Map<String, ProjectReportMdb> historyMap = new HashMap<>();


    // Morphia
    public ProjectReportHistoryMdb() {
    }

    public ProjectReportHistoryMdb(String projectNumber, String lastEdit) {
        this(UUID.randomUUID().toString(), projectNumber, lastEdit, new HashMap<>());
    }

    public ProjectReportHistoryMdb(String projectReportHistoryId, String projectNumber, String lastEdit, Map<String, ProjectReportMdb> historyMap) {
        this.projectReportHistoryId = projectReportHistoryId;
        this.projectNumber = projectNumber;
        this.lastEdit = lastEdit;
        this.historyMap = historyMap;
    }


    public String getProjectReportHistoryId() {
        return projectReportHistoryId;
    }

    public void setProjectReportHistoryId(String projectReportHistoryId) {
        this.projectReportHistoryId = projectReportHistoryId;
    }

    public String getProjectNumber() {
        return projectNumber;
    }

    public void setProjectNumber(String projectNumber) {
        this.projectNumber = projectNumber;
    }

    public String getLastEdit() {
        return lastEdit;
    }

    public void setLastEdit(String lastEdit) {
        this.lastEdit = lastEdit;
    }

    public Map<String, ProjectReportMdb> getHistoryMap() {
        return historyMap;
    }

    public void setHistoryMap(Map<String, ProjectReportMdb> historyMap) {
        this.historyMap = historyMap;
    }

    public void addHistory(String offsetDateTime, ProjectReportMdb projectReportMdb) {
        this.historyMap.put(offsetDateTime, projectReportMdb);
    }
}
