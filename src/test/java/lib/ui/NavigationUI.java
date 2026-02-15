package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class NavigationUI extends MainPageObject{

    protected static String
            SAVED_ARTICLES_BUTTON,
            OPEN_NAVIGATION;

    public NavigationUI(RemoteWebDriver driver)
    {
        super(driver);
    }

    public void openNavigation()
    {
        if(Platform.getInstance().isMW()) {
            this.waitForElementAndClick(OPEN_NAVIGATION,
                    "Cannot find and click open navigation button",
                    15);
        }
        else {
            System.out.println("Method openNavigation() does nothing for platform " +
                    Platform.getInstance().getPlatformVar());
        }
    }

    public void clickSavedArticles()
    {
        if(Platform.getInstance().isMW())
        {
            this.tryClickElementWithFewAttempts(SAVED_ARTICLES_BUTTON,
                    "Cannot find 'Saved' button",
                    5);
            {

            }
        }
        if((Platform.getInstance().isIos()) || Platform.getInstance().isAndroid())
            {
            this.waitForElementAndClick((SAVED_ARTICLES_BUTTON),
                "Cannot find 'Saved' button",
                15);
            }
            else{
            System.out.println("Method clickSavedArticles() does nothing for platform " +
                    Platform.getInstance().getPlatformVar());
            }
    }


}
