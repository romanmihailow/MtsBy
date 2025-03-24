import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class GPayWindow {
    public WebDriver driver;

    public GPayWindow(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//span[contains(text(), 'BYN')]")
    private WebElement gPayInfoTextSum;

    @FindBy(xpath = "//button[contains(text(), 'Оплатить') and contains(text(), 'BYN')]")
    private WebElement gPayInfoButtonSum;

    @FindBy(id = "connection-phone")
    private WebElement connectionPhoneNumber;

    @FindBy(id = "connection-sum")
    private WebElement connectionSum;

    @FindBy(id = "connection-email")
    private WebElement connectionEmail;

    @FindBy(xpath = "//*[@id=\"pay-connection\"]/button")
    private WebElement payConnectionButton;

    @FindBy(xpath = "//label[text() = 'Номер карты']")
    private WebElement gPayInfoNumberCart;

    @FindBy(xpath = "//label[text() = 'Срок действия']")
    private WebElement gPayInfoDateCart;

    @FindBy(xpath = "//label[text() = 'CVC']")
    private WebElement gPayInfoCvcCart;

    @FindBy(xpath = "//label[text() = 'Имя держателя (как на карте)']")
    private WebElement gPayInfoNameHolderCart;

    @FindBy(xpath = "//img[contains(@src, 'visa-system.svg') or contains(@src, 'mastercard-system.svg') or contains(@src, 'belkart-system.svg') or contains(@src, 'mir-system.svg') or contains(@src, 'maestro-system.svg')]")
    private List<WebElement> gPayInfoImageHolders;

    @Step("Получаем плейсхолдер для номера карты")
    @Description("Метод возвращает placeholder для поля 'Номер карты'")
    public String getGPayInfoNumberCartPlaceholder() {
        return gPayInfoNumberCart.getAttribute("placeholder");
    }

    @Step("Получаем плейсхолдер для срока действия карты")
    @Description("Метод возвращает placeholder для поля 'Срок действия'")
    public String getGPayInfoDateCartPlaceholder() {
        return gPayInfoDateCart.getAttribute("placeholder");
    }

    @Step("Получаем плейсхолдер для CVC карты")
    @Description("Метод возвращает placeholder для поля 'CVC'")
    public String getGPayInfoCvcCartPlaceholder() {
        return gPayInfoCvcCart.getAttribute("placeholder");
    }

    @Step("Получаем плейсхолдер для имени держателя карты")
    @Description("Метод возвращает placeholder для поля 'Имя держателя (как на карте)'")
    public String getGPayInfoNameHolderCartPlaceholder() {
        return gPayInfoNameHolderCart.getAttribute("placeholder");
    }

    @Step("Кликаем на поле 'Номер телефона'")
    @Description("Метод кликает на поле для ввода номера телефона")
    public void clickPhoneNumberField() {
        connectionPhoneNumber.click();
    }

    @Step("Вводим номер телефона {phoneNumber}")
    @Description("Метод вводит номер телефона в поле")
    public void sendPhoneNumber(String phoneNumber) {
        connectionPhoneNumber.sendKeys(phoneNumber);
    }

    @Step("Кликаем на поле 'Сумма'")
    @Description("Метод кликает на поле для ввода суммы")
    public void clickSumField() {
        connectionSum.click();
    }

    @Step("Вводим сумму {sum}")
    @Description("Метод вводит сумму в поле")
    public void sendSum(String sum) {
        connectionSum.sendKeys(sum);
    }

    @Step("Кликаем на поле 'Email'")
    @Description("Метод кликает на поле для ввода email")
    public void clickEmailField() {
        connectionEmail.click();
    }

    @Step("Вводим email {email}")
    @Description("Метод вводит email в поле")
    public void sendEmail(String email) {
        connectionEmail.sendKeys(email);
    }

    @Step("Кликаем на кнопку 'Оплатить'")
    @Description("Метод кликает на кнопку 'Оплатить'")
    public void clickPayButton() {
        payConnectionButton.click();
    }

    @Step("Получаем текст суммы на экране")
    @Description("Метод получает текст суммы, отображаемой на экране")
    public WebElement getGPayInfoTextSum() {
        return gPayInfoTextSum;
    }

    @Step("Получаем кнопку с суммой")
    @Description("Метод получает кнопку, на которой отображается сумма")
    public WebElement getGPayInfoButtonSum() {
        return gPayInfoButtonSum;
    }

    @Step("Получаем список логотипов платежных систем")
    @Description("Метод получает все логотипы платежных систем на странице")
    public List<WebElement> getGPayInfoImageHolders() {
        return gPayInfoImageHolders;
    }
}
