package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSMyListPageObject extends MyListPageObject
{
        static
        {
            ARTICLE_BY_TITLE = "id:{TITLE}";
            SYNC_YOUR_SAVED_ARTICLES = "id:Sync your saved articles?";
            POP_UP_CLOSE_BUTTON = "id:Close";
            BASKET_ICON = "id:swipe action delete";
            SECOND_ARTICLE_LOCATOR = "ios_predicate:name == \"{SUBSTRING}\"";
        }

        public IOSMyListPageObject(RemoteWebDriver driver)
        {
            super(driver);
        }
}
