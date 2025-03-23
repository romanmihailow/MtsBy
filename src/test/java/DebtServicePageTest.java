import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DebtServicePageTest {
    public static WebDriver driver;
    public static WebDriverWait wait;
    public static DebtServicePage debtServicePage;

    @BeforeAll
    public static void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(ConfProperties.getProperty("url"));
        debtServicePage = new DebtServicePage(driver);

        System.out.println("start");
        // Проверяем, есть ли кнопка согласия с cookie
        try {
            WebElement cookieAgreeButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cookie-agree")));
            cookieAgreeButton.click();
        } catch (Exception e) {
            System.out.println("Кнопка согласия с cookie не найдена, продолжаем тест.");
        }

        //debtServicePage.clickEmailField();
        //System.out.println("clickEmailField");

        debtServicePage.clickDropDownMenuButton();
        System.out.println("clickDropDownMenuButton");
        debtServicePage.clickDropDownMenuDebtServiceSelect();
        System.out.println("clickDropDownMenuDebtServiceSelect");
    }

    @DisplayName("Тест на плейсхолдер Номер абонента")
    @Test
    public void placeHolderDebtServicePhoneTest() {
        // Используем метод вместо прямого доступа
        wait.until(ExpectedConditions.visibilityOf(debtServicePage.getSubscriberNumberInput()));
        String expectedPlaceHolder = "Номер счета на 2073";
        String actualPlaceHolder = debtServicePage.getSubscriberNumberPlaceholder();
        Assertions.assertEquals(expectedPlaceHolder, actualPlaceHolder);
    }

    @DisplayName("Тест на плейсхолдер Сумма")
    @Test
    public void placeHolderSumTest() {
        // Используем метод вместо прямого доступа
        wait.until(ExpectedConditions.visibilityOf(debtServicePage.getTotalSumInput()));
        String expectedPlaceHolder = "Сумма";
        String actualPlaceHolder = debtServicePage.getTotalSumPlaceholder();
        Assertions.assertEquals(expectedPlaceHolder, actualPlaceHolder);
    }

    @DisplayName("Тест на плейсхолдер Email")
    @Test
    public void placeHolderEmailTest() {
        // Используем метод вместо прямого доступа
        wait.until(ExpectedConditions.visibilityOf(debtServicePage.getEmailInput()));
        String expectedPlaceHolder = "E-mail для отправки чека";
        String actualPlaceHolder = debtServicePage.getEmailPlaceholder();
        Assertions.assertEquals(expectedPlaceHolder, actualPlaceHolder);
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
