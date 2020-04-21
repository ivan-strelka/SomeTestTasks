package pages;

import config.WebDriverSettings;
import org.openqa.selenium.support.PageFactory;

public class BasePage extends WebDriverSettings {


    public BasePage() {
        PageFactory.initElements(driver, this);
    }

    public static void switchToDefaultFrameContent() {
        driver.switchTo().defaultContent();
    }

}
