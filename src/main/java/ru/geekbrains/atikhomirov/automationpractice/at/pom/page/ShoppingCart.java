package ru.geekbrains.atikhomirov.automationpractice.at.pom.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.geekbrains.atikhomirov.automationpractice.at.pom.common.BaseActions;

public class ShoppingCart extends BaseActions {
    private static final By PAGE_HEADER = By.cssSelector(".page-heading");
    private static final By CART_ITEMS_LIST = By.cssSelector("#order-detail-content > table > tbody > tr ");
    private static final By CART_ITEM_NAME = By.cssSelector(".product-name");
    private static final By CART_ITEM_QTY = By.cssSelector("[class=\"cart_quantity_input form-control grey\"]");

    public ShoppingCart(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public boolean isPageHeaderPresent() {
        return isElementPresent(PAGE_HEADER);
    }

    public String getPageHeaderText() {
        return driver.findElement(PAGE_HEADER).getText();
    }

    public boolean isItemInCart(String itemName) {
        return findElement(CART_ITEMS_LIST, CART_ITEM_NAME, itemName).isDisplayed();
    }

    public int getItemQty(String itemName) {
        return Integer.parseInt(
                findElement(CART_ITEMS_LIST, CART_ITEM_NAME, itemName)
                        .findElement(CART_ITEM_QTY).getAttribute("value")
        );
    }
}
