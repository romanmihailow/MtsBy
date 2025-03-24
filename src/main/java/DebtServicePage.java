import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DebtServicePage {
    public WebDriver driver;

    public DebtServicePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//button[@class='select__header']//span[@class='select__now' and text()='Услуги связи']")
    public WebElement dropDownMenuButton;

    @FindBy(xpath = "//p[@class='select__option' and text()='Задолженность']")
    public WebElement dropDownMenuDebtServiceSelect;

    @FindBy(id = "score-arrears")
    private WebElement subscriberNumberInput;

    @FindBy(id = "arrears-sum")
    private WebElement totalSumInput;

    @FindBy(id = "arrears-email")
    private WebElement emailInputDS;

    @Step("Открытие выпадающего меню")
    public void clickDropDownMenuButton() {
        dropDownMenuButton.click();
    }

    @Step("Выбор опции 'Задолженность' в выпадающем меню")
    public void clickDropDownMenuDebtServiceSelect() {
        dropDownMenuDebtServiceSelect.click();
    }

    @Step("Получение плейсхолдера поля 'Номер счета'")
    public String getSubscriberNumberPlaceholder() {
        return subscriberNumberInput.getAttribute("placeholder");
    }

    @Step("Получение плейсхолдера поля 'Сумма'")
    public String getTotalSumPlaceholder() {
        return totalSumInput.getAttribute("placeholder");
    }

    @Step("Получение плейсхолдера поля 'E-mail для отправки чека'")
    public String getEmailPlaceholder() {
        return emailInputDS.getAttribute("placeholder");
    }

    public WebElement getSubscriberNumberInput() {
        return subscriberNumberInput;
    }

    public WebElement getTotalSumInput() {
        return totalSumInput;
    }

    public WebElement getEmailInput() {
        return emailInputDS;
    }

    @Step("Клик по полю Email")
    public void clickEmailField() {
        emailInputDS.click();
    }
}
