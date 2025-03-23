import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/// ВСЕ ТЕСТЫ ПРОХОДЯТ ///

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

//    @Test
//    public void FormPayButtonTest() {
//        driver.navigate().refresh();
//
//        communicationServicePage.clickPhoneNumberField();
//        communicationServicePage.sendPhoneNumber(ConfProperties.getProperty("phonenumber"));
//        communicationServicePage.clickSumField();
//        communicationServicePage.sendSum(ConfProperties.getProperty("sum"));
//        communicationServicePage.clickEmailField();
//        communicationServicePage.sendEmail(ConfProperties.getProperty("email"));
//        communicationServicePage.clickPayButton();
//
//        // Ожидание и переключение на iframe, как в примере с GPayWindow
//        new WebDriverWait(driver, Duration.ofSeconds(5))
//                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@src='https://checkout.bepaid.by/widget_v2/index.html']")));
//
//        WebElement paySectionTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Оплата')]")));
//        String expectedTitle = "Оплата: Услуги связи Номер:375297777777";
//        String actualTitle = paySectionTitle.getText().replace("\n", " ").trim();
//        Assertions.assertEquals(expectedTitle, actualTitle, "Тест провален");
//    }


    @DisplayName("Проверка title Онлайн пополнение без комиссии")
    @Test
    public void checkBlockNameTest() {

        WebElement paySectionTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='pay-section']//h2")));
        String expectedTitle = "Онлайн пополнение без комиссии";
        String actualTitle = paySectionTitle.getText().replace("\n", " ").trim();
        Assertions.assertEquals(expectedTitle, actualTitle, "title окна Онлайн пополнение без комиссии не соответствует ожидаемому результату");
    }

    @DisplayName("Проверка видимости логотипов")
    @Test
    public void visibilityPaymentLogoSystemTest() {
        for (WebElement logo : communicationServicePage.getPaymentLogos()) {
            wait.until(ExpectedConditions.visibilityOf(logo));
            Assertions.assertTrue(logo.isDisplayed(), "Логотип " + logo.getAttribute("alt") + " не отображается");
        }
    }

    @DisplayName("Проверка ссыдки информация")
    @Test
    public void clickLinkInfoServiceTest() {

        communicationServicePage.clickPaySectionLink();
        WebElement titlePageInfoPayCard = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[normalize-space(text())='Оплата банковской картой']\n")));
        String expectedTitle = "Оплата банковской картой";
        String actualTitle = titlePageInfoPayCard.getText();
        Assertions.assertEquals(expectedTitle, actualTitle, "Заголовок формы не соответствует");
    }

    @DisplayName("Проверка плейсхолдера номер телефона")
    @Test
    public void checkPhonePlaceholderTest() {
        WebElement phoneInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@placeholder='Номер телефона']")));
        String expectedPlaceholder = "Номер телефона";
        String actualPlaceholder = phoneInput.getAttribute("placeholder");

        Assertions.assertEquals(expectedPlaceholder, actualPlaceholder, "Плейсхолдер для номера телефона не соответствует ожидаемому");
    }

    @DisplayName("Проверка плейсхолдера сумма")
    @Test
    public void checkSumPlaceholderTest() {
        WebElement totalSumInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@placeholder='Сумма']")));
        String expectedPlaceholder = "Сумма";
        String actualPlaceholder = totalSumInput.getAttribute("placeholder");

        Assertions.assertEquals(expectedPlaceholder, actualPlaceholder, "Плейсхолдер для суммы не соответствует ожидаемому");
    }

    @DisplayName("Проверка плейсхолдера Email")
    @Test
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
