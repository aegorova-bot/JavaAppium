package lib;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Platform {

    private static final String PLATFORM_IOS = "ios";
    private static final String PLATFORM_ANDROID = "android";
    private static final String PLATFORM_MOBILE_WEB = "mobile_web";
    private static final String APPIUM_URL = "http://127.0.0.1:4723";

    private static Platform instance;
    private Platform() {}
    public static Platform getInstance()
    {
        if(instance == null)
        {
            instance = new Platform();
        }
        return instance;
    }

    public RemoteWebDriver getDriver() throws Exception
    {
        URL URL = new URL(APPIUM_URL);
        if(this.isAndroid())
        {
            return new AndroidDriver(URL, this.getAndroidDesiredCapabilities());
        }
        else if(this.isIos())
        {
            return new IOSDriver(URL, this.getIosDesiredCapabilities());
        }
         else if(this.isMW())
    {
        System.setProperty(
                "webdriver.chrome.driver",
                "/Users/aleksandraegorova/JavaAppium/JavaAppium/chromedriver"
        );
        return new ChromeDriver(this.getMWChromeOptions());
    }
        else
        {
            throw new Exception("Cannot detect type of Driver. Platform value " + this.getPlatformVar());
        }
    }

    public boolean isAndroid() {return isPlatform(PLATFORM_ANDROID);}

    public boolean isIos() {return isPlatform(PLATFORM_IOS);}

    public boolean isMW() {return isPlatform(PLATFORM_MOBILE_WEB);};

    private DesiredCapabilities getAndroidDesiredCapabilities()
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "9");
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/Users/aleksandraegorova/Desktop/JavaAppiumAutomation/JavaAppiumAutomation/apks/org.wikipedia_50467_apps.evozi.com.apk");
        return capabilities;
    }
    private DesiredCapabilities getIosDesiredCapabilities()
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("deviceName", "iPhone 15");
        capabilities.setCapability("platformVersion", "17.2");
        capabilities.setCapability("automationName", "XCUITest");
        capabilities.setCapability("app", "/Users/aleksandraegorova/Desktop/JavaAppiumAutomation/JavaAppiumAutomation/apks/Wikipedia.app");
        return capabilities;
    }

    private ChromeOptions getMWChromeOptions() {

        Map<String, Object> deviceMetrics = new HashMap<>();
        deviceMetrics.put("width", 360);
        deviceMetrics.put("height", 640);
        deviceMetrics.put("pixelRatio", 3.0);

        Map<String, Object> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceMetrics", deviceMetrics);
        mobileEmulation.put("userAgent",
                "Mozilla/5.0 (Linux; Android 4.2.1; en-us; Nexus 5 Build/JOP40D) " +
                        "AppleWebKit/535.19 (KHTML, like Gecko) " +
                        "Chrome/145.0.7632.46 Mobile Safari/535.19");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
        chromeOptions.addArguments("window-size=360,640");

        return chromeOptions;
    }

    private boolean isPlatform(String my_platform)
    {
        String platform = this.getPlatformVar();
        return my_platform.equals(platform);
    }

    public String getPlatformVar()
    {
        return System.getProperty("platform");
    }
}
