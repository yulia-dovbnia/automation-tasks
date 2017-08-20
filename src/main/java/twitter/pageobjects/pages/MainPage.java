package twitter.pageobjects.pages;


import twitter.pageobjects.BasePage;

public class MainPage extends BasePage {

    public MainPage() {
        super();
    }

    public LoginPage openLoginPage() {
        getDriver().get("https://twitter.com/");
        return new LoginPage();
    }
}
