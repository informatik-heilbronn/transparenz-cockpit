package de.hhn.seb.labsw.transparentcockpit.backend.springboot.controller.api.report;

import de.hhn.seb.labsw.transparentcockpit.backend.buisnesslogic.ProjectReportService;
import de.hhn.seb.labsw.transparentcockpit.backend.buisnesslogic.ProjectReportTemplateService;
import de.hhn.seb.labsw.transparentcockpit.backend.exceptions.reporitory.NotFoundException;
import de.hhn.seb.labsw.transparentcockpit.backend.exceptions.validation.ValueException;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.report.ProjectReport;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.template.ProjectReportTemplate;
import de.hhn.seb.labsw.transparentcockpit.backend.springboot.internal.mapper.ProjectReportSbMapper;
import de.hhn.seb.labsw.transparentcockpit.backend.springboot.internal.models.project.report.ProjectReportSb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;


@RestController
@RequestMapping("/api/v1/report")
public class ReportApiController {


    private final ProjectReportService projectReportService;
    private final ProjectReportTemplateService projectReportTemplateService;


    public ReportApiController(@Autowired ProjectReportService projectReportService,
                               @Autowired ProjectReportTemplateService projectReportTemplateService) {
        this.projectReportService = projectReportService;
        this.projectReportTemplateService = projectReportTemplateService;
    }


    @PostMapping("create")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Map<String, String>> createReport(
            @RequestBody Map<String, Object> input
    ) {
        // Generate Random UUID for Report ID
        String reportId = LocalDateTime.now().getYear() + "0" + new Random().nextInt(Integer.MAX_VALUE);

        // Mapping ProjectReportSb to ProjectReport
        ProjectReport projectReport;
        try {
            projectReport = ProjectReportSbMapper.toProjectReport(input, reportId);
        } catch (IllegalArgumentException | ValueException e) {
            Map<String, String> returnObject = new HashMap<>();
            returnObject.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(returnObject);
        }

        // Validate
        ProjectReportTemplate projectReportTemplate;
        try {
            projectReportTemplate = projectReportTemplateService.getProjectReportTemplateById(projectReport.getTemplateId());
        } catch (NotFoundException e) {
            Map<String, String> returnObject = new HashMap<>();
            returnObject.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnObject);
        }

        try {
            projectReport.validateReport(projectReportTemplate);
        } catch (IllegalArgumentException | ValueException e) {

            Map<String, String> returnObject = new HashMap<>();
            returnObject.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(returnObject);
        }

        // Store
        projectReportService.saveProjectReport(projectReport);

        // Return Object
        HashMap<String, String> returnObject = new HashMap<>();
        returnObject.put("reportId", reportId);
        return ResponseEntity.ok(returnObject);
    }

    @GetMapping("get/{reportId}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Object> getReport(
            @PathVariable(value = "reportId", required = true) String reportId
    ) {
        ProjectReport result;
        try {
            result = projectReportService.getProjectReportById(reportId);
        } catch (NotFoundException e) {
            Map<String, String> returnObject = new HashMap<>();
            returnObject.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnObject);
        }

        // Return Object
        ProjectReportSb convertedProjectReport = ProjectReportSbMapper.convertToSb(result);
        return ResponseEntity.ok(convertedProjectReport);
    }

    @PostMapping("/update/{reportId}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Map<String, String>> updateReport(
            @PathVariable(value = "reportId", required = true) String reportId,
            @RequestBody Map<String, Object> input
    ) {
        ProjectReport result;
        try {
            result = projectReportService.getProjectReportById(reportId);
        } catch (NotFoundException e) {
            Map<String, String> returnObject = new HashMap<>();
            returnObject.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnObject);
        }

        // Mapping ProjectReportSb to ProjectReport
        ProjectReport projectReport = ProjectReportSbMapper.toProjectReport(input, reportId);

        // Check if ProjectReportId has changed
        if (!Objects.equals(projectReport.getProjectNumber(), reportId)) {
            Map<String, String> returnObject = new HashMap<>();
            returnObject.put("error", "Change of the ProjectReport Id is not possible");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(returnObject);
        }

        // Validate
        ProjectReportTemplate projectReportTemplate;
        try {
            projectReportTemplate = projectReportTemplateService.getProjectReportTemplateById(projectReport.getTemplateId());
        } catch (NotFoundException e) {
            Map<String, String> returnObject = new HashMap<>();
            returnObject.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnObject);
        }

        try {
            projectReport.validateReport(projectReportTemplate);
        } catch (IllegalArgumentException e) {
            Map<String, String> returnObject = new HashMap<>();
            returnObject.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(returnObject);
        }

        // Update
        projectReportService.updateProjectReport(projectReport);

        return ResponseEntity.ok(null);
    }
}
