package lib.ui.mobile_web;

import lib.ui.MyListPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWMyListPageObject extends MyListPageObject {
    static
    {
        ARTICLE_BY_TITLE = "xpath://h3[text()='{TITLE}']";
        REMOVED_FROM_SAVED_BUTTON = "xpath://li[@title='{TITLE}']//span[contains(@class,'mf-icon-unStar')]";
        JAVA_ARTICLE_DATA_ID_ON_WATCHLIST = "css:li[data-id='15881']";
    }

    public MWMyListPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}
