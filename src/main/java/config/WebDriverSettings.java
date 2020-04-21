package config;


import constants.Constants;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class WebDriverSettings {

    public static WebDriver driver;
    public static Properties properties;

    public WebDriverSettings() {
        try {
            properties = new Properties();
            FileInputStream ip = new FileInputStream("src/main/resources/config.properties");
            properties.load(ip);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void initialisation() throws Exception {
        String browserName = properties.getProperty("browser");

        if (browserName.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver",
                    properties.getProperty("chromedriverPath"));
            driver = new ChromeDriver();

        } else if (browserName.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver",
                    properties.getProperty("geckodriverPath"));
            driver = new FirefoxDriver();
        } else {
            throw new Exception("Вы можете выбрать только CHROME или FIREFOX браузер");
        }

        driver.manage().deleteAllCookies();
        driver.manage().window().setSize(new Dimension(1000, 700));
        driver.manage().timeouts().implicitlyWait(Constants.TIME_IMPLICITLY_WAIT, TimeUnit.SECONDS);
        driver.get(properties.getProperty("url"));

    }

    public void tearDown() {
        driver.quit();
    }

}
