package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Features;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

@Epic("Search tests")
public class SearchTests extends CoreTestCase {

    @Test
    @Feature(value = "Search")
    @DisplayName("Search test")
    @Description("Test types search request and wait for search result")
    public void testSearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }

    @Test
    @Feature(value = "Search")
    @DisplayName("Cancel search")
    @Description("Test types search request and cancel search")
    public void testCanselSearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    @Feature(value = "Search")
    @DisplayName("Amount of not empty search")
    @Description("Test checks if amount of search results not less than 0")
    public void testAmountOfNotEmptySearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        String search_line = "CJD Christophorusschul";
        SearchPageObject.typeSearchLine(search_line);
        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();

        Assert.assertTrue("We found too few results",
                amount_of_search_results > 0);
    }

    @Test
    @Feature(value = "Search")
    @DisplayName("Amount of empty search")
    @Description("Test checks if there are no search results")
    public void testAmountOfEmptySearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        String search_line = "khgfkhgfghkfhgf";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.assertThereIsNoResultOfSearch();
    }

//    @Test
//    @Feature(value = "Search")
//    @DisplayName("Cancel search DZ")
//    @Description("Test searches, wait for results and cancel search")
//    public void testCanselSearchDz()
//    {
//        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
//        SearchPageObject.initSearchInput();
//        String search_line = "Kotlin";
//        SearchPageObject.typeSearchLine(search_line);
//        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();
//        Assert.assertTrue("We found one or less results",
//                amount_of_search_results > 1);
//        SearchPageObject.waitForElementAndClear();
//        SearchPageObject.assertThereIsNoResultOfSearch();
//    }
}
