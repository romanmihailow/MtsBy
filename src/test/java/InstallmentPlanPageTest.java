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
import ru.aston.InstallmentPlanPage;
import java.time.Duration;

public class InstallmentPlanPageTest {
    private static WebDriver driver;
    public static WebDriverWait wait;
    public static InstallmentPlanPage installmentPlanPage;

    @BeforeClass
    public static void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(ConfProperties.getProperty("url"));
        installmentPlanPage = new InstallmentPlanPage(driver);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Проверяем, есть ли кнопка согласия с cookie
        try {
            WebElement cookieAgreeButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cookie-agree")));
            cookieAgreeButton.click();
        } catch (Exception e) {
            System.out.println("Кнопка согласия с cookie не найдена, продолжаем тест.");
        }
        installmentPlanPage.clickDropDownMenuButton();
        installmentPlanPage.clickInstallmentPlanSelect();
    }
    /// Тест на плейсхолдер "Номер счета"
    @Test
    public void placeHolderInstallmentNumberTest() {
        // Используем метод вместо прямого доступа
        wait.until(ExpectedConditions.visibilityOf(installmentPlanPage.getSubscriberNumberInput()));
        String expectedPlaceHolder = "Номер счета на 44";
        String actualPlaceHolder = installmentPlanPage.getSubscriberNumberPlaceholder();
        Assert.assertEquals(expectedPlaceHolder, actualPlaceHolder);
    }
    /// Тест на плейсхолдер "Сумма"
    @Test
    public void placeHolderSumInstallmentTest() {
        // Используем метод вместо прямого доступа
        wait.until(ExpectedConditions.visibilityOf(installmentPlanPage.getTotalSumInput()));
        String expectedPlaceHolder = "Сумма";
        String actualPlaceHolder = installmentPlanPage.getTotalSumPlaceholder();
        Assert.assertEquals(expectedPlaceHolder, actualPlaceHolder);
    }
    /// Тест на плейсхолдер "Email"
    @Test
    public void placeHolderEmailInstallmentTest() {
        // Используем метод вместо прямого доступа
        wait.until(ExpectedConditions.visibilityOf(installmentPlanPage.getEmailInput()));
        String expectedPlaceHolder = "E-mail для отправки чека";
        String actualPlaceHolder = installmentPlanPage.getEmailPlaceholder();
        Assert.assertEquals(expectedPlaceHolder, actualPlaceHolder);
    }
    @AfterClass
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
