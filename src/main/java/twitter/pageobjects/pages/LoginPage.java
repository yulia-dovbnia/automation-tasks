package twitter.pageobjects.pages;


import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import twitter.pageobjects.BasePage;

public class LoginPage extends BasePage {

    LoginPage() {
        super();
    }

    @FindBy(xpath = ".//input[@id='signin-email']")
    private WebElementFacade loginInput;

    @FindBy(xpath = ".//input[@id='signin-password']")
    private WebElementFacade passwordInput;

    @FindBy(xpath = ".//button[@type='submit']")
    private WebElementFacade submitButton;


    public void enter_login(String text) {
        loginInput.type(text);
    }

    public void enter_password(String text) {
        passwordInput.type(text);
    }

    public AccountMainPage click_submit() {
        submitButton.click();
        return new AccountMainPage();
    }
}
