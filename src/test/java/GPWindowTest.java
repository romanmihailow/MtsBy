

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.aston.ConfProperties;
import ru.aston.GPayWindow;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class GPWindowTest {
    public static WebDriver driver;
    public static WebDriverWait wait;
    public static GPayWindow gPayWindow;

    @BeforeClass
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

    /// Проверяем, что номер телефона соответствует
    @Test
    public void CheckInfoSumTest() {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.tagName("iframe")));
        WebElement paySectionTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Оплата')]")));

        String actualText = paySectionTitle.getText().replace("\n", " ").trim();
        String actualPhoneNumber = actualText.replaceAll(".*Номер:(\\d{12}).*", "$1");
        String expectedPhoneNumber = "375297777777";
        Assert.assertEquals(expectedPhoneNumber, actualPhoneNumber);
    }

    /// Проверяем, что сумма в заголовке соответствует
    @Test
    public void CheckTextSumTest() {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.tagName("iframe")));
        WebElement gPayInfoTextSum = wait.until(ExpectedConditions.visibilityOf(gPayWindow.getGPayInfoTextSum()));

        String actualTextGPay = gPayInfoTextSum.getText().replace("\n", " ").trim();
        String actualSum = actualTextGPay.replaceAll(".*?(\\d+\\.\\d{2})(?=\\s*BYN).*", "$1");

        String expectedSum = "100.00";
        Assert.assertEquals("Сумма не совпадает!", expectedSum, actualSum);
    }

    /// Проверяем, что сумма на кнопке "Оплатить" соответствует
    @Test
    public void CheckButtonSumTest() {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.tagName("iframe")));
        WebElement gPayButton = wait.until(ExpectedConditions.visibilityOf(gPayWindow.getGPayInfoButtonSum()));

        String actualTextGPay = gPayButton.getText().replace("\n", " ").trim();
        String actualSum = actualTextGPay.replaceAll(".*?(\\d+\\.\\d{2})(?=\\s*BYN).*", "$1");

        String expectedSum = "100.00";
        Assert.assertEquals("Сумма не совпадает!", expectedSum, actualSum);
    }

    /// + Проверяем, что плейсхолдер (текст) Номер карты отображается корректно
    @Test
    public void checkNumberCartGPayPlaceholderTest() {
        // Ожидание iframe перед переключением
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@src='https://checkout.bepaid.by/widget_v2/index.html']")));

        // Проверка количества iframe после ожидания
        List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
        System.out.println("Количество iframe на странице после ожидания: " + iframes.size());

        // Ожидание и поиск элемента label (лейбл "Номер карты")
        WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement cardNumberLabel = longWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='content ng-tns-c2312288139-1']/label[contains(text(), 'Номер карты')]")));

        // Получаем текст лейбла
        String actualLabel = cardNumberLabel.getText().trim();
        String expectedLabel = "Номер карты";

        // Проверяем соответствие
        Assert.assertEquals("Лейбл 'Номер карты' не соответствует ожидаемому", expectedLabel, actualLabel);
        Assert.assertTrue("Лейбл 'Номер карты' не найден на странице", cardNumberLabel.isDisplayed());

        // Возвращаемся в основной контент
        driver.switchTo().defaultContent();
    }

    /// Проверяем, что плейсхолдер (текст) Срок действия отображается корректно
    @Test
    public void checkDateGPayPlaceholderTest() {
        // Ожидание iframe перед переключением
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@src='https://checkout.bepaid.by/widget_v2/index.html']")));

        // Проверка количества iframe после ожидания
        List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
        System.out.println("Количество iframe на странице после ожидания: " + iframes.size());

        // Ожидание и поиск элемента label (лейбл "Номер карты")
        WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement cardNumberLabel = longWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='content ng-tns-c2312288139-4']/label[contains(text(), 'Срок действия')]")));

        // Получаем текст лейбла
        String actualLabel = cardNumberLabel.getText().trim();
        String expectedLabel = "Срок действия";

        // Проверяем соответствие
        Assert.assertEquals("Лейбл 'Срок действия' не соответствует ожидаемому", expectedLabel, actualLabel);
        Assert.assertTrue("Лейбл 'Срок действия' не найден на странице", cardNumberLabel.isDisplayed());

        // Возвращаемся в основной контент
        driver.switchTo().defaultContent();
    }

    /// Проверяем, что плейсхолдер (текст) CVC отображается корректно
    @DisplayName("dd")
    @Test
    public void checkCvcGPayPlaceholderTest() {
        // Ожидание iframe перед переключением
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@src='https://checkout.bepaid.by/widget_v2/index.html']")));

        // Проверка количества iframe после ожидания
        List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
        System.out.println("Количество iframe на странице после ожидания: " + iframes.size());

        // Ожидание и поиск элемента label (лейбл "Номер карты")
        WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement cardNumberLabel = longWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='content ng-tns-c2312288139-5']/label[contains(text(), 'CVC')]")));

        // Получаем текст лейбла
        String actualLabel = cardNumberLabel.getText().trim();
        String expectedLabel = "CVC";

        // Проверяем соответствие
        Assert.assertEquals("Лейбл 'CVC' не соответствует ожидаемому", expectedLabel, actualLabel);
        Assert.assertTrue("Лейбл 'CVC' не найден на странице", cardNumberLabel.isDisplayed());

        // Возвращаемся в основной контент
        driver.switchTo().defaultContent();
    }

    /// Проверяем, что плейсхолдер (текст) Имя держателя отображается корректно
    @Test
    public void checkCartHolderGPayPlaceholderTest() {
        // Ожидание iframe перед переключением
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@src='https://checkout.bepaid.by/widget_v2/index.html']")));

        // Проверка количества iframe после ожидания
        List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
        System.out.println("Количество iframe на странице после ожидания: " + iframes.size());

        // Ожидание и поиск элемента label (лейбл "Номер карты")
        WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement cardNumberLabel = longWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='content ng-tns-c2312288139-3']/label[contains(text(), 'Имя держателя')]")));

        // Получаем текст лейбла
        String actualLabel = cardNumberLabel.getText().trim();
        String expectedLabel = "Имя держателя (как на карте)";

        // Проверяем соответствие
        Assert.assertEquals("Лейбл 'Имя держателя' не соответствует ожидаемому", expectedLabel, actualLabel);
        Assert.assertTrue("Лейбл 'Имя держателя' не найден на странице", cardNumberLabel.isDisplayed());

        // Возвращаемся в основной контент
        driver.switchTo().defaultContent();
    }

    @Test
    public void checkLogoGPayPlaceholderTest() {
        GPayWindow gPayWindow = new GPayWindow(driver);

        // Ожидание iframe перед переключением
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@src='https://checkout.bepaid.by/widget_v2/index.html']")));

        // Проверка количества iframe после ожидания
        List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
        System.out.println("Количество iframe на странице после ожидания: " + iframes.size());

        // Ожидание логотипов платежных систем
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        List<WebElement> logos = gPayWindow.getGPayInfoImageHolders();
        wait.until(ExpectedConditions.visibilityOfAllElements(logos));

        // Проверяем, что хотя бы один логотип найден
        Assert.assertFalse("Логотипы платежных систем не найдены!", logos.isEmpty());
        System.out.println("Количество найденных логотипов: " + logos.size());

        // Проверяем, что все найденные логотипы видимы
        for (WebElement logo : logos) {
            Assert.assertTrue("Логотип платежной системы не отображается!", logo.isDisplayed());
        }

        // Возвращаемся в основной контент
        driver.switchTo().defaultContent();
    }

    @AfterClass
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
