import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.aston.CommunicationServicePage;
import ru.aston.ConfProperties;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class CommunicationServicePageTest {
    public static WebDriver driver;
    public static WebDriverWait wait;
    public static ChromeDriver chromeDriver;
    public static CommunicationServicePage communicationServicePage;


    @BeforeClass
    public static void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        communicationServicePage = new CommunicationServicePage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get(ConfProperties.getProperty("url"));
    }

    /// Проверяем открылось ли pop-up окно
    @Test
    public void cookiePopUpTitleTest() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(communicationServicePage.cookiePageTitle));
        String expectedTitle = "Обработка файлов cookie";
        String actualTitle = communicationServicePage.shouldTitleUpPopUpWindow();
        Assert.assertEquals("Заголовок поп-ап окна не соответствует ожидаемому тексту", expectedTitle, actualTitle);
    }

    /// Проверяем наименование блока Онлайн пополнение без комиссии
    @Test
    public void checkBlockNameTest() {
        communicationServicePage.clickCookieAgreeButton();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement paySectionTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='pay-section']//h2")));
        String expectedTitle = "Онлайн пополнение без комиссии";
        String actualTitle = paySectionTitle.getText().replace("\n", " ").trim();
        Assert.assertEquals("title окна Онлайн пополнение без комиссии не соответствует ожидаемому результату", expectedTitle, actualTitle);
    }

    /// Проверяем видимость логотипов платежных систем
    @Test
    public void visibilityPaymentLogoSystemTest() {
        // Ждем, пока все логотипы станут видимыми
        for (WebElement logo : communicationServicePage.getPaymentLogos()) {
            wait.until(ExpectedConditions.visibilityOf(logo));
            Assert.assertTrue("Логотип " + logo.getAttribute("alt") + " не отображается", logo.isDisplayed());
        }
//        // Ждем, что изображения будут видны
//        wait.until(ExpectedConditions.visibilityOf(communicationServicePage.getPaySectionImageVisa()));
//        wait.until(ExpectedConditions.visibilityOf(communicationServicePage.getPaySectionImageVerifiedByVisa()));
//        wait.until(ExpectedConditions.visibilityOf(communicationServicePage.getPaySectionImageMasterCard()));
//        wait.until(ExpectedConditions.visibilityOf(communicationServicePage.getPaySectionImageMasterCardSecureCode()));
//        wait.until(ExpectedConditions.visibilityOf(communicationServicePage.getPaySectionImageBelCart()));
//        // Проверяем, что все логотипы видны
//        Assert.assertTrue("Логотип Visa не отображается", communicationServicePage.getPaySectionImageVisa().isDisplayed());
//        Assert.assertTrue("Логотип VerifiedByVisa не отображается", communicationServicePage.getPaySectionImageVerifiedByVisa().isDisplayed());
//        Assert.assertTrue("Логотип MasterCard не отображается", communicationServicePage.getPaySectionImageMasterCard().isDisplayed());
//        Assert.assertTrue("Логотип MasterCardSecureCode не отображается", communicationServicePage.getPaySectionImageMasterCardSecureCode().isDisplayed());
//        Assert.assertTrue("Логотип BelCart не отображается", communicationServicePage.getPaySectionImageBelCart().isDisplayed());
    }

    /// Проверяем работу ссылки "Подробнее о сервисе"
    @Test
    public void clickLinkInfoServiceTest() {
        communicationServicePage.clickCookieAgreeButton();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        communicationServicePage.clickPaySectionLink();
        WebElement titlePageInfoPayCard = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[6]/main/div/div[4]/h3[1]")));
        String expectedTitle = "Оплата банковской картой";
        String actualTitle = titlePageInfoPayCard.getText();
        Assert.assertEquals("Заголовок формы не соответствует", expectedTitle, actualTitle);
    }

    /// Проверяем работу кнопки "Продолжить" формы онлайн пополнения
    @Test
    public void FormPayButtonTest() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Проверяем, есть ли кнопка согласия с cookie
        try {
            WebElement cookieAgreeButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cookie-agree")));
            cookieAgreeButton.click();
        } catch (Exception e) {
            System.out.println("Кнопка согласия с cookie не найдена, продолжаем тест.");
        }

        // Заполнение формы
        communicationServicePage.clickPhoneNumberField();
        communicationServicePage.sendPhoneNumber(ConfProperties.getProperty("phonenumber"));
        communicationServicePage.clickSumField();
        communicationServicePage.sendSum(ConfProperties.getProperty("sum"));
        communicationServicePage.clickEmailField();
        communicationServicePage.sendEmail(ConfProperties.getProperty("email"));
        communicationServicePage.clickPayButton();

        // Ожидание появления iframe и автоматическое переключение
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.tagName("iframe")));

        // Ожидание появления заголовка внутри iframe
        WebElement paySectionTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Оплата')]")));

        // Проверка заголовка
        String expectedTitle = "Оплата: Услуги связи Номер:375297777777";
        String actualTitle = paySectionTitle.getText().replace("\n", " ").trim();
        Assert.assertEquals("Тест провален", expectedTitle, actualTitle);


        //Проверка суммы в заголовке и на кнопке
        String expectedSumInfoButton = "100";
        String actualSumInfoButton = communicationServicePage.getGPayInfoButtonSum();














    }

    /// Проверяем, что плейсхолдер для номера телефона отображается правильно
    @Test
    public void checkPhonePlaceholderTest() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Ожидаем появления поля для ввода телефона
        WebElement phoneInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@placeholder='Номер телефона']")));
        String expectedPlaceholder = "Номер телефона";
        String actualPlaceholder = phoneInput.getAttribute("placeholder");

        Assert.assertEquals("Плейсхолдер для номера телефона не соответствует ожидаемому", expectedPlaceholder, actualPlaceholder);
    }
    /// Проверяем, что плейсхолдер для суммы отображается правильно
    @Test
    public void checkSumPlaceholderTest() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Ожидаем появления поля для ввода суммы
        WebElement totalSumInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@placeholder='Сумма']")));
        String expectedPlaceholder = "Сумма";
        String actualPlaceholder = totalSumInput.getAttribute("placeholder");

        Assert.assertEquals("Плейсхолдер для суммы не соответствует ожидаемому", expectedPlaceholder, actualPlaceholder);
    }

    /// Проверяем, что плейсхолдер для email отображается правильно
    @Test
    public void checkEmailPlaceholderTest() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Ожидаем появления поля для ввода email
        WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@placeholder='E-mail для отправки чека']")));
        String expectedPlaceholder = "E-mail для отправки чека";
        String actualPlaceholder = emailInput.getAttribute("placeholder");

        Assert.assertEquals("Плейсхолдер для E-mail не соответствует ожидаемому", expectedPlaceholder, actualPlaceholder);
    }








    @AfterClass
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
