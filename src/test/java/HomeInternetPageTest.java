import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.qameta.allure.Description;
import io.qameta.allure.Step;

import java.time.Duration;

public class HomeInternetPageTest {

    public static WebDriver driver;
    public static WebDriverWait wait;
    public static HomeInternetPage homeInternetPage;

    @BeforeAll
    public static void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(ConfProperties.getProperty("url"));
        homeInternetPage = new HomeInternetPage(driver);

        try {
            WebElement cookieAgreeButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cookie-agree")));
            cookieAgreeButton.click();
        } catch (Exception e) {
            System.out.println("Кнопка согласия с cookie не найдена, продолжаем тест.");
        }
        homeInternetPage.clickDropDownMenuButton();
        homeInternetPage.clickDropDownMenuHomeInternetSelect();
    }

    @Step("Проверка плейсхолдера 'Номер абонента'")
    @DisplayName("Тест на плейсхолдер Номер абонента")
    @Test
    @Description("Проверка, что плейсхолдер для поля 'Номер абонента' отображается корректно.")
    public void placeHolderInternetPhoneTest() {
        wait.until(ExpectedConditions.visibilityOf(homeInternetPage.getSubscriberNumberInput()));
        String expectedPlaceHolder = "Номер абонента";
        String actualPlaceHolder = homeInternetPage.getSubscriberNumberPlaceholder();
        Assertions.assertEquals(expectedPlaceHolder, actualPlaceHolder);
    }

    @Step("Проверка плейсхолдера 'Сумма'")
    @DisplayName("Тест на плейсхолдер Сумма")
    @Test
    @Description("Проверка, что плейсхолдер для поля 'Сумма' отображается корректно.")
    public void placeHolderSumTest() {
        wait.until(ExpectedConditions.visibilityOf(homeInternetPage.getTotalSumInput()));
        String expectedPlaceHolder = "Сумма";
        String actualPlaceHolder = homeInternetPage.getTotalSumPlaceholder();
        Assertions.assertEquals(expectedPlaceHolder, actualPlaceHolder);
    }

    @Step("Проверка плейсхолдера 'Email'")
    @DisplayName("Тест на плейсхолдер Email")
    @Test
    @Description("Проверка, что плейсхолдер для поля 'Email' отображается корректно.")
    public void placeHolderEmailTest() {
        wait.until(ExpectedConditions.visibilityOf(homeInternetPage.getEmailInput()));
        String expectedPlaceHolder = "E-mail для отправки чека";
        String actualPlaceHolder = homeInternetPage.getEmailPlaceholder();
        Assertions.assertEquals(expectedPlaceHolder, actualPlaceHolder);
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
