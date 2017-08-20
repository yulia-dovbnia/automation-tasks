package definitionSteps;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.After;
import org.junit.runner.RunWith;
import twitter.pageobjects.BasePage;

@RunWith(Cucumber.class)
@CucumberOptions(
        format = {"pretty"},
        features = "src/test/resources/feature/cucumber",
        glue = "cucumber.steps")
public class AcceptanceTest {


   /* @After
    protected void tearDown() throws Exception {
        if (null != BasePage.driver) {
            BasePage.driver.quit();
        }


    }*/

}
