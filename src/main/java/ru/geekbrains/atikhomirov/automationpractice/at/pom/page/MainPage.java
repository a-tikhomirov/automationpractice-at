package ru.geekbrains.atikhomirov.automationpractice.at.pom.page;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.geekbrains.atikhomirov.automationpractice.at.pom.common.BaseActions;
import ru.geekbrains.atikhomirov.automationpractice.at.pom.common.ContentPage;
import ru.geekbrains.atikhomirov.automationpractice.at.pom.common.Header;

public class MainPage extends ContentPage {
    private static final String HOMEPAGE_URL = "http://automationpractice.com/";

    @Override
    public boolean isPageHeaderPresent() {
        return false;
    }

    @Override
    public String getPageHeaderText() {
        return "";
    }

    public MainPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @Step("Open http://automationpractice.com/")
    public MainPage home() {
        driver.get(HOMEPAGE_URL);
        return this;
    }
}
