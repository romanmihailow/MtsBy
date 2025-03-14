package ru.aston;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CommunicationServicePage {
    public WebDriver driver;

    public CommunicationServicePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }





    /// Проверка pop-up она == "Обработка файлов cookie"
    @FindBy(xpath = "/html/body/div[6]/main/div/div[2]/div/div[1]/div[1]/h3")
    public WebElement cookiePageTitle;

    /// Кнопка принять (всплывающее окно при входе на страницу) - click
    @FindBy(xpath = "//*[@id=\"cookie-agree\"]")
    private WebElement cookieAgreeButton;

    /// Название блока онлайн пополнение без комиссии == "Онлайн пополнение без комиссии"
    @FindBy(xpath = "//*[@id=\"pay-section\"]/div/div/div[2]/section/div/h2/text()[1]")
    private WebElement paySectionTitle;

    /// Visa ==
    @FindBy(xpath = "//*[@id=\"pay-section\"]/div/div/div[2]/section/div/div[2]/ul/li[1]/img")
    private WebElement paySectionImageVisa;

    /// VerifiedByVisa ==
    @FindBy(xpath = "//*[@id=\"pay-section\"]/div/div/div[2]/section/div/div[2]/ul/li[2]/img")
    private WebElement paySectionImageVerifiedByVisa;

    /// MasterCard ==
    @FindBy(xpath = "//*[@id=\"pay-section\"]/div/div/div[2]/section/div/div[2]/ul/li[3]/img")
    private WebElement paySectionImageMasterCard;

    /// MasterCardSecureCode ==
    @FindBy(xpath = "//*[@id=\"pay-section\"]/div/div/div[2]/section/div/div[2]/ul/li[4]/img")
    private WebElement paySectionImageMasterCardSecureCode;

    /// BelCart ==
    @FindBy(xpath = "//*[@id=\"pay-section\"]/div/div/div[2]/section/div/div[2]/ul/li[5]/img")
    private WebElement paySectionImageBelCart;

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

    /// Проверка перехода на форму оплаты
    @FindBy(xpath = "/html/body/app-root/div/div/div/app-payment-container/section/div/div/div[2]/span")
    private WebElement paySectionTitleGPay;







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
    public WebElement getPaySectionImageVisa() {
        return paySectionImageVisa;
    }
    public WebElement getPaySectionImageVerifiedByVisa() {
        return paySectionImageVerifiedByVisa;
    }
    public WebElement getPaySectionImageMasterCard() {
        return paySectionImageMasterCard;
    }
    public WebElement getPaySectionImageMasterCardSecureCode() {
        return paySectionImageMasterCardSecureCode;
    }
    public WebElement getPaySectionImageBelCart() {
        return paySectionImageBelCart;
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









}
