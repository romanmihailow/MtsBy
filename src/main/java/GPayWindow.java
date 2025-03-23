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

    /// Отображение суммы
    @FindBy(xpath = "//span[contains(text(), 'BYN')]")
    private WebElement gPayInfoTextSum;
    /// Отображение суммы на кнопке
    @FindBy(xpath = "//button[contains(text(), 'Оплатить') and contains(text(), 'BYN')]")
    private WebElement gPayInfoButtonSum;


    /// Поля формы
    @FindBy(id = "connection-phone")
    private WebElement connectionPhoneNumber;
    @FindBy(id = "connection-sum")
    private WebElement connectionSum;
    @FindBy(id = "connection-email")
    private WebElement connectionEmail;
    @FindBy(xpath = "//*[@id=\"pay-connection\"]/button")
    private WebElement payConnectionButton;

    /// Locator CART pay
    /// Number cart
    @FindBy(xpath = "//label[text() = 'Номер карты']")
    private WebElement gPayInfoNumberCart;
    /// Date Cart
    @FindBy(xpath = "//label[text() = 'Срок действия']")
    private WebElement gPayInfoDateCart;
    /// CVC
    @FindBy(xpath = "//label[text() = 'CVC']")
    private WebElement gPayInfoCvcCart;
    @FindBy(xpath = "//label[text() = 'Имя держателя (как на карте)']")
    private WebElement gPayInfoNameHolderCart;

    /// Локаторы Logo payment
    @FindBy(xpath = "//img[contains(@src, 'visa-system.svg') or contains(@src, 'mastercard-system.svg') or contains(@src, 'belkart-system.svg') or contains(@src, 'mir-system.svg') or contains(@src, 'maestro-system.svg')]")
    private List<WebElement> gPayInfoImageHolders;

    /// visa
    @FindBy(xpath = "//img[contains(@src, 'visa-system.svg')]")
    private WebElement gPayInfoImageHolderVisa;
    /// mastercard
    @FindBy(xpath = "//img[contains(@src, 'mastercard-system.svg')]")
    private WebElement gPayInfoImageHolderMasterCard;
    /// belcart
    @FindBy(xpath = "//img[contains(@src, 'belkart-system.svg')]")
    private WebElement gPayInfoImageHolderBelkart;
    /// mir
    @FindBy(xpath = "//img[contains(@src, 'mir-system.svg')]")
    private WebElement gPayInfoImageHolderMir;
    /// maestro
    @FindBy(xpath = "//img[contains(@src, 'maestro-system.svg')]")
    private WebElement gPayInfoImageHolderMaestro;

    /// Методы Placeholder
    public String getGPayInfoNumberCartPlaceholder() {
        return gPayInfoNumberCart.getAttribute("placeholder");
    }
    public String getGPayInfoDateCartPlaceholder() {
        return gPayInfoDateCart.getAttribute("placeholder");
    }
    public String getGPayInfoCvcCartPlaceholder() {
        return gPayInfoCvcCart.getAttribute("placeholder");
    }
    public String getGPayInfoNameHolderCartPlaceholder() {
        return gPayInfoNameHolderCart.getAttribute("placeholder");
    }
    public WebElement getGPayInfoNumberCart() {
        return gPayInfoNumberCart;
    }
    public WebElement getGPayInfoDateCart() {
        return gPayInfoDateCart;
    }
    public WebElement getGPayInfoCvcCart() {
        return gPayInfoCvcCart;
    }
    public WebElement getGPayInfoNameHolderCart() {
        return gPayInfoNameHolderCart;
    }






    /// Методы для взаимодействия с полями
    public void clickPhoneNumberField() {
        connectionPhoneNumber.click();
    }
    public void sendPhoneNumber(String phoneNumber) {
        connectionPhoneNumber.sendKeys(phoneNumber);
    }
    public void clickSumField() {
        connectionSum.click();
    }
    public void sendSum(String sum) {
        connectionSum.sendKeys(sum);
    }
    public void clickEmailField() {
        connectionEmail.click();
    }
    public void sendEmail(String email) {
        connectionEmail.sendKeys(email);
    }
    public void clickPayButton() {
        payConnectionButton.click();
    }


    /// Методы для получения элементов
    public WebElement getGPayInfoTextSum() {
        return gPayInfoTextSum;
    }
    public WebElement getGPayInfoButtonSum() {
        return gPayInfoButtonSum;
    }
    public List<WebElement> getGPayInfoImageHolders() {
        return gPayInfoImageHolders;
    }

}
