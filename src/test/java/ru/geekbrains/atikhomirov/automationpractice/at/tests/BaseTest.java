package ru.geekbrains.atikhomirov.automationpractice.at.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import ru.geekbrains.atikhomirov.automationpractice.at.pom.page.*;

public abstract class BaseTest extends MatcherAssert {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    protected WebDriverWait wait;

    protected MainPage mainPage;
    protected SignInPage signInPage;
    protected RegistrationPage registrationPage;
    protected AccountPage accountPage;
    protected ShopPage shopPage;
    protected ShoppingCart shoppingCart;

    @Description("Setting up browser driver")
    @BeforeMethod
    @Parameters("browser")
    public void setUpDriver(@Optional("chrome") String browser) {
        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver.set(new ChromeDriver());
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver.set(new FirefoxDriver());
                break;
        }
        driver.get().manage().window().maximize();
        wait = new WebDriverWait(driver.get(), 6);
        mainPage = new MainPage(driver.get(), wait);
        signInPage = new SignInPage(driver.get(), wait);
        registrationPage = new RegistrationPage(driver.get(), wait);
        accountPage = new AccountPage(driver.get(), wait);
        shopPage = new ShopPage(driver.get(), wait);
        shoppingCart = new ShoppingCart(driver.get(), wait);
    }

    @Description("Closing browser driver")
    @AfterMethod
    public void tearDown() {
        driver.get().quit();
    }
}
