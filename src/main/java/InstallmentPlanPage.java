import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import io.qameta.allure.Step;
import io.qameta.allure.Description;

public class InstallmentPlanPage {
    public WebDriver driver;

    public InstallmentPlanPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//button[@class='select__header']//span[@class='select__now' and text()='Услуги связи']")
    public WebElement dropDownMenuButton;

    @FindBy(xpath = "//p[@class='select__option' and text()='Рассрочка']")
    public WebElement installmentPlanButton;

    @FindBy(id = "score-instalment")
    private WebElement subscriberNumberInput;

    @FindBy(id = "instalment-sum")
    private WebElement totalSumInput;

    @FindBy(id = "instalment-email")
    private WebElement emailInput;

    @Step("Нажимаем на кнопку открытия выпадающего меню.")
    public void clickDropDownMenuButton() {
        dropDownMenuButton.click();
    }

    @Step("Выбираем опцию 'Рассрочка' из выпадающего меню.")
    public void clickInstallmentPlanSelect() {
        installmentPlanButton.click();
    }

    @Step("Получаем плейсхолдер для поля 'Номер счета'.")
    @Description("Метод возвращает плейсхолдер поля для ввода номера счета.")
    public String getSubscriberNumberPlaceholder() {
        return subscriberNumberInput.getAttribute("placeholder");
    }

    @Step("Получаем плейсхолдер для поля 'Сумма'.")
    @Description("Метод возвращает плейсхолдер поля для ввода суммы рассрочки.")
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
