package tests;

import config.WebDriverSettings;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.MainPage;

import static org.assertj.core.api.Assertions.assertThat;

public class MainPageTest extends WebDriverSettings {
    MainPage mainPage;

    public MainPageTest() {
        super();
    }

    @BeforeMethod
    public void setUp() throws Exception {
        initialisation();
        mainPage = new MainPage();
    }

    @Test
    public void testName() {
        assertThat(mainPage.atMainPage()).isTrue();
        mainPage.typeTextInFastSearchInput("xiaomi Mi");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}
