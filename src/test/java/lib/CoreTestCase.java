package lib;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import junit.framework.TestCase;
import lib.ui.WelcomePageObject;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.FileOutputStream;
import java.time.Duration;
import java.util.Properties;

public class CoreTestCase  {

    protected RemoteWebDriver driver;

    @Before
    @Step("Run driver and session")
    public void setUp() throws Exception {

        driver = Platform.getInstance().getDriver();
        this.createAllurePropertyFile();
        this.rotateScreenPortrait();
        this.skipWelcomePage();
        this.openWikiWebPageForMobileWeb();
    }

    @After
    @Step("Remove driver and session")
    public void tearDown() {
        driver.quit();
    }

    @Step("Rotate screen to portrait mode")
    protected void rotateScreenPortrait()
    {
        if(driver instanceof AppiumDriver)
        {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.PORTRAIT);
        }
        else
        {
         System.out.println("Method rotateScreenPortrait() does nothing for platform " +
                 Platform.getInstance().getPlatformVar());
        }

    }

    @Step("Rotate screen to landscape mode")
    protected void rotateScreenLandscape()
    {
        if(driver instanceof AppiumDriver)
        {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.LANDSCAPE);
        }
        else
        {
            System.out.println("Method rotateScreenPortrait() does nothing for platform " +
                    Platform.getInstance().getPlatformVar());
        }
    }

    @Step("Send mobile app to background")
    protected void backgroundApp(int seconds)
    {
        if(driver instanceof AppiumDriver)
        {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.runAppInBackground(Duration.ofSeconds(seconds));
        }
        else
        {
            System.out.println("Method rotateScreenPortrait() does nothing for platform " +
                    Platform.getInstance().getPlatformVar());
        }
    }

    @Step("Skip preview for android app")
    protected void skipPreviewAndroid() {
        if(driver instanceof AppiumDriver)
        {   AppiumDriver driver = (AppiumDriver) this.driver;
            if(Platform.getInstance().isAndroid())
            {
                WelcomePageObject WelcomePageObject = new WelcomePageObject(driver);
                WelcomePageObject.clickSkipAndroid();
            }
        }
        else
        {
            System.out.println("Method rotateScreenPortrait() does nothing for platform " +
                    Platform.getInstance().getPlatformVar());
        }
    }

    @Step("Skip welcome page for ios app")
    private void skipWelcomePageForIOSApp()
    {
        if(driver instanceof AppiumDriver)
        {   AppiumDriver driver = (AppiumDriver) this.driver;
            if (Platform.getInstance().isIos())
            {
                WelcomePageObject WelcomePageObject = new WelcomePageObject(driver);
                WelcomePageObject.clickSkipIOS();
            }
        }
        else
        {
            System.out.println("Method rotateScreenPortrait() does nothing for platform " +
                    Platform.getInstance().getPlatformVar());
        }
    }

    @Step("Open Wiki page for mobile web")
    protected void openWikiWebPageForMobileWeb()
    {
        if(Platform.getInstance().isMW()) {
            driver.get("https://en.m.wikipedia.org ");
        } else {
           System.out.println("Method openWikiWebPageForMobileWeb() do nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    @Step("Skip welcome page")
    protected void skipWelcomePage() {
       {
            if (Platform.getInstance().isIos()) {
                this.skipWelcomePageForIOSApp();
            } else if (Platform.getInstance().isAndroid()) {
                this.skipPreviewAndroid();
            }
        }
    }

    private void createAllurePropertyFile()
    {
        String path = System.getProperty("allure.results.directory");
        try
        {
            Properties props = new Properties();
            FileOutputStream fos = new FileOutputStream(path + "/environment.properties");
            props.setProperty("Environment", Platform.getInstance().getPlatformVar());
            props.store(fos,"See https://docs.qameta.io/allure/#_environment");
            fos.close();
        } catch (Exception e)
        {
            System.err.println("IO problen when writing allure properties file");
            e.printStackTrace();
        }
    }
}
