package lib.ui.mobile_web;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWSearchPageObject extends SearchPageObject
{
    static {
        SEARCH_INIT_ELEMENT = "css:button#searchIcon";
        SEARCH_INPUT = "css:input[name='search']";
        SEARCH_CANCEL_BUTTON = "css:button[aria-label='Close search dialog']";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://bdi[contains(text(),'{SUBSTRING}')]";;
        SEARCH_RESULT_ELEMENT = "xpath://li[@role='option']";
        SEARCH_EMPTY_RESULT_ELEMENT = "xpath://a[contains(@class,'cdx-typeahead-search__search-footer')]";
        SEARCH_RESULT_LOCATOR = "xpath://li[@role='option' and not(.//a[contains(@class,'cdx-typeahead-search__search-footer')])]";
    }

    public MWSearchPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}

