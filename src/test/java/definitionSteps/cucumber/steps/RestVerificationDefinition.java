package definitionSteps.cucumber.steps;

import cucumber.api.java.en.Then;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter.utils.ScenarioSession;
import twitter.utils.Util;
import twitter.webservices.TwitterResponse;

import java.util.List;
import java.util.stream.Collectors;

public class RestVerificationDefinition {

    Logger LOG = LoggerFactory.getLogger(this.getClass());


    @Then("^Field 'text' is not empty in all records$")
    public void fieldTextContainsTestInAllRecords() throws Throwable {
        SoftAssertions softAssertions = new SoftAssertions();
        ScenarioSession.getTwitterResponseList().forEach(twitterResponse -> {
                    String text_actual = twitterResponse.getText();
                    LOG.info("Trying to check actual: {text='{}'}", text_actual);
                    softAssertions.assertThat(text_actual).isNotBlank();
                }
        );
        softAssertions.assertAll();
    }

    @Then("^Field '(.*)' equals '(.*)' in all records$")
    public void fieldEqualsToValueInAllRecords(String field, String value) throws Throwable {
        SoftAssertions softAssertions = new SoftAssertions();
        ScenarioSession.getTwitterResponseList().forEach(twitterResponse -> {
                    String value_actual = twitterResponse.getFieldValue(field);
                    LOG.info("Trying to check actual: {text='{}'}", value_actual);
                    softAssertions.assertThat(value_actual).isEqualTo(value);
                }
        );
        softAssertions.assertAll();
    }

    @Then("^status code should be: (\\d+)$")
    public void checkStatusCode(int statusCode) throws Throwable {
        Assertions.assertThat(ScenarioSession.getResponseWrapper()
                .getStatusCode()).isEqualTo(statusCode);
    }

    @Then("^Field 'created_at' is correct$")
    public void fieldCreated_atIsCorrect() throws Throwable {
        TwitterResponse twitterResponse = ScenarioSession.getTwitterResponseList().get(0);
        String created_at_actual = twitterResponse.getCreated_at();
        String timeStamp = ScenarioSession.getLastDate();
        LOG.info("Trying to check actual: {created_at='{}'} ", created_at_actual);
        Assert.assertEquals(timeStamp, created_at_actual);
    }

    @Then("^Field 'retweet_count' is '(\\d+)'$")
    public void fieldRetweet_countIs(int count) throws Throwable {
        TwitterResponse twitterResponse = Util.getLast(ScenarioSession.getTwitterResponseList());
        int retweet_count = Integer.valueOf(twitterResponse
                .getRetweet_count());
        LOG.info("Trying to check actual: {retweet_count='{}'} ", retweet_count);
        Assert.assertEquals(count, retweet_count);
    }

    @Then("^Field 'text' is '(.*)'$")
    public void fieldTextIsTest(String text) throws Throwable {
        TwitterResponse twitterResponse = ScenarioSession.getTwitterResponseList().get(0);
        String text_actual = twitterResponse.getText();
        long timestamp = ScenarioSession.getTimestamp();
        LOG.info("Trying to check actual: {text='{}'}", text_actual);
        Assert.assertEquals(text + timestamp, text_actual);
    }

    @Then("^There is no posted record in response$")
    public void thereIsNoPostedRecordInResponse() throws Throwable {
        List<TwitterResponse> twitterResponseList = ScenarioSession.getTwitterResponseList();
        List<String> idList = twitterResponseList.stream().map(TwitterResponse::getId).collect(Collectors.toList());
        Assert.assertFalse(idList.contains(ScenarioSession.getLastID()));
    }

    @Then("^There is posted record in response$")
    public void thereIsPostedRecordInResponse() throws Throwable {
        List<TwitterResponse> twitterResponseList = ScenarioSession.getTwitterResponseList();
        List<String> idList = twitterResponseList.stream().map(TwitterResponse::getId).collect(Collectors.toList());
        Assert.assertTrue(idList.contains(ScenarioSession.getLastID()));
    }

    @Then("^Text is present in API get request$")
    public void textIsPresentInAPIGetRequest() throws Throwable {
        TwitterResponse twitterResponse = ScenarioSession.getTwitterResponseList().get(0);
        String expected = Util.getLast(ScenarioSession.getListOfPostedText());
        Assert.assertEquals(expected, twitterResponse.getText());
    }

}
