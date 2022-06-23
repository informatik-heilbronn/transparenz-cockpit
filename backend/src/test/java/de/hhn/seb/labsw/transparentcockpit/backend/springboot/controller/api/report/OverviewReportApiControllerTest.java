package de.hhn.seb.labsw.transparentcockpit.backend.springboot.controller.api.report;

import de.hhn.seb.labsw.transparentcockpit.backend.buisnesslogic.ProjectReportService;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.base.input.DataType;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.base.input.InputType;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.report.ProjectReport;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.report.input.Input;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class OverviewReportApiControllerTest {

    private final String INPUT_TEST_NAME_STRING = "testName";
    private final String INPUT_TEST_ID_STRING = "testID";
    private final String INPUT_TEST_GROUP_STRING = "testGroup";

    @Mock
    ProjectReportService projectReportService;

    @InjectMocks
    OverviewReportApiController underTest;

    @BeforeEach
    void setup() {
        openMocks(this);
    }

    @Test
    void getAllReportsWithNoReportsTest() {
        //Setup Input

        //Run function
        when(projectReportService.getAllProjectReports()).thenReturn(new ArrayList<>());
        var expectedResult = ResponseEntity.ok(Collections.emptyMap());

        //Evaluate
        assertEquals(expectedResult, underTest.getAllReports());
    }

    @Test
    void getAllReportsTest() {
        //Setup Input

        List<ProjectReport> reportList = new ArrayList<>();
        ProjectReport projectReport = setupInputProjectReport();
        reportList.add(projectReport);

        //Run function
        when(projectReportService.getAllProjectReports()).thenReturn(reportList);
        var result = underTest.getAllReports();

        //Evaluate
        assertEquals(result.getStatusCode(), HttpStatus.OK);
        assertEquals(result.getStatusCodeValue(), 200);
        assertTrue(result.getBody().containsKey(INPUT_TEST_GROUP_STRING));
    }

    ProjectReport setupInputProjectReport() {
        List<Object> list = new ArrayList<>();
        list.add(INPUT_TEST_ID_STRING);

        Input inputReportID = new Input("6.", "Vorhabennummer", true,
                DataType.STRING, InputType.SINGLE_INPUT, list);

        list = new ArrayList<>();
        list.add(INPUT_TEST_NAME_STRING);
        Input inputReportName = new Input("1.", "Vorhabentitle", true,
                DataType.STRING, InputType.SINGLE_INPUT, list);

        list = new ArrayList<>();
        list.add(INPUT_TEST_GROUP_STRING);
        List<Object> allowedValuesInputC0 = new ArrayList<>();
        Collections.addAll(allowedValuesInputC0, "Verwaltung & Infrastruktur", "Bildungs- und Wissensstadt",
                "Teilhabe an der Stadtgesellschaft", "Zukunftsfähige Mobilität");
        Input inputReportGroup = new Input("0.", "Gruppe", true,
                DataType.STRING, InputType.SELECT_SINGLE_INPUT, allowedValuesInputC0, list);

        ProjectReport projectReport = new ProjectReport(UUID.randomUUID(), inputReportID, inputReportName, inputReportGroup);
        return projectReport;
    }
}
