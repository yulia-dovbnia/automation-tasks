package definitionSteps.cucumber.steps;

import cucumber.api.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter.steps.RestSteps;
import twitter.utils.ScenarioSession;
import twitter.utils.Util;
import twitter.webservices.ResponseWrapper;
import twitter.webservices.TwitterWrapper;

import java.util.Arrays;

public class RestDefinition {
    Logger LOG = LoggerFactory.getLogger(this.getClass());
    RestSteps restSteps = new RestSteps();

    @When("^I send (.*) post request and save response into Session: '(.*)'")
    public void sendPostRequestAndSaveResponseIntoSession(String version, String value) throws Throwable {
        String textToPost = value + Util.getTimeStamp(version);
        ResponseWrapper responsewrapper = restSteps.sentUpdateRequest(textToPost);

        ScenarioSession.getListOfPostedText().add(textToPost);
        ScenarioSession.setLastID(TwitterWrapper.getTwitterResponse(responsewrapper).getId());
        ScenarioSession.setResponseWrapper(responsewrapper);
        ScenarioSession.setTwitterResponseList(
                Arrays.asList(TwitterWrapper.getTwitterResponse(responsewrapper)));
        ScenarioSession.setLastDate(TwitterWrapper.getTwitterResponse(responsewrapper).getCreated_at());
    }


    @When("^I send get request and save response into Session$")
    public void iSendNewGetRequestAndSaveResponseIntoSessionTest() throws Throwable {
        ResponseWrapper responsewrapper = restSteps.sentGetRequest();

        ScenarioSession.setResponseWrapper(responsewrapper);
        ScenarioSession.setTwitterResponseList(TwitterWrapper.getTwitterResponseList(responsewrapper));
    }

    @When("^I send destroy request and save response into Session$")
    public void iSendDestroyRequestAndSaveResponseIntoSession() throws Throwable {
        String id = Util.getLast(ScenarioSession.getTwitterResponseList()).getId();
        ResponseWrapper responseWrapper = restSteps.sentDestroyRequest(id);

        ScenarioSession.setResponseWrapper(responseWrapper);
        ScenarioSession.setLastID(id);
    }

}