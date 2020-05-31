package ru.geekbrains.atikhomirov.automationpractice.at.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.geekbrains.atikhomirov.automationpractice.at.utility.Account;

public class RegistrationPage extends BaseActions {
    private static final By REGISTER_PAGE_HEADER = By.cssSelector(".page-heading");

    private static final By FIRST_NAME_INPUT = By.cssSelector("#customer_firstname");
    private static final By LAST_NAME_INPUT = By.cssSelector("#customer_lastname");
    private static final By EMAIL_INPUT = By.cssSelector("#email");
    private static final By PASSWD_INPUT = By.cssSelector("#passwd");
    private static final By ADDR_FIRST_NAME_INPUT = By.cssSelector("[id=firstname]");
    private static final By ADDR_LAST_NAME_INPUT = By.cssSelector("[id=lastname]");
    private static final By ADDR_INPUT = By.cssSelector("#address1");
    private static final By CITY_INPUT = By.cssSelector("#city");
    private static final By STATE_SELECT = By.cssSelector("#id_state");
    private static final By ZIP_INPUT = By.cssSelector("#postcode");
    private static final By COUNTRY_SELECT = By.cssSelector("#id_country");
    private static final By PHONE_INPUT = By.cssSelector("#phone_mobile");
    private static final By ADDR_ALIAS_INPUT = By.cssSelector("#alias");

    private static final By REGISTER_BTN = By.cssSelector("#submitAccount");

    private static final By ALERT_DIV = By.cssSelector("#center_column .alert");

    public RegistrationPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public boolean isPageHeaderPresent() {
        return isElementPresent(REGISTER_PAGE_HEADER);
    }

    public String getPageHeaderText() {
        return driver.findElement(REGISTER_PAGE_HEADER).getText();
    }

    public void fillFormAndSubmit(Account acc) {
        type(acc.getFirstName(), FIRST_NAME_INPUT);
        type(acc.getLastName(), LAST_NAME_INPUT);
        type(acc.getEmail(), EMAIL_INPUT);
        type(acc.getPassword(), PASSWD_INPUT);
        type(acc.getAddrFirstName(), ADDR_FIRST_NAME_INPUT);
        type(acc.getAddrLastName(), ADDR_LAST_NAME_INPUT);
        type(acc.getAddress(), ADDR_INPUT);
        type(acc.getCity(), CITY_INPUT);
        select(acc.getStateId(), STATE_SELECT);
        type(acc.getZip(), ZIP_INPUT);
        select(acc.getCountryId(), COUNTRY_SELECT);
        type(acc.getMobilePhone(), PHONE_INPUT);
        type(acc.getAddressAlias(), ADDR_ALIAS_INPUT);
        click(REGISTER_BTN);
    }

    public boolean isAlertPresent() {
        return isElementPresent(ALERT_DIV);
    }

    public String getAlertText() {
        return driver.findElement(ALERT_DIV).getText();
    }

    public enum Alert {
        FIRST_NAME_REQ("firstname is required"),
        LAST_NAME_REQ("lastname is required"),
        EMAIL_REQ("email is required"),
        PASSWD_REQ("passwd is required"),
        ADDR_REQ("address1 is required"),
        CITY_REQ("city is required"),
        STATE_REQ("This country requires you to choose a State"),
        ZIP_REQ("The Zip/Postal code you've entered is invalid. It must follow this format: 00000"),
        COUNTRY_REQ("Country is invalid"),
        PHONE_REQ("You must register at least one phone number"),
        ADDR_ALIAS_REQ("alias is required");

        private String text;

        Alert(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }
    }
}
