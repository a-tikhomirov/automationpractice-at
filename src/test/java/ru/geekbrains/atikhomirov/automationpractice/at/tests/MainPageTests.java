package ru.geekbrains.atikhomirov.automationpractice.at.tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import ru.geekbrains.atikhomirov.automationpractice.at.pom.page.MainPage;
import ru.geekbrains.atikhomirov.automationpractice.at.pom.page.SignInPage;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToIgnoringCase;

@Feature(value = "http://automationpractice.com autotests")
@Story(value = "MainPage tests")
public class MainPageTests extends BaseTest {

    @Test(description = "Checks that auth page is opened when clicked on Sign-in button")
    public void goToSignInPageTest() {
        SignInPage signInPage = new MainPage(getDriver(), getWait())
                .home()
                .getHeader().signIn();
        assertThat("Page header is presented", signInPage.isPageHeaderPresent(), equalTo(true));
        assertThat("Page header text is correct", signInPage.getPageHeaderText(), equalToIgnoringCase("authentication"));
    }
}
