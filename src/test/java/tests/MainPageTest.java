package tests;

import config.WebDriverSettings;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.MainPage;

import static org.assertj.core.api.Assertions.assertThat;

public class MainPageTest extends WebDriverSettings {
    MainPage mainPage;
    BasePage basePage;

    public MainPageTest() {
        super();
    }

    @BeforeMethod
    public void setUp() throws Exception {
        initialisation();
        mainPage = new MainPage();
        basePage = new BasePage();
    }

    @Test
    public void testName() throws Exception {
        assertThat(mainPage.atMainPage()).isTrue();
        mainPage.typeTextInFastSearchInput("xiaomi Mi");
        mainPage.clickToItemOnIframeFastInput(2);
        assertThat(mainPage.checkMiddlePriceIsMoreThan(232)).isTrue();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
