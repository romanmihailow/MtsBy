import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import io.qameta.allure.Description;
import io.qameta.allure.Step;

public class GPWindowTest {
    public static WebDriver driver;
    public static WebDriverWait wait;
    public static GPayWindow gPayWindow;

    @BeforeAll
    public static void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        gPayWindow = new GPayWindow(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get(ConfProperties.getProperty("url"));

        // Проверяем, есть ли кнопка согласия с cookie
        try {
            WebElement cookieAgreeButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cookie-agree")));
            cookieAgreeButton.click();
        } catch (Exception e) {
            System.out.println("Кнопка согласия с cookie не найдена, продолжаем тест.");
        }

        // Заполнение формы
        gPayWindow.clickPhoneNumberField();
        gPayWindow.sendPhoneNumber(ConfProperties.getProperty("phonenumber"));
        gPayWindow.clickSumField();
        gPayWindow.sendSum(ConfProperties.getProperty("sum"));
        gPayWindow.clickEmailField();
        gPayWindow.sendEmail(ConfProperties.getProperty("email"));
        gPayWindow.clickPayButton();
    }

    @Step("Проверяем, что номер телефона соответствует")
    @DisplayName("Проверяем, что номер телефона соответствует")
    @Test
    @Description("Проверка того, что номер телефона отображается корректно на странице оплаты.")
    public void CheckPhoneNumberTest() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@src='https://checkout.bepaid.by/widget_v2/index.html']")));

        WebElement paySectionTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Оплата')]")));
        String actualText = paySectionTitle.getText().replace("\n", " ").trim();
        String actualPhoneNumber = actualText.replaceAll(".*Номер:(\\d{12}).*", "$1");

        String expectedPhoneNumber = "375297777777";

        Assertions.assertEquals(expectedPhoneNumber, actualPhoneNumber);
        driver.switchTo().defaultContent();
    }

    @Step("Проверка title что сумма соответствует")
    @DisplayName("Проверка title что сумма соответствует")
    @Test
    @Description("Проверка того, что сумма отображается корректно в заголовке.")
    public void CheckTitleSumTest() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@src='https://checkout.bepaid.by/widget_v2/index.html']")));

        WebElement gPayInfoTextSum = wait.until(ExpectedConditions.visibilityOf(gPayWindow.getGPayInfoTextSum()));
        String actualTextGPay = gPayInfoTextSum.getText().replace("\n", " ").trim();
        String actualSum = actualTextGPay.replaceAll(".*?(\\d+\\.\\d{2})(?=\\s*BYN).*", "$1");

        String expectedSum = "100.00";

        Assertions.assertEquals(expectedSum, actualSum);
        driver.switchTo().defaultContent();
    }

    @Step("Проверка суммы пополнения на кнопке")
    @DisplayName("Проверка суммы пополнения на кнопке")
    @Test
    @Description("Проверка того, что сумма отображается на кнопке пополнения.")
    public void CheckButtonSumTest() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@src='https://checkout.bepaid.by/widget_v2/index.html']")));

        WebElement gPayButton = wait.until(ExpectedConditions.visibilityOf(gPayWindow.getGPayInfoButtonSum()));
        String actualTextGPay = gPayButton.getText().replace("\n", " ").trim();
        String actualSum = actualTextGPay.replaceAll(".*?(\\d+\\.\\d{2})(?=\\s*BYN).*", "$1");

        String expectedSum = "100.00";

        Assertions.assertEquals(expectedSum, actualSum);
    }

    @Step("Проверяем, что плейсхолдер (текст) Номер карты отображается корректно")
    @DisplayName("Проверяем, что плейсхолдер (текст) Номер карты отображается корректно")
    @Test
    @Description("Проверка того, что плейсхолдер для номера карты отображается корректно.")
    public void checkNumberCartGPayPlaceholderTest() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@src='https://checkout.bepaid.by/widget_v2/index.html']")));

        WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement cardNumberLabel = longWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='content ng-tns-c2312288139-1']/label[contains(text(), 'Номер карты')]")));

        String actualLabel = cardNumberLabel.getText().trim();
        String expectedLabel = "Номер карты";

        Assertions.assertEquals(expectedLabel, actualLabel);
        driver.switchTo().defaultContent();
    }

    @Step("Проверяем, что плейсхолдер (текст) Срок действия отображается корректно")
    @DisplayName("Проверяем, что плейсхолдер (текст) Срок действия отображается корректно")
    @Test
    @Description("Проверка того, что плейсхолдер для срока действия карты отображается корректно.")
    public void checkDateGPayPlaceholderTest() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@src='https://checkout.bepaid.by/widget_v2/index.html']")));

        WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement cardNumberLabel = longWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='content ng-tns-c2312288139-4']/label[contains(text(), 'Срок действия')]")));

        String actualLabel = cardNumberLabel.getText().trim();
        String expectedLabel = "Срок действия";

        Assertions.assertEquals(expectedLabel, actualLabel);
        driver.switchTo().defaultContent();
    }

    @Step("Проверяем, что плейсхолдер (текст) CVC отображается корректно")
    @DisplayName("Проверяем, что плейсхолдер (текст) CVC отображается корректно")
    @Test
    @Description("Проверка того, что плейсхолдер для CVC отображается корректно.")
    public void checkCvcGPayPlaceholderTest() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@src='https://checkout.bepaid.by/widget_v2/index.html']")));

        WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement cardNumberLabel = longWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='content ng-tns-c2312288139-5']/label[contains(text(), 'CVC')]")));

        String actualLabel = cardNumberLabel.getText().trim();
        String expectedLabel = "CVC";

        Assertions.assertEquals(expectedLabel, actualLabel);
        driver.switchTo().defaultContent();
    }

    @Step("Проверяем, что плейсхолдер (текст) Имя держателя отображается корректно")
    @DisplayName("Проверяем, что плейсхолдер (текст) Имя держателя отображается корректно")
    @Test
    @Description("Проверка того, что плейсхолдер для имени держателя карты отображается корректно.")
    public void checkCartHolderGPayPlaceholderTest() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@src='https://checkout.bepaid.by/widget_v2/index.html']")));

        WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement cardNumberLabel = longWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='content ng-tns-c2312288139-3']/label[contains(text(), 'Имя держателя')]")));

        String actualLabel = cardNumberLabel.getText().trim();
        String expectedLabel = "Имя держателя (как на карте)";

        Assertions.assertEquals(expectedLabel, actualLabel);
        driver.switchTo().defaultContent();
    }

    @Step("Проверка логотипов")
    @DisplayName("Проверка логотипов")
    @Test
    @Description("Проверка отображения логотипов платежных систем на странице.")
    public void checkLogoGPayPlaceholderTest() {
        GPayWindow gPayWindow = new GPayWindow(driver);

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@src='https://checkout.bepaid.by/widget_v2/index.html']")));

        List<WebElement> logos = gPayWindow.getGPayInfoImageHolders();
        wait.until(ExpectedConditions.visibilityOfAllElements(logos));

        Assertions.assertFalse(logos.isEmpty(), "Логотипы платежных систем не найдены!");

        for (WebElement logo : logos) {
            Assertions.assertTrue(logo.isDisplayed(), "Логотип платежной системы не отображается!");
        }

        driver.switchTo().defaultContent();
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
