package lib.ui;

import org.openqa.selenium.remote.RemoteWebDriver;

public class AuthorizationPageObject extends MainPageObject{
    private static final String
                LOGIN_BUTTON = "css:a.cdx-button--action-progressive",
                LOGIN_INPUT = "id:wpName1",
                PASSWORD_INPUT = "id:wpPassword1",
                SUBMIT_BUTTON = "id:wpLoginAttempt";


    public AuthorizationPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

    public void clickAuthButton()
    {
        this.waitForElementPresent(LOGIN_BUTTON,
                "Cannot find login button",
                15);
        this.waitForElementAndClick(LOGIN_BUTTON,
                "Cannot click login button",
                15);
    }

    public void enterLoginData(String login, String password)
    {
        this.waitForElementAndSendKeys(LOGIN_INPUT, login,
                "Cannot find and put login in login input",
                15);
        this.waitForElementAndSendKeys(PASSWORD_INPUT, password,
                "Cannot find and put password in password input",
                15);
    }

    public void submitForm()
    {
        this.waitForElementAndClick(SUBMIT_BUTTON,
                "Cannot find and click submit button",
                15);
    }
}
