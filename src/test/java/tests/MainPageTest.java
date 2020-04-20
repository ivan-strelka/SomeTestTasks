package tests;

import config.WebDriverSettings;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.MainPage;

import java.text.ParseException;

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
    public void testName() throws ParseException {
        assertThat(mainPage.atMainPage()).isTrue();
        mainPage.typeTextInFastSearchInput("xiaomi Mi");
        basePage.switchToIFrame(By.xpath("//iframe[@class='modal-iframe']"));
        basePage.clickToItemOnIframe(2);
        basePage.switchToDefaultFrameContent();
        basePage.checkSumAllPrice();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}
