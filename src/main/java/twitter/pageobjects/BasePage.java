package twitter.pageobjects;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BasePage extends PageObject {

    private static final WebDriver CHROME_DRIVER = new ChromeDriver();

    public BasePage() {
        super(CHROME_DRIVER);
    }

    public void refreshPage() {
        getDriver().navigate().refresh();
        this.waitForWithRefresh();
    }
}
