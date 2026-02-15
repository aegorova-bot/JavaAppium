package lib.ui.mobile_web;

import lib.ui.MyListPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWMyListPageObject extends MyListPageObject {
    static
    {
        ARTICLE_BY_TITLE = "xpath://h3[text()='{TITLE}']";
        REMOVED_FROM_SAVED_BUTTON = "xpath://li[@title='{TITLE}']//span[contains(@class,'mf-icon-unStar')]";
    }

    public MWMyListPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}
