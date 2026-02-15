package lib.ui.mobile_web;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject {

    static {
        TITLE = "id:firstHeading";
        FOOTER_ELEMENT = "css:footer";
        SAVE_BUTTON = "css:a#ca-watch";
        REMOVE_FROM_LIST_BUTTON = "css:a#ca-watch[href*='action=unwatch']";

    }

    public MWArticlePageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}
