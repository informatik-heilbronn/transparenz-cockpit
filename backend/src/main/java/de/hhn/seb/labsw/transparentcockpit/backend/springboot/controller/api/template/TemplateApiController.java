package de.hhn.seb.labsw.transparentcockpit.backend.springboot.controller.api.template;

import de.hhn.seb.labsw.transparentcockpit.backend.buisnesslogic.ProjectReportTemplateService;
import de.hhn.seb.labsw.transparentcockpit.backend.buisnesslogic.SystemSettingsService;
import de.hhn.seb.labsw.transparentcockpit.backend.exceptions.reporitory.NotFoundException;
import de.hhn.seb.labsw.transparentcockpit.backend.exceptions.validation.ValueException;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.template.ProjectReportTemplate;
import de.hhn.seb.labsw.transparentcockpit.backend.models.system.SystemSetting;
import de.hhn.seb.labsw.transparentcockpit.backend.springboot.internal.mapper.ProjectReportTemplateSbMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@RestController
@RequestMapping("/api/v1/template")
public class TemplateApiController {


    private final ProjectReportTemplateService projectReportTemplateService;
    private final SystemSettingsService systemSettingsService;


    public TemplateApiController(@Autowired ProjectReportTemplateService projectReportTemplateService,
                                 @Autowired SystemSettingsService systemSettingsService) {
        this.projectReportTemplateService = projectReportTemplateService;
        this.systemSettingsService = systemSettingsService;
    }


    @PostMapping("create")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Map<String, String>> createTemplate(
            @RequestBody Map<String, Object> input,
            @PathParam("default") boolean setDefault
    ) {
        // Generate Random UUID for Template ID
        UUID reportTemplateId = UUID.randomUUID();

        // Mapping
        ProjectReportTemplate projectReportTemplate;
        try {
            projectReportTemplate = ProjectReportTemplateSbMapper.toProjectReportTemplate(reportTemplateId, input);
        } catch (IllegalArgumentException | ValueException e) {
            Map<String, String> returnObject = new HashMap<>();
            returnObject.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(returnObject);
        }

        // Validation
        try {
            projectReportTemplate.validateTemplate();
        } catch (IllegalArgumentException e) {
            Map<String, String> returnObject = new HashMap<>();
            returnObject.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(returnObject);
        }

        // Save
        projectReportTemplateService.saveProjectReportTemplate(projectReportTemplate);


        // Set Default
        if (setDefault) {
            SystemSetting systemSetting = new SystemSetting("DEFAULT_TEMPLATE_ID", reportTemplateId.toString());
            systemSettingsService.updateSetting(systemSetting);
        }

        // Return Object
        Map<String, String> returnObject = new HashMap<>();
        returnObject.put("templateId", reportTemplateId.toString());
        return ResponseEntity.ok(returnObject);
    }

    @GetMapping("default")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Map<String, String>> getTemplate() {
        String result;
        try {
            result = systemSettingsService.loadSetting("DEFAULT_TEMPLATE_ID").getValue();
        } catch (NotFoundException e) {
            Map<String, String> returnObject = new HashMap<>();
            returnObject.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnObject);
        }

        // Return Object
        HashMap<String, String> returnObject = new HashMap<>();
        returnObject.put("templateId", result);
        return ResponseEntity.ok(returnObject);
    }

    @GetMapping("get/{templateId}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Object> getTemplate(
            @PathVariable(value = "templateId", required = true) UUID templateId
    ) {
        ProjectReportTemplate result;
        try {
            result = projectReportTemplateService.getProjectReportTemplateById(templateId);
        } catch (NotFoundException e) {
            Map<String, String> returnObject = new HashMap<>();
            returnObject.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnObject);
        }

        // Found Template
        return ResponseEntity.ok(result);
    }
}
