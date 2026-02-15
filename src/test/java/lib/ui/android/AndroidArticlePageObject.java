package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidArticlePageObject extends ArticlePageObject {

    static {
        TITLE = "xpath://*[contains(@text, '{SUBSTRING}')]";
        FOOTER_ELEMENT = "xpath://*[contains(@text, 'View article in browser')]";
        SAVE_BUTTON = "id:org.wikipedia:id/page_save";
        ADD_TO_LIST_BUTTON = "id:org.wikipedia:id/snackbar_action";
        NAME_FIELD = "id:org.wikipedia:id/text_input";
        OK_BUTTON = "id:android:id/button1";
        BACK_BUTTON = "xpath://android.widget.ImageButton[@content-desc=\"Navigate up\"]";
        EXISTING_LIST = "id:org.wikipedia:id/item_title";
    }

    public AndroidArticlePageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}
