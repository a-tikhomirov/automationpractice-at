package ru.geekbrains.atikhomirov.automationpractice.at.pom.common;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public abstract class BaseActions {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BaseActions(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public WebElement waitElement(int timeoutSec, int pollingMillis, Function<WebDriver, WebElement> f) {
        Wait<WebDriver> fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeoutSec))
                .pollingEvery(Duration.ofMillis(pollingMillis))
                .ignoring(NoSuchElementException.class);
        return fluentWait.until(f);
    }

    public void type(String text, By by) {
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        WebElement element = driver.findElement(by);
        element.clear();
        element.sendKeys(text);
    }

    public void select(String value, By by) {
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        wait.until((ExpectedCondition<Boolean>) driver -> new Select(driver.findElement(by)).getOptions().size() > 1);
        Select select = new Select(driver.findElement(by));
        select.selectByValue(value);
    }

    public void click(By by) {
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        click(driver.findElement(by));
    }

    public void click(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public boolean isElementPresent(By by) {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public WebElement findElement(By listSelector, By subElement, String expectedValue) {
        List<Object> arrayList = new ArrayList<>();
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(listSelector, 0));
        for (WebElement webElement : driver.findElements(listSelector)) {
            String actualText = webElement.findElement(subElement).getText();
            arrayList.add(actualText);
            if (actualText.toLowerCase().trim().equals(expectedValue.toLowerCase().trim())) {
                return webElement;
            }
        }
        throw new RuntimeException(String.format("Collection:%s\nElement: %s not found", arrayList, expectedValue));
    }

    public void hoverElement(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }
}
