import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CommunicationServicePageTest {
    public static WebDriver driver;
    public static WebDriverWait wait;
    public static CommunicationServicePage communicationServicePage;

    @BeforeAll
    public static void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        communicationServicePage = new CommunicationServicePage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get(ConfProperties.getProperty("url"));
        try {
            WebElement cookieAgreeButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cookie-agree")));
            cookieAgreeButton.click();
        } catch (Exception e) {
            System.out.println("Кнопка согласия с cookie не найдена, продолжаем тест.");
        }
    }

    @DisplayName("Проверка title Онлайн пополнение без комиссии")
    @Test
    @Description("Проверка заголовка блока 'Онлайн пополнение без комиссии'")
    @Step("Проверяем заголовок блока 'Онлайн пополнение без комиссии'")
    public void checkBlockNameTest() {
        WebElement paySectionTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='pay-section']//h2")));
        String expectedTitle = "Онлайн пополнение без комиссии";
        String actualTitle = paySectionTitle.getText().replace("\n", " ").trim();
        Assertions.assertEquals(expectedTitle, actualTitle, "title окна Онлайн пополнение без комиссии не соответствует ожидаемому результату");
    }

    @DisplayName("Проверка видимости логотипов")
    @Test
    @Description("Проверка видимости логотипов платежных систем")
    @Step("Проверяем видимость логотипов платежных систем")
    public void visibilityPaymentLogoSystemTest() {
        for (WebElement logo : communicationServicePage.getPaymentLogos()) {
            wait.until(ExpectedConditions.visibilityOf(logo));
            Assertions.assertTrue(logo.isDisplayed(), "Логотип " + logo.getAttribute("alt") + " не отображается");
        }
    }

    @DisplayName("Проверка ссылки информация о сервисе")
    @Test
    @Description("Проверка перехода по ссылке 'Информация о сервисе'")
    @Step("Проверяем переход по ссылке 'Информация о сервисе'")
    public void clickLinkInfoServiceTest() {
        communicationServicePage.clickPaySectionLink();
        WebElement titlePageInfoPayCard = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[normalize-space(text())='Оплата банковской картой']")));
        String expectedTitle = "Оплата банковской картой";
        String actualTitle = titlePageInfoPayCard.getText();
        Assertions.assertEquals(expectedTitle, actualTitle, "Заголовок формы не соответствует");
    }

    @DisplayName("Проверка плейсхолдера номер телефона")
    @Test
    @Description("Проверка плейсхолдера для поля 'Номер телефона'")
    @Step("Проверяем плейсхолдер для поля 'Номер телефона'")
    public void checkPhonePlaceholderTest() {
        WebElement phoneInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@placeholder='Номер телефона']")));
        String expectedPlaceholder = "Номер телефона";
        String actualPlaceholder = phoneInput.getAttribute("placeholder");
        Assertions.assertEquals(expectedPlaceholder, actualPlaceholder, "Плейсхолдер для номера телефона не соответствует ожидаемому");
    }

    @DisplayName("Проверка плейсхолдера сумма")
    @Test
    @Description("Проверка плейсхолдера для поля 'Сумма'")
    @Step("Проверяем плейсхолдер для поля 'Сумма'")
    public void checkSumPlaceholderTest() {
        WebElement totalSumInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@placeholder='Сумма']")));
        String expectedPlaceholder = "Сумма";
        String actualPlaceholder = totalSumInput.getAttribute("placeholder");
        Assertions.assertEquals(expectedPlaceholder, actualPlaceholder, "Плейсхолдер для суммы не соответствует ожидаемому");
    }

    @DisplayName("Проверка плейсхолдера Email")
    @Test
    @Description("Проверка плейсхолдера для поля 'E-mail'")
    @Step("Проверяем плейсхолдер для поля 'E-mail'")
    public void checkEmailPlaceholderTest() {
        WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@placeholder='E-mail для отправки чека']")));
        String expectedPlaceholder = "E-mail для отправки чека";
        String actualPlaceholder = emailInput.getAttribute("placeholder");
        Assertions.assertEquals(expectedPlaceholder, actualPlaceholder, "Плейсхолдер для E-mail не соответствует ожидаемому");
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
