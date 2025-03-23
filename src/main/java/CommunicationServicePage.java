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





    ///update Проверка pop-up она == "Обработка файлов cookie"
    @FindBy(xpath = "//div[contains(@class, 'cookie')]/h3")
    public WebElement cookiePageTitle;
//    @FindBy(xpath = "/html/body/div[6]/main/div/div[2]/div/div[1]/div[1]/h3")
//    public WebElement cookiePageTitle;

    ///update Кнопка принять (всплывающее окно при входе на страницу) - click
    @FindBy(xpath = "//button[contains(@id, 'cookie-agree')]")
    private WebElement cookieAgreeButton;
//    @FindBy(xpath = "//*[@id=\"cookie-agree\"]")
//    private WebElement cookieAgreeButton;

    ///update Название блока онлайн пополнение без комиссии == "Онлайн пополнение без комиссии"
    @FindBy(xpath = "//*[@id='pay-section']//h2")
    private WebElement paySectionTitle;
//    @FindBy(xpath = "//*[@id=\"pay-section\"]/div/div/div[2]/section/div/h2/text()[1]")
//    private WebElement paySectionTitle;

    /// Логотипы платежных систем (Visa, VerifiedByVisa, MasterCard и т.д.)
    @FindBy(xpath = "//ul/li/img[contains(@alt, 'Visa') or contains(@alt, 'MasterCard') or contains(@alt, 'BelCart')]")
    private List<WebElement> paymentLogos;

    /// Подробнее о сервисе - click
    @FindBy(xpath = "//*[@id=\"pay-section\"]/div/div/div[2]/section/div/a")
    private WebElement clickPaySectionLink;

    /// Проверка перехода == "Оплата банковской картой"
    @FindBy(xpath = "/html/body/div[6]/main/div/div[4]/h3[1]")
    private WebElement titlePageInfoPayCard;


    /// Поле номер телефона - ввести номер телефона: 297777777
    @FindBy(xpath = "//*[@id=\"connection-phone\"]")
    private WebElement connectionPhoneNumber;
    /// Поле сумма - ввести сумму: 100
    @FindBy(xpath = "//*[@id=\"connection-sum\"]")
    private WebElement connectionSum;
    /// Поле email - ввести email: rmw@gmail.com
    @FindBy(xpath = "//*[@id=\"connection-email\"]")
    private WebElement connectionEmail;
    /// Кнопка продолжить - click
    @FindBy(xpath = "//*[@id=\"pay-connection\"]/button")
    private WebElement payConnectionButton;



    /// Проверка перехода на форму оплаты gPay
    @FindBy(xpath = "/html/body/app-root/div/div/div/app-payment-container/section/div/div/div[2]/span")
    private WebElement paySectionTitleGPay;
    /// @FindBy(xpath = "//span[contains(text(), 'Оплата: Услуги связи')]\n")






    /// Проверка плейсхолдера "Номер телефона"
    @FindBy(xpath = "//*[@placeholder='Номер телефона']")
    private WebElement phoneInput;

    /// Проверка плейсхолдера "Сумма"
    @FindBy(xpath = "//*[@placeholder='Сумма']")
    private WebElement totalSumInput;

    /// Проверка плейсхолдера "E-mail для отправки"
    @FindBy(xpath = "//*[@placeholder='E-mail для отправки чека']")
    private WebElement emailInput;







    /// Метод запроса title "Pop-Up окна cookie"
    public String shouldTitleUpPopUpWindow() {
        return cookiePageTitle.getText();
    }
    /// Метод клик по кнопке ПРИНЯТЬ в "Pop-up окне cookie"
    public void clickCookieAgreeButton() {
        cookieAgreeButton.click();
    }
    public String getPaySectionTitle() {
        return paySectionTitle.getText();
    }

    /// Метод получения списка логотипов
    public List<WebElement> getPaymentLogos() {
        return paymentLogos;
    }

    /// Метод для клика по ссылке подробнее об оплате
    public void clickPaySectionLink() {
        clickPaySectionLink.click();
    }
    /// Метод для получения title на странице информации об оплате
    public String getTitlePageInfoPayCard() {
        return titlePageInfoPayCard.getText();
    }
    public void clickPhoneNumberField() {
        connectionPhoneNumber.click();
    }
    public void sendPhoneNumber(String phoneNumber) {
        connectionPhoneNumber.sendKeys(ConfProperties.getProperty("phonenumber"));
    }
    public void clickSumField() {
        connectionSum.click();
    }
    public void sendSum(String sum) {
        connectionSum.sendKeys(ConfProperties.getProperty("sum"));
    }
    public void clickEmailField() {
        connectionEmail.click();
    }
    public void sendEmail(String email) {
        connectionEmail.sendKeys(ConfProperties.getProperty("email"));
    }
    public void clickPayButton() {
        payConnectionButton.click();
    }
    public String getPaySectionTitleGPay() {
        return paySectionTitleGPay.getText();
    }

    /// Проверить корректность отображения суммы (в том числе на кнопке),
    /// номера телефона, а также надписей в незаполненных полях для ввода реквизитов карты,
    /// наличие иконок платёжных систем.

    /// Отображение суммы
    @FindBy(xpath = "//span[contains(text(), 'BYN')]\n")
    private WebElement gPayInfoTextSum;
    /// Отображение суммы на кнопке
    @FindBy(xpath = "//button[contains(text(), 'Оплатить') and contains(text(), 'BYN')]\n")
    private WebElement gPayInfoButtonSum;



    /// Отображение номера телефона
    @FindBy(xpath = "//span[contains(text(), 'Оплата: Услуги связи')]\n")
    private WebElement gPayInfoTextPhoneNumber;

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
    /// Name cart holder
    @FindBy(xpath = "//label[text() = 'Имя держателя (как на карте)']")
    private WebElement gPayInfoNameHolderCart;

    /// Logo payment
    @FindBy(xpath = "//img[@src='assets/images/payment-icons/card-types/visa-system.svg']")
    private WebElement gPayLogoVisa;
    @FindBy(xpath = "//img[@src='assets/images/payment-icons/card-types/mastercard-system.svg']")
    private WebElement gPayLogoMasterCart;
    @FindBy(xpath = "//img[@src='assets/images/payment-icons/card-types/belkart-system.svg']")
    private WebElement gPayLogoBelCart;
    @FindBy(xpath = "//img[@src='assets/images/payment-icons/card-types/maestro-system.svg']")
    private WebElement gPayLogoMIRCart;




    public String getGPayInfoButtonSum() {
        return gPayInfoTextSum.getText();
    }
    public WebElement getGPayInfoTextSum() {
        return gPayInfoTextSum;
    }










}
