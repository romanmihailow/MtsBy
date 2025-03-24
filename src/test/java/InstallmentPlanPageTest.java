import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.qameta.allure.Step;
import io.qameta.allure.Description;

import java.time.Duration;

public class InstallmentPlanPageTest {

    private static WebDriver driver;
    public static WebDriverWait wait;
    public static InstallmentPlanPage installmentPlanPage;

    @BeforeAll
    public static void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(ConfProperties.getProperty("url"));
        installmentPlanPage = new InstallmentPlanPage(driver);

        try {
            WebElement cookieAgreeButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cookie-agree")));
            cookieAgreeButton.click();
        } catch (Exception e) {
            System.out.println("Кнопка согласия с cookie не найдена, продолжаем тест.");
        }
        installmentPlanPage.clickDropDownMenuButton();
        installmentPlanPage.clickInstallmentPlanSelect();
    }

    @Step("Проверка плейсхолдера 'Номер счета'")
    @DisplayName("Тест на плейсхолдер Номер счета")
    @Test
    @Description("Проверка, что плейсхолдер для поля 'Номер счета' отображается корректно.")
    public void placeHolderInstallmentNumberTest() {
        wait.until(ExpectedConditions.visibilityOf(installmentPlanPage.getSubscriberNumberInput()));
        String expectedPlaceHolder = "Номер счета на 44";
        String actualPlaceHolder = installmentPlanPage.getSubscriberNumberPlaceholder();
        Assertions.assertEquals(expectedPlaceHolder, actualPlaceHolder);
    }

    @Step("Проверка плейсхолдера 'Сумма'")
    @DisplayName("Тест на плейсхолдер Сумма")
    @Test
    @Description("Проверка, что плейсхолдер для поля 'Сумма' отображается корректно.")
    public void placeHolderSumInstallmentTest() {
        wait.until(ExpectedConditions.visibilityOf(installmentPlanPage.getTotalSumInput()));
        String expectedPlaceHolder = "Сумма";
        String actualPlaceHolder = installmentPlanPage.getTotalSumPlaceholder();
        Assertions.assertEquals(expectedPlaceHolder, actualPlaceHolder);
    }

    @Step("Проверка плейсхолдера 'Email'")
    @DisplayName("Тест на плейсхолдер Email")
    @Test
    @Description("Проверка, что плейсхолдер для поля 'Email' отображается корректно.")
    public void placeHolderEmailInstallmentTest() {
        wait.until(ExpectedConditions.visibilityOf(installmentPlanPage.getEmailInput()));
        String expectedPlaceHolder = "E-mail для отправки чека";
        String actualPlaceHolder = installmentPlanPage.getEmailPlaceholder();
        Assertions.assertEquals(expectedPlaceHolder, actualPlaceHolder);
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
