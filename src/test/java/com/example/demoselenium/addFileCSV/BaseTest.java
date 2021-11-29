package com.example.demoselenium.addFileCSV;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

public class BaseTest implements Serializable {

    public WebDriver webDriver;

    public void clickMethod(By by) {
        webDriver.findElement(by).click();
    }

    public void sendKeyMethod(By by, String key) {
        webDriver.findElement(by).sendKeys(key);
    }

    public void clearMethod(By by) {
        webDriver.findElement(by).clear();
    }


    public boolean isElementPresent(By by) {
        try {
            webDriver.findElement(by);
            return true;
        } catch (Exception e) {
            return false ;
        }
    }
}
