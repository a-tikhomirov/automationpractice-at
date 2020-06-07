package ru.geekbrains.atikhomirov.automationpractice.at.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.*;
import ru.geekbrains.atikhomirov.automationpractice.at.pom.page.RegistrationPage.Alert;
import ru.geekbrains.atikhomirov.automationpractice.at.utility.Account;
import ru.geekbrains.atikhomirov.automationpractice.at.utility.AccountDataGenerator;

import static org.hamcrest.Matchers.*;
import static ru.geekbrains.atikhomirov.automationpractice.at.utility.Account.Field.*;

@Feature(value = "http://automationpractice.com autotests")
@Story(value = "Registration tests")
public class RegistrationTests extends BaseTest {
    private static AccountDataGenerator accDataGenerator;

    @Description("Preparing account data generator")
    @BeforeClass
    private void prepareAccountGenerator() {
        accDataGenerator = new AccountDataGenerator(20);
    }

    @Description("Main page - sign in page")
    @BeforeMethod
    private void goToSignInPage() {
        mainPage.home();
        mainPage.getHeader().signIn();
    }

    @DataProvider(name = "CreateNewAccountDataSupplier")
    private static Object[][] createNewAccountTestDataSupplier() {
        return accDataGenerator.genAccounts(5).stream()
                .map(account -> new Object[]{account})
                .toArray(Object[][]::new);
    }

    @Test(
            description = "Checks: Create an account - Register with valid data",
            dataProvider = "CreateNewAccountDataSupplier"
    )
    public void createNewAccountTest(Account account) {
        signInPage.enterEmailAndGoToRegistration(account.getEmail());
        registrationPage.fillFormAndSubmit(account);
        assertThat("Page header is presented", accountPage.isPageHeaderPresent(), equalTo(true));
        assertThat("Page header text is correct", accountPage.getPageHeaderText(), equalToIgnoringCase("my account"));
    }

    @DataProvider(name = "registrationFailDataSupplier")
    private static Object[][] registrationFailTestDataSupplier() {
        return new Object[][]{
                {accDataGenerator.genAccount(),
                        new Account.Field[]{FIRST_NAME, LAST_NAME, EMAIL, PASSWD},
                        new String[]{Alert.FIRST_NAME_REQ.getText(), Alert.LAST_NAME_REQ.getText(), Alert.EMAIL_REQ.getText(), Alert.PASSWD_REQ.getText()}
                },
                {accDataGenerator.genAccount(),
                        new Account.Field[]{PASSWD, ADDR_FIRST_NAME, ADDR_LAST_NAME, ADDRESS},
                        new String[]{Alert.PASSWD_REQ.getText(), Alert.FIRST_NAME_REQ.getText(), Alert.LAST_NAME_REQ.getText(), Alert.ADDR_REQ.getText()}
                },
                {accDataGenerator.genAccount(),
                        new Account.Field[]{ADDRESS, CITY, STATE, ZIP},
                        new String[]{Alert.ADDR_REQ.getText(), Alert.CITY_REQ.getText(), Alert.STATE_REQ.getText(), Alert.ZIP_REQ.getText()}
                },
                {accDataGenerator.genAccount(),
                        new Account.Field[]{COUNTRY, PHONE},
                        new String[]{Alert.COUNTRY_REQ.getText(), Alert.PHONE_REQ.getText()}
                },
                {accDataGenerator.genAccount(),
                        new Account.Field[]{PHONE, ADDR_ALIAS},
                        new String[]{Alert.PHONE_REQ.getText(), Alert.ADDR_ALIAS_REQ.getText()}
                }
        };
    }

    @Test(
            description = "Checks: Create an account - Register with invalid data (missing some fields)",
            dataProvider = "registrationFailDataSupplier"
    )
    public void registrationFailTest(Account account, Account.Field[] fieldsToBreak, String[] expectedAlerts) {
        signInPage.enterEmailAndGoToRegistration(account.getEmail());
        for (Account.Field fieldToBreak : fieldsToBreak) {
            account.setField(fieldToBreak, "");
        }
        registrationPage.fillFormAndSubmit(account);
        assertThat("Registration fail alert is presented", registrationPage.isAlertPresent(), equalTo(true));
        String alert = registrationPage.getAlertText();
        for (String expectedAlert : expectedAlerts) {
            assertThat("Alert text contains", alert, containsString(expectedAlert));
        }
    }
}
