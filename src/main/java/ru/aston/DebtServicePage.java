package ru.aston;

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

    /// Drop down menu open
    @FindBy(xpath = "//button[@class='select__header']//span[@class='select__now' and text()='Услуги связи']")
    public WebElement dropDownMenuButton;

    /// Drop down menu == Home Internet
    @FindBy(xpath = "//p[@class='select__option' and text()='Задолженность']")
    public WebElement dropDownMenuHomeInternetSelect;


    /// Поле "Номер счета"
    @FindBy(id = "score-arrears")
    private WebElement subscriberNumberInput;

    /// Поле "Сумма"
    @FindBy(id = "arrears-sum")
    private WebElement totalSumInput;

    /// Поле "E-mail для отправки чека"
    @FindBy(id = "arrears-email")
    private WebElement emailInput;

    /// Метод открытия Drop down menu
    public void clickDropDownMenuButton() {
        dropDownMenuButton.click();
    }

    public void clickDropDownMenuDebtServiceSelect() {
        dropDownMenuHomeInternetSelect.click();
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
