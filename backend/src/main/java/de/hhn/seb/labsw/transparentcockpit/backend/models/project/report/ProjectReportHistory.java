package de.hhn.seb.labsw.transparentcockpit.backend.models.project.report;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.TreeMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Project Report History.
 */
public class ProjectReportHistory {
  // Identifier
  private final String projectNumber;

  // Content
  private LocalDateTime lastEdit;
  private final Map<LocalDateTime, ProjectReport> historyMap;


  /**
   * Construtor.
   *
   * @param projectNumber Number of the Project
   */
  public ProjectReportHistory(String projectNumber) {
    this.projectNumber = projectNumber;
    this.historyMap = new TreeMap<>();
  }

  public String getProjectNumber() {
    return projectNumber;
  }

  public LocalDateTime getLastEdit() {
    return lastEdit;
  }

  /**
   * Adding ProjectReport to the History.
   *
   * @param localDateTime when the Changes took place
   * @param projectReport the Change which were made
   */
  public void addHistory(LocalDateTime localDateTime, ProjectReport projectReport) {
    lastEdit = localDateTime;
    historyMap.put(localDateTime, projectReport);
  }

  public Map<LocalDateTime, ProjectReport> getHistoryMap() {
    return historyMap;
  }

}
