package ru.geekbrains.atikhomirov.automationpractice.at;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.geekbrains.atikhomirov.automationpractice.at.pom.BaseActions;
import ru.geekbrains.atikhomirov.automationpractice.at.utility.Account;
import ru.geekbrains.atikhomirov.automationpractice.at.utility.AccountDataGenerator;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static ru.geekbrains.atikhomirov.automationpractice.at.pom.RegistrationPage.*;

@DisplayName("http://automationpractice.com - registration test")
//@Execution(ExecutionMode.CONCURRENT)
public class RegistrationTests extends BaseTest {

    @BeforeEach
    private void goToSignInPage() {
        mainPage.home();
        mainPage.goToSignIn();
        assertTrue(signInPage.isPageHeaderPresent());
        assertEquals("authentication", signInPage.getPageHeaderText().toLowerCase());
    }

    private static Stream<Account> createNewAccountTestDataSupplier() {
        AccountDataGenerator accountDataGenerator = new AccountDataGenerator();
        return accountDataGenerator.genAccounts(5).stream();
    }

    @DisplayName("Checks: main page - Sign in - Create an account - Register with valid data")
    @ParameterizedTest(name = "{index} ==> {0}")
    @MethodSource("createNewAccountTestDataSupplier")
    public void createNewAccountTest(Account account) {
        signInPage.enterEmailAndGoToRegistration(account.getEmail());
        assertTrue(registrationPage.isPageHeaderPresent());
        assertEquals("create an account", registrationPage.getPageHeaderText().toLowerCase());
        registrationPage.fillFormAndSubmit(account);
        assertTrue(accountPage.isPageHeaderPresent());
        assertEquals("my account", accountPage.getPageHeaderText().toLowerCase());
    }

    private static Stream<Arguments> registrationFailTestDataSupplier() {
        AccountDataGenerator accGen = new AccountDataGenerator();
        return Stream.of(
                Arguments.of(accGen.genAccount(), Account.Field.FIRST_NAME, Alert.FIRST_NAME_REQ.getText()),
                Arguments.of(accGen.genAccount(), Account.Field.LAST_NAME, Alert.LAST_NAME_REQ.getText()),
                Arguments.of(accGen.genAccount(), Account.Field.EMAIL, Alert.EMAIL_REQ.getText()),
                Arguments.of(accGen.genAccount(), Account.Field.PASSWD, Alert.PASSWD_REQ.getText()),
                Arguments.of(accGen.genAccount(), Account.Field.ADDR_FIRST_NAME, Alert.FIRST_NAME_REQ.getText()),
                Arguments.of(accGen.genAccount(), Account.Field.ADDR_LAST_NAME, Alert.LAST_NAME_REQ.getText()),
                Arguments.of(accGen.genAccount(), Account.Field.ADDRESS, Alert.ADDR_REQ.getText()),
                Arguments.of(accGen.genAccount(), Account.Field.CITY, Alert.CITY_REQ.getText()),
                Arguments.of(accGen.genAccount(), Account.Field.STATE, Alert.STATE_REQ.getText()),
                Arguments.of(accGen.genAccount(), Account.Field.ZIP, Alert.ZIP_REQ.getText()),
                Arguments.of(accGen.genAccount(), Account.Field.COUNTRY, Alert.COUNTRY_REQ.getText()),
                Arguments.of(accGen.genAccount(), Account.Field.PHONE, Alert.PHONE_REQ.getText()),
                Arguments.of(accGen.genAccount(), Account.Field.ADDR_ALIAS, Alert.ADDR_ALIAS_REQ.getText())
        );
    }

    @DisplayName("Checks: main page - Sign in - Create an account - Register with invalid data (missing one field)")
    @ParameterizedTest(name = "{index} ==> Empty field: {1}; Expected alert: {2}")
    @MethodSource("registrationFailTestDataSupplier")
    public void registrationFailTest(Account account, Account.Field fieldToBreak, String expectedAlert) {
        signInPage.enterEmailAndGoToRegistration(account.getEmail());
        account.setField(fieldToBreak, "");
        registrationPage.fillFormAndSubmit(account);
        assertTrue(registrationPage.isAlertPresent());
        assertTrue(registrationPage.getAlertText().contains(expectedAlert));
    }
}
