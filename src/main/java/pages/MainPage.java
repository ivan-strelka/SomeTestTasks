package pages;

import config.WebDriverSettings;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends WebDriverSettings {

    @FindBy(xpath = "//input[@class='fast-search__input']")
    private WebElement fast_search_input;

    public MainPage() {
        PageFactory.initElements(driver, this);
    }

    public void typeTextInFastSearchInput(String text) {
        fast_search_input.clear();
        fast_search_input.click();
        fast_search_input.sendKeys(text);
    }

    public boolean atMainPage() {
        if (driver.getTitle().equals("Onliner")) {
            return true;
        } else {
            return false;
        }
    }

}
