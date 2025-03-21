package ru.aston;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InstallmentPlanPage {
    public WebDriver driver;

    public InstallmentPlanPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    /// Drop down menu open
    @FindBy(xpath = "//button[@class='select__header']//span[@class='select__now' and text()='Услуги связи']")
    public WebElement dropDownMenuButton;

    /// Drop down == "Рассрочка"
    @FindBy(xpath = "//p[@class='select__option' and text()='Рассрочка']")
    public WebElement installmentPlanButton;

    /// Поле "Номер счета"
    @FindBy(id = "score-instalment")
    private WebElement subscriberNumberInput;

    /// Поле "Сумма"
    @FindBy(id = "instalment-sum")
    private WebElement totalSumInput;

    /// Поле "E-mail для отправки чека"
    @FindBy(id = "instalment-email")
    private WebElement emailInput;

    /// Метод открытия Drop down menu
    public void clickDropDownMenuButton() {
        dropDownMenuButton.click();
    }
    public void clickInstallmentPlanSelect() {
        installmentPlanButton.click();
    }


    public String getSubscriberNumberPlaceholder() {
        return subscriberNumberInput.getAttribute("placeholder");
    }
    public String getTotalSumPlaceholder() {
        return totalSumInput.getAttribute("placeholder");
    }
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
