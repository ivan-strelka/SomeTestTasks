package pages;

import config.WebDriverSettings;
import constants.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class MainPage extends WebDriverSettings {

    @FindBy(xpath = "//input[@class='fast-search__input']")
    protected WebElement fast_search_input;
    @FindBy(xpath = "//a[@class='button button_orange product__button']")
    protected List<WebElement> allElementsOnIframe;
    @FindBy(xpath = "//tr//td//p//a//span")
    protected List<WebElement> allElementsOnResultPage;

    public MainPage() {
        PageFactory.initElements(driver, this);
    }

    public void clickToItemOnIframeFastInput(int numberOfItem) {
        switchToIFrameForFastSearchInput();
        List<WebElement> elements = allElementsOnIframe;
        elements.get(numberOfItem - 1).click();
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

    public double getMiddleSumAllItemsOnPage() throws Exception {
//        Как можно сделать с коллекцией
//        double sum = elements.stream().map(WebElement::getText).map(item ->
//                NumberFormat.getInstance(Locale.FRANCE).parse(item)).map(Number::doubleValue)
//                .mapToDouble().sum();
//                double average = sum/elements.size();
        List<WebElement> elements = allElementsOnResultPage;
        if ((elements.size() == 0)) {
            throw new Exception("В коллекции 0 элементов, ничего не выбрано");
        } else {
            double sum = 0;
            for (WebElement element : elements) {
                NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
                Number number = format.parse(element.getText());
                double onePrice = number.doubleValue();
                for (int i = 1; i == 1; ++i) {
                    sum += onePrice;
                }
            }
            System.out.println("Количество товаров = " + elements.size());
            System.out.println("Сумма всех товаров = " + sum);
            System.out.println("Средняя цена за предмет  = " + sum / elements.size());
            return sum / elements.size();
        }
    }

    public boolean checkMiddlePriceIsMoreThan(int comparator) throws Exception {
        double result = getMiddleSumAllItemsOnPage();
        BasePage.switchToDefaultFrameContent();
        boolean flag = false;
        if (result > comparator) {
            System.out.println("Средняя цена за предмет больше чем " + comparator);
            flag = true;
        } else if (result < comparator) {
            System.out.println("Средняя цена за предмет меньше чем " + comparator);
            flag = false;
        }
        if (result == comparator) {
            System.out.println("Средняя цена за предмет равна " + comparator);
            flag = false;
        }
        return flag;
    }

    //Можно еще так, но колхозно
    public void clickToItemOnIframe2(int numberOfItem) {
        driver.findElements(By.xpath("(//a[@class='button button_orange product__button'])[" + numberOfItem + "]"));
    }

    public void switchToIFrameForFastSearchInput() {
        driver.switchTo().frame(driver.findElement(Constants.IFRAME_ON_FAST_INPUT));
    }

}
