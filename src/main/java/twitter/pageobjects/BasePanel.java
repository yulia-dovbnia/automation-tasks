package twitter.pageobjects;

import ch.lambdaj.Lambda;
import ch.lambdaj.function.convert.Converter;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.pages.WebElementFacadeImpl;
import net.thucydides.core.annotations.locators.SmartElementLocatorFactory;
import net.thucydides.core.annotations.locators.SmartFieldDecorator;
import net.thucydides.core.webdriver.MobilePlatform;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.FieldDecorator;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static net.serenitybdd.core.selectors.Selectors.isXPath;

public class BasePanel {

    private BasePage page;
    private WebDriverAdapter webDriverAdapter;
    private int implicityTimeout = 1000;

    public BasePanel(final WebElementFacade panelBaseLocation, final BasePage page) {
        initPanel(panelBaseLocation, page);
    }

    private void initPanel(final WebElementFacade panelBaseLocation, final BasePage page) {
        this.page = page;
        this.webDriverAdapter = new WebDriverAdapter(panelBaseLocation, getDriver());
        ElementLocatorFactory finder = new SmartElementLocatorFactory(webDriverAdapter, MobilePlatform.NONE,
                (int) page.getImplicitWaitTimeout().in(TimeUnit.SECONDS));
        FieldDecorator decorator = new SmartFieldDecorator(finder, getDriver(), page);
        PageFactory.initElements(decorator, this);
    }

    protected WebElementFacade findBy(final String xpathOrCssSelector) {
        final WebElement webElement = webDriverAdapter.findElement(xpathOrCssSelector(xpathOrCssSelector));
        return WebElementFacadeImpl.wrapWebElement(getDriver(), webElement, implicityTimeout);
    }

    protected List<WebElementFacade> findMultipleBy(final String xpathOrCssSelector) {
        getDriver().manage().timeouts().implicitlyWait(implicityTimeout, MILLISECONDS);
        List<WebElement> matchingWebElements;
        try {
            matchingWebElements = webDriverAdapter.findElements(xpathOrCssSelector(xpathOrCssSelector));
        } finally {
            getPage().resetImplicitTimeout();
        }
        return Lambda.convert(matchingWebElements, toWebElementFacades());
    }

    private By xpathOrCssSelector(final String xpathOrCssSelector) {
        By locator;
        if (isXPath(xpathOrCssSelector)) {
            locator = By.xpath(xpathOrCssSelector);
        } else {
            locator = By.cssSelector(xpathOrCssSelector);
        }
        return locator;
    }

    private Converter<WebElement, WebElementFacade> toWebElementFacades() {
        return this::element;
    }

    private WebElementFacade element(final WebElement webElement) {
        return WebElementFacadeImpl.wrapWebElement(getDriver(), webElement, implicityTimeout);
    }


    public WebDriver getDriver() {
        return page.getDriver();
    }

    public BasePage getPage() {
        return page;
    }
}
