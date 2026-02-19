package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import lib.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class ArticlePageObject extends MainPageObject {

    protected static String
            TITLE,
            FOOTER_ELEMENT,
            SAVE_BUTTON,
            ADD_TO_LIST_BUTTON,
            ADD_TO_LIST_BUTTON_ON_FOCUS,
            REMOVE_FROM_LIST_BUTTON,
            NAME_FIELD,
            OK_BUTTON,
            BACK_BUTTON,
            CANCEL_BUTTON,
            EXISTING_LIST;

    public ArticlePageObject (RemoteWebDriver driver)
    {
        super(driver);
    }

    /*TEMPLATE METHODS*/
    private static String getTitle(String substring)
    {
        return TITLE.replace("{SUBSTRING}", substring);
    }
    /*TEMPLATE METHODS*/

    @Step("Wait for title on article page")
    public WebElement waitForTitleElement(String substring)
    {
        String title_xpath = getTitle(substring);
        return this.waitForElementPresent((title_xpath),
                "Cannot find article title on page " + substring,
                15);
    }

    @Step("Getting title from article page")
    public String getArticleTitle(String substring)
    {
        WebElement title_element = waitForTitleElement(substring);
        screenshot(this.takeScreenshot("article_title"));
        if(Platform.getInstance().isAndroid())
        {
        return title_element.getAttribute("text");
        }
        else if(Platform.getInstance().isIos())
        {
            return title_element.getAttribute("name");
        }
        else
        {
            return title_element.getText();
        }
    }

    @Step("Swipe article to footer")
    public void swipeToFooter()
    {
        if(Platform.getInstance().isAndroid())
        {
        this.swipeUpToFindElement((FOOTER_ELEMENT),
                "Cannot find the end of article",
                20);
        } else if(Platform.getInstance().isIos())
        {
            this.SwipeUpTillElementAppear((FOOTER_ELEMENT),
                    "Cannot find the end of article",
                    40);
        }
        else
        {
            this.scrollWebPageTillElementNotVisible(FOOTER_ELEMENT,
                    "cannot find the end of article",
                    40);
        {

        };
        }
    }

    @Step("Add article to save list")
    public void addArticleToMyList(String name_of_folder)
    {
        this.waitForElementAndClick((SAVE_BUTTON),
                "Cannot find save button",
                15);

        this.waitForElementAndClick((ADD_TO_LIST_BUTTON),
                "Cannot find 'Add to list' button",
                15);

        this.waitForElementAndSendKeys((NAME_FIELD),
                name_of_folder,
                "Cannot find field 'Name'",
                15);

        this.waitForElementAndClick((OK_BUTTON),
                "Cannot find 'OK' button",
                15);
    }

    @Step("Add article to existing save list")
    public void addArticleToExistingList()
    {
        this.waitForElementAndClick((SAVE_BUTTON),
                "Cannot find save button",
                15);

        this.waitForElementAndClick((ADD_TO_LIST_BUTTON),
                "Cannot find 'Add to list' button",
                15);

        this.waitForElementAndClick((EXISTING_LIST),
                "Cannot find 'Add to list' button",
                15);

    }

    @Step("Go back to the main screen")
    public void goBackToMainScreen()
    {
        if(Platform.getInstance().isAndroid())
        {
            this.waitForElementAndClick((BACK_BUTTON),
                    "Cannot find back button",
                    15);
        } else if(Platform.getInstance().isIos())
        {
        this.waitForElementAndClick((BACK_BUTTON),
                "Cannot find back button",
                5);

        this.waitForElementAndClick((CANCEL_BUTTON),
                "Cannot find back button",
                5);
        }
        else
        {
            System.out.println("Method goBackToMainScreen() does nothing for platform " +
                    Platform.getInstance().getPlatformVar());
        }
    }

    @Step("Assert that article has title we need")
    public void articleHasTitleAssert(String substring)
    {
        String title_xpath = getTitle(substring);
        this.assertElementPresent(
                (title_xpath),
                "Article title is not present on the page"
        );
    }

    @Step("Get articled to my saved")
    public void getArticlesToMySaved()
    {
        if(Platform.getInstance().isMW())
            {
                this.removeArticleFromSavedIfItAdded();
            }
        this.waitForElementAndClick(SAVE_BUTTON,
                "Cannot find option to add article to reading list",
                15);

    }

    @Step("Remove article from saved if it is added")
    public void removeArticleFromSavedIfItAdded()
    {
        if(this.isElementPresent(REMOVE_FROM_LIST_BUTTON))
        {
            this.waitForElementAndClick(REMOVE_FROM_LIST_BUTTON,
                    "Cannot click button to remove article from saved",
                    1);
            this.waitForElementPresent(ADD_TO_LIST_BUTTON,
                    "Cannot find button to add an article to save list after removing it from this list before",
                    15);
        }
    }
}
