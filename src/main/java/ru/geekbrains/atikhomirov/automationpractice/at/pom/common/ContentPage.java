package ru.geekbrains.atikhomirov.automationpractice.at.pom.common;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class ContentPage extends BaseActions {
    private static final By PAGE_HEADER = By.cssSelector(".page-heading");

    private Header header;

    public Header getHeader() {
        return header;
    }

    public ContentPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        header = new Header(driver, wait);
    }

    @Step("Checks that page header is presented")
    public boolean isPageHeaderPresent() {
        return isElementPresent(PAGE_HEADER);
    }

    @Step("Get page header text (for assertion)")
    public String getPageHeaderText() {
        return driver.findElement(PAGE_HEADER).getText();
    }
}
