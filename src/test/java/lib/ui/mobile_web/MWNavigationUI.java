package lib.ui.mobile_web;

import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWNavigationUI extends NavigationUI {
    static
    {
        SAVED_ARTICLES_BUTTON = "css:a[href*='Special:EditWatchlist']";
        OPEN_NAVIGATION = "id:mw-mf-main-menu-button";
    }

    public MWNavigationUI(RemoteWebDriver driver)
    {
        super(driver);
    }
}
