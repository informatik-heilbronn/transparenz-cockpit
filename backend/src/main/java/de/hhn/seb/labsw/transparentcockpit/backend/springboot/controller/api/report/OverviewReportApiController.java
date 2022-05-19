package de.hhn.seb.labsw.transparentcockpit.backend.springboot.controller.api.report;

import de.hhn.seb.labsw.transparentcockpit.backend.buisnesslogic.ProjectReportService;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.report.ProjectReport;
import de.hhn.seb.labsw.transparentcockpit.backend.springboot.internal.mapper.ProjectReportSbMapper;
import de.hhn.seb.labsw.transparentcockpit.backend.springboot.internal.models.project.report.ProjectReportSb;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Report Overview Controller.
 */
@RestController
@RequestMapping("/api/v1/report/overview")
public class OverviewReportApiController {


  protected final ProjectReportService projectReportService;


  public OverviewReportApiController(@Autowired ProjectReportService projectReportService) {
    this.projectReportService = projectReportService;
  }


  @GetMapping("")
  @CrossOrigin(origins = "*")
  public ResponseEntity<Map<String, List<ProjectReportSb>>> getAllReports() {
    List<ProjectReport> results = projectReportService.getAllProjectReports();

    // No Reports Found
    if (results.isEmpty()) {
      return ResponseEntity.ok(Collections.emptyMap());
    }

    // Found Reports
    List<ProjectReportSb> convertedProjectReports = results.stream()
        .map(ProjectReportSbMapper::convertToSb)
        .collect(Collectors.toList());

    Map<String, List<ProjectReportSb>> returnObject = new HashMap<>();
    for (ProjectReportSb convertedProjectReport : convertedProjectReports) {
      if (!returnObject.containsKey(convertedProjectReport.getProjectGroup())) {
        List<ProjectReportSb> list = new ArrayList<>();
        returnObject.put(convertedProjectReport.getProjectGroup(), list);
      }

      returnObject.get(convertedProjectReport.getProjectGroup()).add(convertedProjectReport);
    }
    return ResponseEntity.ok(returnObject);
  }

}
