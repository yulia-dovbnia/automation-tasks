package definitionSteps.cucumber.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import twitter.properties.Keys;
import twitter.properties.PropReader;
import twitter.steps.AccountPageSteps;
import twitter.steps.OpenLoginPageSteps;
import twitter.utils.ScenarioSession;
import twitter.utils.Util;

import java.util.List;


public class UIDefinition {

    private OpenLoginPageSteps openLoginPageSteps = new OpenLoginPageSteps();
    private AccountPageSteps accountPageSteps = new AccountPageSteps();

    @Given("^I open main page$")
    public void iOpenMainPage() throws Throwable {
        openLoginPageSteps.userOpenFirstPage();
    }

    @Given("^I login with credentials$")
    public void iLoginWithCredentials() throws Throwable {
        openLoginPageSteps.userLoginsWithCredentials(
                PropReader.getKeyProperty(Keys.PERSONAL_LOGIN),
                PropReader.getKeyProperty(Keys.PERSONAL_PASSWORD));
    }

    @Then("^Check that I am logined$")
    public void checkThatIAmLogined() throws Throwable {
        Assert.assertTrue("Was not logined", accountPageSteps.isLogined());
    }

    @When("^I post text '(.*)'$")
    public void iPostText(String text) throws Throwable {
        String textToPost = text + Util.getTimeStamp("new");
        accountPageSteps.postTextOnThePage(textToPost);
        ScenarioSession.getListOfPostedText().add(textToPost);
    }

    @Then("^Text is present in latest post$")
    public void textIsPresentInLatestPost() throws Throwable {
        String lastPostedTweetText = accountPageSteps.getLastPostedTweetText();
        List<String> listOfPostedText = ScenarioSession.getListOfPostedText();
        String lastPostedTweetTextExpected = listOfPostedText.get(listOfPostedText.size() - 1);
        Assert.assertEquals("Latest post doesn't contain text", lastPostedTweetTextExpected, lastPostedTweetText);
    }

    @When("^I refresh page$")
    public void iRefreshPage() throws Throwable {
        accountPageSteps.refreshPage();
    }

}
