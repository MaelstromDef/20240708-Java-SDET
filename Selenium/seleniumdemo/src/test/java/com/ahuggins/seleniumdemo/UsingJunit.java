package com.ahuggins.seleniumdemo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class UsingJunit {
    WebDriver driver;

    @BeforeEach
    public void setup(){
        driver = new ChromeDriver();
    }

    @Test
    public void login(){
        driver.get("https://www.selenium.dev/selenium/web/login.html");

        WebElement txtUsername = driver.findElement(By.name("username"));
        WebElement txtPassword = driver.findElement(By.name("password"));
        WebElement btnLogin = driver.findElement(By.id("login-form-submit"));

        txtUsername.sendKeys("MyUsername");
        txtPassword.sendKeys("MyPassword");
        btnLogin.click();
    }

    @AfterEach
    public void teardown(){
        driver.quit();
    }
}
