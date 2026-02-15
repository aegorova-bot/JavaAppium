package lib.ui;

import io.appium.java_client.MobileBy;
import lib.Platform;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import java.util.Collections;

public class MainPageObject {

    protected RemoteWebDriver driver;

    public MainPageObject(RemoteWebDriver driver)
    {
        this.driver = driver;
    }

    public WebElement waitForElementPresent(String locator, String error_message, long timeoutInSeconds)
    {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public WebElement waitForElementPresent(String locator, String error_message)
    {
        return waitForElementPresent(locator, error_message, 15);
    }

    public WebElement waitForElementAndClick(String locator, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(locator, error_message, 5);
        try {
            element.click();
        } catch (ElementClickInterceptedException e) {

            System.out.println("Standard click failed. Using JS click.");

            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].click();", element);
        }

        return element;
    }
    public WebElement waitForElementAndSendKeys(String locator, String value, String error_message, long timeoutInSeconds)
    {
        WebElement element =  waitForElementPresent(locator, error_message, 5);
        element.sendKeys(value);
        return element;
    }

    public boolean waitForElementNotPresent(String locator, String error_message, long timeoutInSeconds)
    {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    public WebElement waitForElementAndClear(String locator, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    public WebElement assertElementHasText(String locator, String expected_text, String error_message)
    {
        WebElement element = waitForElementPresent(locator, error_message, 5);
        String actual_text = element.getText();

        Assert.assertTrue(
                error_message,
                actual_text.contains(expected_text)
        );

        return element;
    }
    public void assertMoreThanOneElement(String locator, String errorMessage)
    {
        By by = this.getLocatorByString(locator);
        List<WebElement> elements = driver.findElements(by);

        Assert.assertTrue(
                errorMessage,
                elements.size() > 1
        );
    }

    public void swipeUp(int timeOfSwipe) {
        Dimension size = driver.manage().window().getSize();

        int startX = size.width / 2;
        int startY = (int) (size.height * 0.8);
        int endY   = (int) (size.height * 0.2);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);

        swipe.addAction(finger.createPointerMove(
                Duration.ZERO,
                PointerInput.Origin.viewport(),
                startX,
                startY
        ));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(
                Duration.ofMillis(timeOfSwipe),
                PointerInput.Origin.viewport(),
                startX,
                endY
        ));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(swipe));
    }

    public void swipeUpQuick()
    {
        swipeUp(200);
    }

    public void scrollWebPageUp()
    {
        if(Platform.getInstance().isMW())
        {
            JavascriptExecutor JSExecutor = (JavascriptExecutor) driver;
            JSExecutor.executeScript("window.scrollBy(0,250)");
        }
        else
        {
            System.out.println("Method rotateScreenPortrait() does nothing for platform " +
                    Platform.getInstance().getPlatformVar());        }
    }

    public void scrollWebPageTillElementNotVisible(String locator, String error_message, int max_swipes)
    {
        int already_swiped = 0;
        WebElement element = this.waitForElementPresent(locator, error_message);
        while(!this.isElementLocatedOnTheScreen(locator))
        {
            scrollWebPageUp();
            ++ already_swiped;
            if(already_swiped > max_swipes)
            {
                Assert.assertTrue(error_message, element.isDisplayed());
            }
        }
    }

    public void swipeUpToFindElement(String locator, String error_message, int max_swipes)
    {
        By by = this.getLocatorByString(locator);
        int already_swiped = 0;
        while(driver.findElements(by).size() == 0)
        {
            if(already_swiped > max_swipes)
            {
                waitForElementPresent(locator,
                        "Cannot find element by swiping up. \n" + error_message,
                        0);
                return;
            }

            swipeUpQuick();
            ++already_swiped;
        };
    }

    public void SwipeUpTillElementAppear(String locator, String error_message, int max_swipes)
    {
        int already_swiped = 0;
        while (!this.isElementLocatedOnTheScreen(locator))
        {
            if(already_swiped > max_swipes)
            {
                Assert.assertTrue(error_message, this.isElementLocatedOnTheScreen(locator));
            }
            swipeUpQuick();
            ++already_swiped;
        }
    }

    public boolean isElementLocatedOnTheScreen(String locator)
    {
        int element_location_by_y = this.waitForElementPresent(locator,
                "Cannot find element by locator",
                15).getLocation().getY();
        if(Platform.getInstance().isMW())
        {
            JavascriptExecutor JSExecutor = (JavascriptExecutor)driver;
            Object js_result = JSExecutor.executeScript("return window.pageYOffset");
            element_location_by_y -= Integer.parseInt(js_result.toString());
        };
        int screen_size_by_y = driver.manage().window().getSize().getHeight();
        return element_location_by_y < screen_size_by_y;
    }

    public void swipeElementToLeft(String locator, String error_message) {
        WebElement element = waitForElementPresent(
                locator,
                error_message,
                10);

        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);

        swipe.addAction(finger.createPointerMove(
                Duration.ZERO,
                PointerInput.Origin.viewport(),
                right_x - 1,     // чуть внутри элемента
                middle_y
        ));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(
                Duration.ofMillis(300),
                PointerInput.Origin.viewport(),
                left_x + 1,      // тоже внутри
                middle_y
        ));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(swipe));
    }

    public int getAmountOfElements(String locator)
    {
        By by = this.getLocatorByString(locator);
        List elements = driver.findElements(by);
        return elements.size();
    }

    public boolean isElementPresent(String locator)
    {
        return getAmountOfElements(locator)>0;
    }

    public void tryClickElementWithFewAttempts(String locator, String error_message, int amount_of_attempts)
    {
        int current_attempts = 0;
        boolean need_more_attempts = true;

        while(need_more_attempts)
        {
            try
            {
                this.waitForElementAndClick(locator, error_message, 1);
                need_more_attempts = false;
            } catch (Exception e)
            {
                if(current_attempts > amount_of_attempts)
                {
                    this.waitForElementAndClick(locator, error_message, 1);
                }
            }
            ++current_attempts;
        }
    }

    public void assertElementNotPresent(String locator, String error_message)
    {
        int amount_of_elements = getAmountOfElements(locator);
        if (amount_of_elements > 0)
        {
            String default_message = "An element '" + locator.toString() + "'supposed to be not present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }

    public String waitForElementAndGetAttribute(String locator, String attribute, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        return element.getAttribute(attribute);
    }

    public void assertElementPresent(String locator, String error_message)
    {
        By by = this.getLocatorByString(locator);
        List<WebElement> elements = driver.findElements(by);

        if (elements.size() == 0)
        {
            throw new AssertionError(error_message);
        }
    }

    private By getLocatorByString(String locator_with_type)
    {
        String[] exploded_locator = locator_with_type.split(Pattern.quote(":"),2);
        String by_type = exploded_locator[0];
        String locator = exploded_locator[1];

        if (by_type.equals("xpath"))
            return By.xpath(locator);
        else if (by_type.equals("id"))
            return By.id(locator);
        else if(by_type.equals("ios_predicate"))
            return MobileBy.iOSNsPredicateString(locator);
        else if(by_type.equals("css"))
            return By.cssSelector(locator);
        else throw new IllegalArgumentException("Cannot get type of locator. Locator " + locator_with_type);
    }
}
