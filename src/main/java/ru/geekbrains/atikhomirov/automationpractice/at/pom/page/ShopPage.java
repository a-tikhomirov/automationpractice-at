package ru.geekbrains.atikhomirov.automationpractice.at.pom.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.geekbrains.atikhomirov.automationpractice.at.pom.common.ContentPage;

public class ShopPage extends ContentPage {
    private static final By PRODUCTS_LIST = By.cssSelector("ul[class=\"product_list grid row\"] > li ");
    private static final By PRODUCT_NAME = By.cssSelector("a.product-name");
    private static final By PRODUCT_ADD_TO_CART_BTN = By.cssSelector("a[title=\"Add to cart\"]");

    private static final By CONTINUE_SHOPPING_BTN = By.cssSelector("[title=\"Continue shopping\"]");

    public ShopPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @Step("Add {productName} to cart")
    public void addProductToCart(String productName) {
        WebElement product = findElement(PRODUCTS_LIST, PRODUCT_NAME, productName);
        hoverElement(product);
        click(product.findElement(PRODUCT_ADD_TO_CART_BTN));
    }

    @Step("Click Continue shopping button")
    public void continueShopping() {
        click(CONTINUE_SHOPPING_BTN);
    }
}
