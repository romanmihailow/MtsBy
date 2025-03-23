import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

/// ВСЕ ТЕСТЫ ПРОХОДЯТ ///

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

    @DisplayName("Проверяем, что номер телефона соответствует")
    @Test
    public void CheckPhoneNumberTest() {
        // Ожидание и переключение на iframe, как в примере с GPayWindow
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@src='https://checkout.bepaid.by/widget_v2/index.html']")));

        // Ожидание элемента с текстом "Оплата"
        WebElement paySectionTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Оплата')]")));

        // Получаем текст из элемента и очищаем его
        String actualText = paySectionTitle.getText().replace("\n", " ").trim();

        // Извлекаем номер телефона с помощью регулярного выражения
        String actualPhoneNumber = actualText.replaceAll(".*Номер:(\\d{12}).*", "$1");

        // Ожидаемый номер телефона
        String expectedPhoneNumber = "375297777777";

        // Проверяем, что номер телефона соответствует ожидаемому
        Assertions.assertEquals(expectedPhoneNumber, actualPhoneNumber);

        // Возвращаемся в основной контент после проверки
        driver.switchTo().defaultContent();
    }



    @DisplayName("Проверка title что сумма соответствует")
    @Test
    public void CheckTitleSumTest() {
        // Ожидание и переключение на iframe
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@src='https://checkout.bepaid.by/widget_v2/index.html']")));

        // Ожидание элемента с текстом суммы
        WebElement gPayInfoTextSum = wait.until(ExpectedConditions.visibilityOf(gPayWindow.getGPayInfoTextSum()));

        // Получаем текст из элемента и очищаем его
        String actualTextGPay = gPayInfoTextSum.getText().replace("\n", " ").trim();

        // Извлекаем сумму с помощью регулярного выражения
        String actualSum = actualTextGPay.replaceAll(".*?(\\d+\\.\\d{2})(?=\\s*BYN).*", "$1");

        // Ожидаемая сумма
        String expectedSum = "100.00";

        // Проверяем, что сумма соответствует ожидаемой
        Assertions.assertEquals(expectedSum, actualSum);

        // Возвращаемся в основной контент после проверки
        driver.switchTo().defaultContent();
    }


    @DisplayName("Проверка суммы пополнения на кнопке")
    @Test
    public void CheckButtonSumTest() {
        // Ожидание и переключение на iframe с конкретным источником
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@src='https://checkout.bepaid.by/widget_v2/index.html']")));

        // Теперь получаем кнопку с информацией о сумме после переключения на iframe
        WebElement gPayButton = wait.until(ExpectedConditions.visibilityOf(gPayWindow.getGPayInfoButtonSum()));

        // Получаем текст с кнопки и убираем лишние символы
        String actualTextGPay = gPayButton.getText().replace("\n", " ").trim();

        // Извлекаем сумму из текста кнопки
        String actualSum = actualTextGPay.replaceAll(".*?(\\d+\\.\\d{2})(?=\\s*BYN).*", "$1");

        // Ожидаемая сумма на кнопке
        String expectedSum = "100.00";

        // Проверяем, что извлеченная сумма совпадает с ожидаемой
        Assertions.assertEquals(expectedSum, actualSum);
    }


    @DisplayName("Проверяем, что плейсхолдер (текст) Номер карты отображается корректно")
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
        Assertions.assertEquals(expectedLabel, actualLabel);
        //Assertions.assertTrue("Лейбл 'Номер карты' не найден на странице", cardNumberLabel.isDisplayed());

        // Возвращаемся в основной контент
        driver.switchTo().defaultContent();
    }

    @DisplayName("Проверяем, что плейсхолдер (текст) Срок действия отображается корректно")
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
        Assertions.assertEquals(expectedLabel, actualLabel);
        //Assertions.assertTrue("Лейбл 'Срок действия' не найден на странице", cardNumberLabel.isDisplayed());

        // Возвращаемся в основной контент
        driver.switchTo().defaultContent();
    }

    @DisplayName("Проверяем, что плейсхолдер (текст) CVC отображается корректно")
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
        Assertions.assertEquals(expectedLabel, actualLabel);
        //Assertions.assertTrue("Лейбл 'CVC' не найден на странице", cardNumberLabel.isDisplayed());

        // Возвращаемся в основной контент
        driver.switchTo().defaultContent();
    }

    @DisplayName("Проверяем, что плейсхолдер (текст) Имя держателя отображается корректно")
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
        Assertions.assertEquals(expectedLabel, actualLabel);
        //Assertions.assertTrue("Лейбл 'Имя держателя' не найден на странице", cardNumberLabel.isDisplayed());

        // Возвращаемся в основной контент
        driver.switchTo().defaultContent();
    }
    @DisplayName("Проверка логотипов")
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
        Assertions.assertFalse(logos.isEmpty(), "Логотипы платежных систем не найдены!");
        System.out.println("Количество найденных логотипов: " + logos.size());

        // Проверяем, что все найденные логотипы видимы
        for (WebElement logo : logos) {
            Assertions.assertTrue(logo.isDisplayed(), "Логотип платежной системы не отображается!");
        }

        // Возвращаемся в основной контент
        driver.switchTo().defaultContent();
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
