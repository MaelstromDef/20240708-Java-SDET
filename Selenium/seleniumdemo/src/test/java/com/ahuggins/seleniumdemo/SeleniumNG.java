package com.ahuggins.seleniumdemo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SeleniumNG {
    WebDriver driver;
    static final String baseUrl = "https://www.selenium.dev/selenium/web/login.html";

    WebElement inUsername;
    WebElement inPassword;
    WebElement btnSubmit;

    @BeforeTest
    public void launchBrowser(){
        driver = new EdgeDriver();
        driver.get(baseUrl);
    }

    @BeforeMethod
    public void getWebElements(){
        System.out.println("BeforeMethod");
        inUsername = driver.findElement(By.id("username-field"));
        inPassword = driver.findElement(By.id("password-field"));
        inUsername.clear();
        inPassword.clear();
        btnSubmit = driver.findElement(By.id("login-form-submit"));
    }

    @Test(priority = 1)
    public void validLogin(){
        System.out.println("validLogin");
        String validUsername = "username";
        String validPassword = "password";

        inUsername.sendKeys(validUsername);
        inPassword.sendKeys(validPassword);
        btnSubmit.click();
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());

        Alert alert = driver.switchTo().alert();
        assertEquals(alert.getText(), "You have successfully logged in.");

        alert.dismiss();
    }

    @Test(priority = 2)
    public void invalidLogin(){
        System.out.println("invalidLogin");
        String validUsername = "";
        String validPassword = "password";

        inUsername.sendKeys(validUsername);
        inPassword.sendKeys(validPassword);
        btnSubmit.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());

        Alert alert = driver.switchTo().alert();
        assertNotEquals(alert.getText(), "You have successfully logged in.");

        alert.dismiss();
    }

    @AfterMethod
    public void cleanup(){
        System.out.println("afterMethod");
    }

    @AfterTest
    public void closeBrowser(){
        driver.quit();
    }

}
