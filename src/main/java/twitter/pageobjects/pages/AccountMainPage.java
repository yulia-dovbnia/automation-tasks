package twitter.pageobjects.pages;


import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import twitter.pageobjects.BasePage;
import twitter.pageobjects.panels.DashboardProfileCardPanel;
import twitter.pageobjects.panels.TimeLineBoxPanel;
import twitter.pageobjects.panels.TimeLineListPanel;

public class AccountMainPage extends BasePage {

    public AccountMainPage() {
        super();
    }

    @FindBy(xpath = ".//*[@class='DashboardProfileCard  module']")
    private WebElementFacade dashboardProfileCard;

    @FindBy(xpath = ".//*[@class='timeline-tweet-box']")
    private WebElementFacade timeLineTweetBox;

    @FindBy(xpath = "(.//ol[@id='stream-items-id'])[1]")
    private WebElementFacade timeLineList;

    public boolean is_logined() {
        return dashboardProfileCard.isCurrentlyVisible();
    }

    public DashboardProfileCardPanel getDashboardProfileCardPanel() {
        return new DashboardProfileCardPanel(dashboardProfileCard, this);
    }

    public TimeLineBoxPanel getTimeLinePanel() {
        return new TimeLineBoxPanel(timeLineTweetBox, this);
    }

    public TimeLineListPanel getTimeLinelistPanel() {
        return new TimeLineListPanel(timeLineList, this);
    }

}
