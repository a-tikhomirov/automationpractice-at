package ru.geekbrains.atikhomirov.automationpractice.at.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

public abstract class BaseTest extends MatcherAssert {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static final ThreadLocal<WebDriverWait> wait = new ThreadLocal<>();

    public WebDriver getDriver() {
        return driver.get();
    }

    public static WebDriverWait getWait() {
        return wait.get();
    }

    private String browserType;

    @BeforeClass
    @Parameters("browser")
    public void getBrowserDriver(@Optional("chrome") String browser) {
        browserType = browser;
        switch (browserType) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                break;
        }
    }

    @Description("Setting up browser driver")
    @BeforeMethod
    //@Parameters("browser")
    public void setUpDriver() {
        switch (browserType) {
            case "chrome":
                driver.set(new ChromeDriver());
                break;
            case "firefox":
                driver.set(new FirefoxDriver());
                break;
        }
        driver.get().manage().window().maximize();
        wait.set(new WebDriverWait(driver.get(), 8));
    }

    @Description("Closing browser driver")
    @AfterMethod
    public void tearDown() {
        driver.get().quit();
    }
}
