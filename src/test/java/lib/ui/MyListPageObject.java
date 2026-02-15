package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

import static lib.ui.ArticlePageObject.ADD_TO_LIST_BUTTON;
import static lib.ui.ArticlePageObject.ADD_TO_LIST_BUTTON_ON_FOCUS;

abstract public class MyListPageObject extends MainPageObject{

    protected static String
        FOLDER_BY_NAME,
        ARTICLE_BY_TITLE,
        SYNC_YOUR_SAVED_ARTICLES,
        POP_UP_CLOSE_BUTTON,
        BASKET_ICON,
        SECOND_ARTICLE_LOCATOR,
        JAVA_ARTICLE_DATA_ID_ON_WATCHLIST,
        REMOVED_FROM_SAVED_BUTTON;

    /*TEMPLATE METHODS*/
    private static String getFolderXpathByName(String name_of_folder)
    {
        return FOLDER_BY_NAME.replace("{FOLDER_NAME}", name_of_folder);
    }

    private static String getRemoveButtonByTitle(String article_title)
    {
        return REMOVED_FROM_SAVED_BUTTON.replace("{TITLE}", article_title);
    }

    private static String getSavedArticleXpathByTitle(String article_title)
    {
        return ARTICLE_BY_TITLE.replace("{TITLE}", article_title);
    }

    private static String getDescriptionOfArticle(String substring)
    {
        return SECOND_ARTICLE_LOCATOR.replace("{SUBSTRING}", substring);
    }
    /*TEMPLATE METHODS*/

    public MyListPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

    public void openFolderByName(String name_of_folder )
    {
        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementAndClick((folder_name_xpath),
                "Cannot find folder by name " + name_of_folder,
                15);
    }

    public void waitForArticleToAppearByTitle(String article_title)
    {
        String article_xpath  = getSavedArticleXpathByTitle(article_title);
        this.waitForElementPresent((article_xpath),
                "Cannot find saved article by title " + article_title,
                15);
    }

    public void swipeByArticleToDelete(String article_title)
    {
        String article_xpath  = getSavedArticleXpathByTitle(article_title);
        this.waitForArticleToAppearByTitle(article_title);

        if((Platform.getInstance().isIos()) || (Platform.getInstance().isAndroid()))
        {
            this.swipeElementToLeft((article_xpath),
                    "Cannot find saved article for to delete");
            if(Platform.getInstance().isIos())
            {
                this.waitForElementPresent(BASKET_ICON,
                        "Cannot find basket icon",
                        15);
                this.waitForElementAndClick(BASKET_ICON,
                        "Cannot find basket icon to delete article",
                        15);
            }
        }
        else
            {
                String remove_locator = getRemoveButtonByTitle(article_title);
                this.waitForElementAndClick(remove_locator,
                    "Cannot click button to remove article from saved",
                    15);
                this.waitForElementPresent(ADD_TO_LIST_BUTTON_ON_FOCUS,
                        "Watch button did not appear after removing article",
                        10);
             }

        if(Platform.getInstance().isMW()){
            driver.navigate().refresh();
        }

        this.waitForArticleToDisappearByTitle(article_title);

    }
    public void waitForArticleToDisappearByTitle(String article_title){

        String article_xpath  = getSavedArticleXpathByTitle(article_title);
        this.waitForElementNotPresent((article_xpath),
                "Saved article with title " + article_title + " still presents",
                15);
    }

    public void closePopUp()
    {
        this.waitForElementPresent(SYNC_YOUR_SAVED_ARTICLES,
                "Cannot find pop-up 'Sync your saved articles?' ",
                15);
        this.waitForElementAndClick(POP_UP_CLOSE_BUTTON,
                "Cannot find close button on pop-up 'Sync your saved articles?'",
                15);
    }

    public void checkTheSecondArticleIsPresent(String substring)
    {
        if((Platform.getInstance().isIos()) || Platform.getInstance().isAndroid())
        {
            String description = getDescriptionOfArticle(substring);
            waitForElementPresent(description,
                    "Cannot find the second article on view",
                    15);
        } else
        {
            waitForElementPresent(JAVA_ARTICLE_DATA_ID_ON_WATCHLIST,
                    "Cannot find article that has left",
                    15);
        }
    }

    }

