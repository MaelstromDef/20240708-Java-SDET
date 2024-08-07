package com.ahuggins.seleniumdemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.Select;

public class FirstTester {
    public static void main(String[] args) {
        // Driver
        WebDriver driver = new EdgeDriver();
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");
        System.out.println(driver.getTitle());

        // Textbox
        WebElement textbox = driver.findElement(By.name("my-text"));
        textbox.sendKeys("This is my input!");
        
        // Checkbox
        WebElement chkBox = driver.findElement(By.name("my-check"));
        chkBox.click();

        // Select
        WebElement selector = driver.findElement(By.name("my-select"));
        System.out.println(selector.getTagName());
        Select mySelect = new Select(selector);
        mySelect.selectByIndex(3);
        mySelect.selectByVisibleText("Two");

        // Multiple elements
        WebElement multiple = driver.findElement(By.className("col-md-4"));
        multiple.click();

        // xpath
        WebElement xpath = driver.findElement(By.xpath("//input[@name='my-radio']"));
        System.out.println(xpath.isSelected());
        xpath.click();
        System.out.println(xpath.isSelected());

        //driver.quit();
    }
}

/**
 * If we wanted to get string and title we would make it equal to driver. and we have a few items we can do here,
 * we can do element we can do get title 
 */
