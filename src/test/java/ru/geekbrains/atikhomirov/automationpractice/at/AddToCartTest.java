package ru.geekbrains.atikhomirov.automationpractice.at;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.geekbrains.atikhomirov.automationpractice.at.utility.Account;
import ru.geekbrains.atikhomirov.automationpractice.at.utility.AccountDataGenerator;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static ru.geekbrains.atikhomirov.automationpractice.at.pom.common.Header.*;

@DisplayName("http://automationpractice.com - sign in and add to cart test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AddToCartTest extends BaseTest {
    private static List<Account> accounts;

    private static Stream<Account> createAccountsDataSupplier() {
        AccountDataGenerator accountDataGenerator = new AccountDataGenerator(3);
        accounts = accountDataGenerator.genAccounts(3);
        return accounts.stream();
    }

    @Order(1)
    @DisplayName("Order 1 - Creating accounts for next test")
    @ParameterizedTest(name = "{index} ==> Creating {0}")
    @MethodSource("createAccountsDataSupplier")
    public void createAccounts(Account acc) {
        mainPage.home();
        mainPage.goToSignIn();
        signInPage.enterEmailAndGoToRegistration(acc.getEmail());
        registrationPage.fillFormAndSubmit(acc);
    }

    private static Stream<Arguments> addItemsAndCheckCartDataSupplier() {
        String[] products = {"Faded Short Sleeve T-shirts", "Blouse", "Printed Dress"};
        return Stream.of(
                Arguments.of(accounts.get(0), products),
                Arguments.of(accounts.get(1), products),
                Arguments.of(accounts.get(2), products)
        );
    }

    @Order(2)
    @DisplayName("Order 2 - Checks: main page - Sign in - Add to cart - view cart")
    @ParameterizedTest(name = "{index} ==> {0} Adding to cart: {1}")
    @MethodSource("addItemsAndCheckCartDataSupplier")
    public void addItemsAndCheckCart(Account account, String[] products) {
        mainPage.home();
        mainPage.goToSignIn();
        signInPage.signIn(account.getEmail(), account.getPassword());
        accountPage.selectShoppingCategory(ShoppingCategory.WOMEN);
        for (String product:products) {
            shopPage.addProductToCart(product);
            shopPage.continueShopping();
        }
        shopPage.viewCart();
        for (String product:products) {
            assertTrue(shoppingCart.isItemInCart(product));
            assertEquals(1, shoppingCart.getItemQty(product));
        }
    }
}
