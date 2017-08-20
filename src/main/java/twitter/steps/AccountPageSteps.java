package twitter.steps;


import net.thucydides.core.steps.ScenarioSteps;
import twitter.pageobjects.pages.AccountMainPage;
import twitter.pageobjects.panels.TimeLineBoxPanel;
import twitter.pageobjects.panels.TimeLineListPanel;

public class AccountPageSteps extends ScenarioSteps {

    private AccountMainPage accountMainPage = new AccountMainPage();
    private TimeLineBoxPanel timeLinePanel = accountMainPage.getTimeLinePanel();
    private TimeLineListPanel timeLineListPanel = accountMainPage.getTimeLinelistPanel();

    public boolean isLogined() {
        return accountMainPage.is_logined();
    }

    public void postTextOnThePage(String text) {
        timeLinePanel.clickInTextBox();
        timeLinePanel.enterText(text);
        timeLinePanel.submitTweet();
        timeLinePanel.waitForSubmitButtonDisapears();
    }

    public String getLastPostedTweetText() {
        return timeLineListPanel.getTweetsTextList().get(0);
    }

    public void refreshPage() {
        accountMainPage.refreshPage();
    }
}
