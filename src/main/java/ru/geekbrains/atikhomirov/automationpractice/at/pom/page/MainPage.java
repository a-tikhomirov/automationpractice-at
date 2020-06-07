package ru.geekbrains.atikhomirov.automationpractice.at.pom.page;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.geekbrains.atikhomirov.automationpractice.at.pom.common.BaseActions;
import ru.geekbrains.atikhomirov.automationpractice.at.pom.common.Header;

public class MainPage extends BaseActions {
    private static final String HOMEPAGE_URL = "http://automationpractice.com/";

    private Header header;

    public Header getHeader() {
        return header;
    }

    public MainPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        header = new Header(driver, wait);
    }

    @Step("Open http://automationpractice.com/")
    public void home() {
        driver.get(HOMEPAGE_URL);
    }
}
