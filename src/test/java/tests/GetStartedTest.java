package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.WelcomePageObject;
import org.junit.Test;

public class GetStartedTest extends CoreTestCase {

    @Test
    public void testPassThroughWelcome()
    {
        if((Platform.getInstance().isAndroid()) || (Platform.getInstance().isMW()))
        {
            return;
        }

        WelcomePageObject WelcomePageObject = new WelcomePageObject(driver);
        WelcomePageObject.waitForTheFreeEncyclopedia();
        WelcomePageObject.clickNextButton();

        WelcomePageObject.waitForNewWaysToExploreText();
        WelcomePageObject.clickNextButton();

        WelcomePageObject.waitForSearchInNearlyLanguages();
        WelcomePageObject.clickNextButton();

        WelcomePageObject.waitForHelpMakeTheAppBetter();
        WelcomePageObject.clickGetStartedButton();
    }
}
