package twitter.pageobjects.panels;

import net.serenitybdd.core.pages.WebElementFacade;
import twitter.pageobjects.BasePage;
import twitter.pageobjects.BasePanel;

import java.util.List;
import java.util.stream.Collectors;

public class TimeLineListPanel extends BasePanel {
    public TimeLineListPanel(WebElementFacade panelBaseLocation, BasePage page) {
        super(panelBaseLocation, page);
    }

    String tweetsContentList = "./li//div[@class='content']";
    String tweetsContentTextList = "/div[@class='js-tweet-text-container']/p";

    public List<String> getTweetsTextList() {
        return findMultipleBy(tweetsContentList + tweetsContentTextList)
                .stream().map(WebElementFacade::getText).collect(Collectors.toList());
    }
}
