package businesstest;

import cucumber.api.CucumberOptions;
import lifecycle.SerenityReports;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        plugin = {"pretty"},
        features = "classpath:features",
        tags = {"not @wip"}
)

public class TestRunner {

    @BeforeClass
    public static void setUp() {
        SerenityReports.clean();
//        SomeEnvironmentManagementClass.someDeleteAllDataMethod(); // Won't implement due to shared environment
    }

    @AfterClass
    public static void tearDown() {
        SerenityReports.generate();
//        SomeEnvironmentManagementClass.someDeleteAllDataMethod(); // Won't implement due to shared environment
    }
}

