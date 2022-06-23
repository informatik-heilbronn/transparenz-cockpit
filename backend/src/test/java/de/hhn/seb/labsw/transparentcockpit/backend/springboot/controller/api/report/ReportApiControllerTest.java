package de.hhn.seb.labsw.transparentcockpit.backend.springboot.controller.api.report;

import de.hhn.seb.labsw.transparentcockpit.backend.buisnesslogic.ProjectReportService;
import de.hhn.seb.labsw.transparentcockpit.backend.buisnesslogic.ProjectReportTemplateService;
import de.hhn.seb.labsw.transparentcockpit.backend.exceptions.reporitory.NotFoundException;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.base.input.DataType;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.base.input.InputModifier;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.base.input.InputType;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.report.ProjectReport;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.report.input.Input;
import de.hhn.seb.labsw.transparentcockpit.backend.springboot.internal.models.project.report.ProjectReportSb;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class ReportApiControllerTest {

    private final String INPUT_TEST_NAME_STRING = "testName";
    private final String INPUT_TEST_ID_STRING = "testID";
    private final String INPUT_TEST_GROUP_STRING = "testGroup";

    @Mock
    ProjectReportService projectReportService;

    @Captor
    ArgumentCaptor<String> stringCaptor;

    @Mock
    ProjectReportTemplateService projectReportTemplateService;

    @InjectMocks
    ReportApiController underTest;

    @BeforeEach
    void setup() {
        openMocks(this);
    }

    @Test
    void getReportWithNoReportTest() {
        //Setup Input

        //Run function
        when(projectReportService.getProjectReportById(any())).thenThrow(new NotFoundException("noReports"));
        var result = underTest.getReport(INPUT_TEST_ID_STRING);

        //Evaluate
        assertEquals(result.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    void getReportWithReportExistingTest() {
        //Setup input
        ProjectReport projectReport = setupInputProjectReport();

        //Run function
        when(projectReportService.getProjectReportById(stringCaptor.capture())).thenReturn(projectReport);
        var result = underTest.getReport(INPUT_TEST_ID_STRING);

        //Evaluate
        assertEquals(stringCaptor.getValue(), INPUT_TEST_ID_STRING);
        assertEquals(result.getStatusCode(), HttpStatus.OK);
        assertEquals(result.getStatusCodeValue(), 200);

        ProjectReportSb resultProjectReport = (ProjectReportSb) result.getBody();
        assertEquals(resultProjectReport.getProjectNumber(), INPUT_TEST_ID_STRING);
        assertEquals(resultProjectReport.getProjectGroup(), INPUT_TEST_GROUP_STRING);
        assertEquals(resultProjectReport.getProjectName(), INPUT_TEST_NAME_STRING);
    }

    ProjectReport setupInputProjectReport() {
        List<Object> valueIDList = new ArrayList<>();
        valueIDList.add(INPUT_TEST_ID_STRING);

        Input inputReportID = new Input("6.", "Vorhabennummer", true,
                DataType.STRING, InputType.SINGLE_INPUT, valueIDList);

        List<Object> valueNameList = new ArrayList<>();
        valueNameList.add(INPUT_TEST_NAME_STRING);
        Input inputReportName = new Input("1.", "Vorhabentitle", true,
                DataType.STRING, InputType.SINGLE_INPUT, valueNameList);

        List<Object> valueGroupList = new ArrayList<>();
        valueGroupList.add(INPUT_TEST_GROUP_STRING);
        List<Object> allowedValuesInputC0 = new ArrayList<>();
        Collections.addAll(allowedValuesInputC0, "Verwaltung & Infrastruktur", "Bildungs- und Wissensstadt",
                "Teilhabe an der Stadtgesellschaft", "Zukunftsfähige Mobilität");
        Input inputReportGroup = new Input("0.", "Gruppe", true,
                DataType.STRING, InputType.SELECT_SINGLE_INPUT, allowedValuesInputC0, valueGroupList);

        ProjectReport projectReport = new ProjectReport(UUID.randomUUID(), inputReportID, inputReportName, inputReportGroup);
        return projectReport;
    }
}
