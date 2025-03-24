import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CommunicationServicePage {
    public WebDriver driver;

    public CommunicationServicePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//div[contains(@class, 'cookie')]/h3")
    public WebElement cookiePageTitle;

    @FindBy(xpath = "//button[contains(@id, 'cookie-agree')]")
    private WebElement cookieAgreeButton;

    @FindBy(xpath = "//*[@id='pay-section']//h2")
    private WebElement paySectionTitle;

    @FindBy(xpath = "//ul/li/img[contains(@alt, 'Visa') or contains(@alt, 'MasterCard') or contains(@alt, 'BelCart')]")
    private List<WebElement> paymentLogos;

    @FindBy(xpath = "//*[@id=\"pay-section\"]/div/div/div[2]/section/div/a")
    private WebElement clickPaySectionLink;

    @FindBy(xpath = "/html/body/div[6]/main/div/div[4]/h3[1]")
    private WebElement titlePageInfoPayCard;

    @FindBy(xpath = "//*[@id=\"connection-phone\"]")
    private WebElement connectionPhoneNumber;

    @FindBy(xpath = "//*[@id=\"connection-sum\"]")
    private WebElement connectionSum;

    @FindBy(xpath = "//*[@id=\"connection-email\"]")
    private WebElement connectionEmail;

    @FindBy(xpath = "//*[@id=\"pay-connection\"]/button")
    private WebElement payConnectionButton;

    @FindBy(xpath = "/html/body/app-root/div/div/div/app-payment-container/section/div/div/div[2]/span")
    private WebElement paySectionTitleGPay;

    @FindBy(xpath = "//*[@placeholder='Номер телефона']")
    private WebElement phoneInput;

    @FindBy(xpath = "//*[@placeholder='Сумма']")
    private WebElement totalSumInput;

    @FindBy(xpath = "//*[@placeholder='E-mail для отправки чека']")
    private WebElement emailInput;

    @Step("Получить название блока 'Онлайн пополнение без комиссии'")
    public String getPaySectionTitle() {
        return paySectionTitle.getText();
    }

    @Step("Получить список логотипов платёжных систем")
    public List<WebElement> getPaymentLogos() {
        return paymentLogos;
    }

    @Step("Нажать на ссылку 'Подробнее о сервисе'")
    public void clickPaySectionLink() {
        clickPaySectionLink.click();
    }

    @Step("Получить название страницы 'Оплата банковской картой'")
    public String getTitlePageInfoPayCard() {
        return titlePageInfoPayCard.getText();
    }

    @Step("Нажать на поле для ввода номера телефона")
    public void clickPhoneNumberField() {
        connectionPhoneNumber.click();
    }

    @Step("Ввести номер телефона")
    public void sendPhoneNumber(String phoneNumber) {
        connectionPhoneNumber.sendKeys(phoneNumber);
    }

    @Step("Нажать на поле для ввода суммы")
    public void clickSumField() {
        connectionSum.click();
    }

    @Step("Ввести сумму")
    public void sendSum(String sum) {
        connectionSum.sendKeys(sum);
    }

    @Step("Нажать на поле для ввода Email")
    public void clickEmailField() {
        connectionEmail.click();
    }

    @Step("Ввести email")
    public void sendEmail(String email) {
        connectionEmail.sendKeys(email);
    }

    @Step("Нажать на кнопку 'Продолжить'")
    public void clickPayButton() {
        payConnectionButton.click();
    }

    @Step("Получить текст с кнопки для оплаты")
    public String getGPayInfoButtonSum() {
        return paySectionTitleGPay.getText();
    }
}
