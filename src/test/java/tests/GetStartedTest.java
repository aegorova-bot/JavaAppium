package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Features;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.WelcomePageObject;
import org.junit.Test;

@Epic("Welcome tests")
public class GetStartedTest extends CoreTestCase {

    @Test
    @Feature(value = "Welcome")
    @DisplayName("Pass through welcome")
    @Description("Test checks if onboarding is working")
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
