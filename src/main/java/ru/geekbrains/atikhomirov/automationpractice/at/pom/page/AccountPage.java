package ru.geekbrains.atikhomirov.automationpractice.at.pom.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.geekbrains.atikhomirov.automationpractice.at.pom.common.BaseActions;
import ru.geekbrains.atikhomirov.automationpractice.at.pom.common.Header;

public class AccountPage extends BaseActions {
    private static final By PAGE_HEADER = By.cssSelector(".page-heading");

    private Header header;

    public AccountPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        header = new Header(driver, wait);
    }

    public boolean isPageHeaderPresent() {
        return isElementPresent(PAGE_HEADER);
    }

    public String getPageHeaderText() {
        return driver.findElement(PAGE_HEADER).getText();
    }

    public void logout() { header.signOut(); }

    public void selectShoppingCategory(Header.ShoppingCategory category) {
        header.openCategory(category);
    }
}
