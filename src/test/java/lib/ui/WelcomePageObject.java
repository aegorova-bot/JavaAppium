package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WelcomePageObject extends MainPageObject {

    private static final String
        STEP_FREE_ENCYCLOPEDIA_TITLE = "id:The free encyclopedia",
        STEP_NEW_WAYS_TO_EXPLORE_TITLE = "id:New ways to explore",
        STEP_SEARCH_IN_LANGUAGES_TITLE = "id:Search in nearly 300 languages",
        STEP_HELP_MAKE_APP_BETTER_TITLE = "id:Help make the app better",
        NEXT_BUTTON = "ios_predicate:name == \"Next\" AND label == \"Next\" AND value == \"Next\"",
        GET_STARTED_BUTTON = "ios_predicate:name == \"Get started\" AND label == \"Get started\" AND value == \"Get started\"",
        SKIP_IOS = "ios_predicate:name == 'Skip' AND label == 'Skip' AND type == 'XCUIElementTypeButton'",
        SKIP_ANDROID = "id:org.wikipedia:id/fragment_onboarding_skip_button";

    public WelcomePageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

    public void waitForTheFreeEncyclopedia()
    {
        this.waitForElementPresent((STEP_FREE_ENCYCLOPEDIA_TITLE),
                "Cannot find 'The free encyclopedia' title",
                15);
    }
    public void waitForNewWaysToExploreText()
    {
        this.waitForElementPresent((STEP_NEW_WAYS_TO_EXPLORE_TITLE),
                "Cannot find 'New ways to explore' title",
                15);
    }
    public void waitForSearchInNearlyLanguages()
    {
        this.waitForElementPresent((STEP_SEARCH_IN_LANGUAGES_TITLE),
                "Cannot find 'Search in nearly 300 languages' title",
                15);
    }
    public void waitForHelpMakeTheAppBetter()
    {
        this.waitForElementPresent((STEP_HELP_MAKE_APP_BETTER_TITLE),
                "Cannot find 'Help make the app better' title",
                15);
    }
    public void clickNextButton()
    {
        this.waitForElementAndClick((NEXT_BUTTON),
                "Cannot find 'Next' button",
                15);
    }
    public void clickGetStartedButton()
    {
        this.waitForElementAndClick((GET_STARTED_BUTTON),
                "Cannot find 'Get Started' button",
                15);
    }

    public void clickSkipIOS()
    {
        this.waitForElementAndClick(SKIP_IOS,
                "Cannot find and click skip button",
                15);
    }

    public void clickSkipAndroid()
    {
        this.waitForElementAndClick(SKIP_ANDROID,
                "Cannot find and click skip button",
                15);
    }
}
