import de.hhn.seb.labsw.transparentcockpit.backend.buisnesslogic.ProjectReportService;
import de.hhn.seb.labsw.transparentcockpit.backend.springboot.controller.api.report.ReportApiController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.MockitoAnnotations.openMocks;

public class ExampleTest {

    //add Mocks
    @Mock
    ProjectReportService projectReportService;      //Just a example

    //change to the class under Test
    @InjectMocks
    ReportApiController underTest;                  //Just a example

    @BeforeEach
    void setup() {
        openMocks(this);
    }

    @Test
    void xxxTest() {
        //Setup Input

        //Run function

        //Evaluate
    }
}
