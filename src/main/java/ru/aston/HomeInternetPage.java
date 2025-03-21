package ru.aston;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomeInternetPage {
    public WebDriver driver;

    public HomeInternetPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    /// Drop down menu open
    @FindBy(xpath = "//button[@class='select__header']//span[@class='select__now' and text()='Услуги связи']")
    public WebElement dropDownMenuButton;

    /// Drop down menu == Home Internet
    @FindBy(xpath = "//p[@class='select__option' and text()='Домашний интернет']")
    public WebElement dropDownMenuHomeInternetSelect;

    /// Поле "Номер абонента"
    @FindBy(id = "internet-phone")
    private WebElement subscriberNumberInput;

    /// Поле "Сумма"
    @FindBy(id = "internet-sum")
    private WebElement totalSumInput;

    /// Поле "E-mail для отправки чека"
    @FindBy(id = "internet-email")
    private WebElement emailInput;

    /// Метод открытия Drop down menu
    public void clickDropDownMenuButton() {
        dropDownMenuButton.click();
    }

    /// Метод выбора клик меню Домашний интернет
    public void clickDropDownMenuHomeInternetSelect() {
        dropDownMenuHomeInternetSelect.click();
    }






    /// Метод для получения плейсхолдера "Номер абонента"
    public String getSubscriberNumberPlaceholder() {
        return subscriberNumberInput.getAttribute("placeholder");
    }
    /// Метод для получения плейсхолдера "Сумма"
    public String getTotalSumPlaceholder() {
        return totalSumInput.getAttribute("placeholder");
    }
    /// Метод для получения плейсхолдера "E-mail для отправки чека"
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
