package com.ahuggins.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {
    private WebDriver driver;

    @FindBy(id="uid")
    private WebElement inUsername;

    @FindBy(id="passw")
    private WebElement inPassword;

    @FindBy(id="btnSubmit")
    private WebElement btnSubmit;

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }
}
