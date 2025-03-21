import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.aston.ConfProperties;
import ru.aston.HomeInternetPage;

import java.time.Duration;

public class HomeInternetPageTest {
    public static WebDriver driver;
    public static WebDriverWait wait;
    public static HomeInternetPage homeInternetPage;

    @BeforeClass
    public static void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(ConfProperties.getProperty("url"));
        homeInternetPage = new HomeInternetPage(driver);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Проверяем, есть ли кнопка согласия с cookie
        try {
            WebElement cookieAgreeButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cookie-agree")));
            cookieAgreeButton.click();
        } catch (Exception e) {
            System.out.println("Кнопка согласия с cookie не найдена, продолжаем тест.");
        }
        homeInternetPage.clickDropDownMenuButton();
        homeInternetPage.clickDropDownMenuHomeInternetSelect();
    }

    /// Тест на плейсхолдер "Номер абонента"
    @Test
    public void placeHolderInternetPhoneTest() {
        // Используем метод вместо прямого доступа
        wait.until(ExpectedConditions.visibilityOf(homeInternetPage.getSubscriberNumberInput()));
        String expectedPlaceHolder = "Номер абонента";
        String actualPlaceHolder = homeInternetPage.getSubscriberNumberPlaceholder();
        Assert.assertEquals(expectedPlaceHolder, actualPlaceHolder);
    }

    /// Тест на плейсхолдер "Сумма"
    @Test
    public void placeHolderSumTest() {
        // Используем метод вместо прямого доступа
        wait.until(ExpectedConditions.visibilityOf(homeInternetPage.getTotalSumInput()));
        String expectedPlaceHolder = "Сумма";
        String actualPlaceHolder = homeInternetPage.getTotalSumPlaceholder();
        Assert.assertEquals(expectedPlaceHolder, actualPlaceHolder);
    }
    /// Тест на плейсхолдер "Email"
    @Test
    public void placeHolderEmailTest() {
        // Используем метод вместо прямого доступа
        wait.until(ExpectedConditions.visibilityOf(homeInternetPage.getEmailInput()));
        String expectedPlaceHolder = "E-mail для отправки чека";
        String actualPlaceHolder = homeInternetPage.getEmailPlaceholder();
        Assert.assertEquals(expectedPlaceHolder, actualPlaceHolder);
    }


    @AfterClass
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
