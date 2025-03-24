import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import io.qameta.allure.Step;
import io.qameta.allure.Description;

public class HomeInternetPage {
    public WebDriver driver;

    public HomeInternetPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//button[@class='select__header']//span[@class='select__now' and text()='Услуги связи']")
    public WebElement dropDownMenuButton;

    @FindBy(xpath = "//p[@class='select__option' and text()='Домашний интернет']")
    public WebElement dropDownMenuHomeInternetSelect;

    @FindBy(id = "internet-phone")
    private WebElement subscriberNumberInput;

    @FindBy(id = "internet-sum")
    private WebElement totalSumInput;

    @FindBy(id = "internet-email")
    private WebElement emailInput;

    @Step("Нажимаем на кнопку открытия выпадающего меню.")
    public void clickDropDownMenuButton() {
        dropDownMenuButton.click();
    }

    @Step("Выбираем опцию 'Домашний интернет' из выпадающего меню.")
    public void clickDropDownMenuHomeInternetSelect() {
        dropDownMenuHomeInternetSelect.click();
    }

    @Step("Получаем плейсхолдер для поля 'Номер абонента'.")
    @Description("Метод возвращает плейсхолдер поля для ввода номера абонента.")
    public String getSubscriberNumberPlaceholder() {
        return subscriberNumberInput.getAttribute("placeholder");
    }

    @Step("Получаем плейсхолдер для поля 'Сумма'.")
    @Description("Метод возвращает плейсхолдер поля для ввода суммы.")
    public String getTotalSumPlaceholder() {
        return totalSumInput.getAttribute("placeholder");
    }

    @Step("Получаем плейсхолдер для поля 'E-mail для отправки чека'.")
    @Description("Метод возвращает плейсхолдер поля для ввода e-mail.")
    public String getEmailPlaceholder() {
        return emailInput.getAttribute("placeholder");
    }

    public WebElement getSubscriberNumberInput() {
        return subscriberNumberInput;
    }

    public WebElement getTotalSumInput() {
        return totalSumInput;
    }

    public WebElement getEmailInput() {
        return emailInput;
    }
}
