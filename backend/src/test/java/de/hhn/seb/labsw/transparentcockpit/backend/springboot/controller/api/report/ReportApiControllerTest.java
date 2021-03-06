package de.hhn.seb.labsw.transparentcockpit.backend.springboot.controller.api.report;

import de.hhn.seb.labsw.transparentcockpit.backend.buisnesslogic.ProjectReportService;
import de.hhn.seb.labsw.transparentcockpit.backend.buisnesslogic.ProjectReportTemplateService;
import de.hhn.seb.labsw.transparentcockpit.backend.exceptions.reporitory.NotFoundException;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.base.group.Group;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.base.input.DataType;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.base.input.InputModifier;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.report.ProjectReport;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.report.input.SingleInput;
import de.hhn.seb.labsw.transparentcockpit.backend.springboot.internal.models.project.report.ProjectReportSb;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

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
        ProjectReport projectReport = new ProjectReport(UUID.randomUUID());

        Group groupA = new Group("A.", "Vorhabenauftrag");
        Set<InputModifier> modifiersInput = new HashSet<>();
        modifiersInput.add(InputModifier.REQUIRED);
        modifiersInput.add(InputModifier.PROJECT_NAME);
        SingleInput inputA1 = new SingleInput("1.", "Vorhabentitle", modifiersInput,
                DataType.STRING, INPUT_TEST_NAME_STRING);
        groupA.addField(inputA1);

        Set<InputModifier> modifiersInputA6 = new HashSet<>();
        modifiersInputA6.add(InputModifier.REQUIRED);
        modifiersInputA6.add(InputModifier.PROJECT_ID);
        modifiersInputA6.add(InputModifier.AUTOGENERATED);
        SingleInput inputA6 = new SingleInput("6.", "Vorhabennummer", modifiersInputA6,
                DataType.STRING, INPUT_TEST_ID_STRING);
        groupA.addField(inputA6);
        projectReport.addGroup(groupA);

        Group groupC = new Group("C.", "Vorhabenkontext");

        Set<InputModifier> modifiersInputC0 = new HashSet<>();
        modifiersInputC0.add(InputModifier.REQUIRED);
        modifiersInputC0.add(InputModifier.PROJECT_GROUP);
        SingleInput inputC0 = new SingleInput("0.", "Gruppe", modifiersInputC0,
                DataType.STRING, INPUT_TEST_GROUP_STRING);
        groupC.addField(inputC0);

        projectReport.addGroup(groupC);
        return projectReport;
    }
}
