package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class SearchPageObject extends MainPageObject{

    protected static String
        SEARCH_INIT_ELEMENT,
        SEARCH_INPUT,
        SEARCH_CANCEL_BUTTON,
        SEARCH_RESULT_BY_SUBSTRING_TPL,
        SEARCH_RESULT_ELEMENT,
        SEARCH_EMPTY_RESULT_ELEMENT,
        SEARCH_RESULT_LOCATOR;

    public SearchPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
    /*TEMPLATE METHODS*/
    private static String getResultSearchElement(String substring)
    {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    /*TEMPLATE METHODS*/

    @Step("Wait for cancel Button to appear")
    public void waitForCancelButtonToAppear()
    {
        this.waitForElementPresent((SEARCH_CANCEL_BUTTON),
                "Cannot find search cancel button",
                15);
        screenshot(this.takeScreenshot("cancel button appears"));
    }

    @Step("Wait for cancel Button to disappear")
    public void waitForCancelButtonToDisappear()
    {
        this.waitForElementNotPresent((SEARCH_CANCEL_BUTTON),
                "Search cancel button still present",
                15);
    }

    @Step("Click cancel search button")
    public void clickCancelSearch()
    {
        this.waitForElementAndClick((SEARCH_CANCEL_BUTTON),
                "Cannot find and click search cancel button",
                15);
    }

    @Step("Finding search input and click it")
    public void initSearchInput()
    {
        this.waitForElementPresent((SEARCH_INIT_ELEMENT),
                "Cannot find search input after clicking search init element",
                15);

        this.waitForElementAndClick((SEARCH_INIT_ELEMENT),
                "Cannot find and click search init element",
                15);
    }

    @Step("Typing '{search_line}' in search input")
    public void typeSearchLine(String search_line)
    {
        this.waitForElementAndSendKeys((SEARCH_INPUT), search_line,
                "Cannot find and type into search input",
                15);
    }

    @Step("Wait for search results")
    public void waitForSearchResult(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent((search_result_xpath),
                "Cannot find search result with substring " + substring,
                15);
    }

    @Step("Click by article that we searching for")
    public void clickByArticleWithSubstring(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick((search_result_xpath),
                "Cannot find and click search result with substring " + substring,
                15);
    }

    @Step("Get amount of found articles")
    public int getAmountOfFoundArticles()
    {
        this.waitForElementPresent((SEARCH_RESULT_ELEMENT),
                "Cannot find anythinng by the request ",
                15);
        return this.getAmountOfElements((SEARCH_RESULT_ELEMENT));
    }

    @Step("Wait for label about empty results")
    public void waitForEmptyResultsLabel()
    {
        this.waitForElementPresent((SEARCH_EMPTY_RESULT_ELEMENT),
                "Cannot find empty result element",
                15);
    }

    @Step("Assert there is no result of search")
    public void assertThereIsNoResultOfSearch()
    {
        this.assertElementNotPresent((SEARCH_RESULT_LOCATOR),
                "We found some results");
    }

    @Step("Clearing search input")
    public void waitForElementAndClear()
    {
        this.waitForElementAndClear((SEARCH_INPUT),
                "Cannot find search field and clear it",
                15
        );
    }

}
