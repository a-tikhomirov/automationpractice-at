package ru.geekbrains.atikhomirov.automationpractice.at.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountPage extends BaseActions {
    private static final By REGISTER_PAGE_HEADER = By.cssSelector(".page-heading");

    public AccountPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public boolean isPageHeaderPresent() {
        return isElementPresent(REGISTER_PAGE_HEADER);
    }

    public String getPageHeaderText() {
        return driver.findElement(REGISTER_PAGE_HEADER).getText();
    }
}
