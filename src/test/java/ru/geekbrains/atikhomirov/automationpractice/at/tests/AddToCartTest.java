package ru.geekbrains.atikhomirov.automationpractice.at.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.geekbrains.atikhomirov.automationpractice.at.pom.page.MainPage;
import ru.geekbrains.atikhomirov.automationpractice.at.pom.page.ShoppingCart;
import ru.geekbrains.atikhomirov.automationpractice.at.utility.Account;
import ru.geekbrains.atikhomirov.automationpractice.at.utility.AccountDataGenerator;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static ru.geekbrains.atikhomirov.automationpractice.at.pom.common.Header.*;

@Feature(value = "http://automationpractice.com autotests")
@Story(value = "Sign in and Add to cart tests")
public class AddToCartTest extends BaseTest {
    private static List<Account> accounts;

    @Description("Creating accounts data")
    @BeforeClass
    private void prepareAccounts() {
        AccountDataGenerator accountDataGenerator = new AccountDataGenerator(3);
        accounts = accountDataGenerator.genAccounts(3);
    }

    @DataProvider(name = "createAccountDataSupplier", parallel = true)
    private static Object[][] createAccountDataSupplier() {
        return accounts.stream()
                .map(account -> new Object[]{ account })
                .toArray(Object[][]::new);
    }

    @Test(
            description = "Creating accounts for next test",
            dataProvider = "createAccountDataSupplier"
    )
    public void createAccount(Account acc) {
        new MainPage(getDriver(), getWait())
                .home().getHeader().signIn()
                .enterEmailAndGoToRegistration(acc.getEmail())
                .fillFormAndSubmit(acc)
                .waitForAccountPageLoaded(5);
    }

    @DataProvider(name = "addItemsAndCheckCartDataSupplier", parallel = true)
    private static Object[][] addItemsAndCheckCartDataSupplier() {
        return new Object[][]{
                {accounts.get(0), "Faded Short Sleeve T-shirts"},
                {accounts.get(1), "Blouse"},
                {accounts.get(2), "Printed Dress"}
        };
    }

    @Test(
            description = "Checks: main page - Sign in - Add to cart - view cart",
            dataProvider = "addItemsAndCheckCartDataSupplier",
            dependsOnMethods = "createAccount"
    )
    public void addItemsAndCheckCart(Account account, String product) {
        ShoppingCart shoppingCart = new MainPage(getDriver(), getWait())
                .home()
                .getHeader().signIn()
                .signIn(account.getEmail(), account.getPassword())
                .getHeader().openCategory(ShoppingCategory.WOMEN)
                .addProductToCart(product)
                .continueShopping()
                .getHeader().viewCart();
        assertThat(String.format("Item %s is presented in the cart", product), shoppingCart.isItemInCart(product), equalTo(true));
        assertThat(String.format("Item %s qty is %d", product, 1), shoppingCart.getItemQty(product), equalTo(1));
    }
}
