package pages;

import config.WebDriverSettings;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;

public class BasePage extends WebDriverSettings {

    @FindBy(xpath = "//a[@class='button button_orange product__button']")
    private List<WebElement> allElementsOnIframe;
    @FindBy(xpath = "//tr//td//p//a//span")
    private List<WebElement> allElementsOnResultPage;

    public BasePage() {
        PageFactory.initElements(driver, this);
    }

    public void switchToIFrame(By by) {
        driver.switchTo().frame(driver.findElement(by));
    }

    public void switchToDefaultFrameContent() {
        driver.switchTo().defaultContent();
    }

    public void clickToItemOnIframe(int numberOfItem) {
        List<WebElement> elements = allElementsOnIframe;
        elements.get(numberOfItem - 1).click();
    }

    public double getMiddleSumAllItemsOnPage() throws ParseException {
        List<WebElement> elements = allElementsOnResultPage;
        System.out.println("Длина коллекции и количество товаров= " + elements.size());
        double sum = 0;
        for (WebElement element : elements) {
            NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
            Number number = format.parse(element.getText());
            double onePrice = number.doubleValue();
            for (int i = 1; i == 1; ++i) {
                sum += onePrice;
            }
        }
        System.out.println("Сумма всех товаров = " + sum);
        System.out.println("Средняя стоимость товаров = " + sum / elements.size());
        return sum / elements.size();
    }

    public boolean checkMiddlePriceIsMoreThan(int comparator) throws ParseException {
        boolean flag = false;
        if (getMiddleSumAllItemsOnPage() > comparator) {
            System.out.println("Средняя цена за предмет больше чем " + comparator);
            return flag = true;
        } else if (getMiddleSumAllItemsOnPage() < comparator) {
            System.out.println("Средняя цена за предмет меньше чем " + comparator);
            return flag = false;
        }
        if (getMiddleSumAllItemsOnPage() == comparator) {
            System.out.println("Средняя цена за предмет равна " + comparator);
            return flag = false;
        }
        return flag;
    }


    //Можно еще так
    public void clickToItemOnIframe2(int numberOfItem) {
        driver.findElements(By.xpath("(//a[@class='button button_orange product__button'])[" + numberOfItem + "]"));
    }


}
