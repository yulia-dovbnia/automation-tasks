package twitter.steps;


import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import twitter.pageobjects.pages.LoginPage;
import twitter.pageobjects.pages.MainPage;
import twitter.properties.Keys;
import twitter.properties.PropReader;

public class OpenLoginPageSteps extends ScenarioSteps {

    private LoginPage loginPage;

    @Step
    public void userOpenFirstPage() {
        loginPage = new MainPage().openLoginPage();
    }

    @Step
    public void userLoginsWithCredentials(String login, String password) {
        loginPage.enter_login(PropReader.getKeyProperty(Keys.PERSONAL_LOGIN));
        loginPage.enter_password(PropReader.getKeyProperty(Keys.PERSONAL_PASSWORD));
        loginPage.click_submit();
    }
}
