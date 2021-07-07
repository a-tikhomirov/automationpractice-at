package ru.geekbrains.atikhomirov.automationpractice.at.pom.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.geekbrains.atikhomirov.automationpractice.at.pom.common.ContentPage;

public class AccountPage extends ContentPage {
    private static final By WELCOME_TO_ACCOUNT = By.cssSelector(".info-account");

    public AccountPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public AccountPage waitForAccountPageLoaded(int seconds) {
        waitElement(seconds, 500, d -> d.findElement(WELCOME_TO_ACCOUNT));
        return this;
    }
}
