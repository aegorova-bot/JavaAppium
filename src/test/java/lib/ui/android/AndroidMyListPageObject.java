package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidMyListPageObject extends MyListPageObject {

    static
    {
        FOLDER_BY_NAME = "xpath://*[contains(@text, '{FOLDER_NAME}')]";
        ARTICLE_BY_TITLE = "xpath://*[contains(@text, '{TITLE}')]";
        SYNC_YOUR_SAVED_ARTICLES = "id:Sync your saved articles?";
        POP_UP_CLOSE_BUTTON = "id:Close";
        SECOND_ARTICLE_LOCATOR = "xpath://*[contains(@text, '{SUBSTRING}')]";
    }

    public AndroidMyListPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}
