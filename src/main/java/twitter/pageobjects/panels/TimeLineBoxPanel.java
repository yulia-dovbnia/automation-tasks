package twitter.pageobjects.panels;

import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.ui.ExpectedConditions;
import twitter.pageobjects.BasePage;
import twitter.pageobjects.BasePanel;

public class TimeLineBoxPanel extends BasePanel {

    private String tweetTextBox = ".//div[@id='tweet-box-home-timeline']";
    private String tweetSubmitButton = ".//button[@class='tweet-action EdgeButton EdgeButton--primary js-tweet-btn']";

    public TimeLineBoxPanel(WebElementFacade panelBaseLocation, BasePage page) {
        super(panelBaseLocation, page);
    }

    public void clickInTextBox() {
        findBy(tweetTextBox).click();
    }

    public void enterText(String text) {
        findBy(tweetTextBox).sendKeys(text);
    }

    public void submitTweet() {
        findBy(tweetSubmitButton).click();
    }

    public void waitForSubmitButtonDisapears() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
