package com.example.demoselenium.addFileCSV;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public class BaseTest {

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

    @BeforeMethod
    public void beforeMethod() {
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();
        webDriver.get("http://automationpractice.com/index.php");
    }

    //    @AfterMethod
    public void afterMethod() throws Exception {
        webDriver.close();
    }


}
