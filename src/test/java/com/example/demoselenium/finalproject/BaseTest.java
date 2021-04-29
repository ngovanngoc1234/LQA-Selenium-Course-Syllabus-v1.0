package com.example.demoselenium.finalproject;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.example.demoselenium.POM.lib.ReportUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    public static ExtentReports extentReport;
    public ExtentTest extentTest;
    public WebDriver webDriver;
    public WebDriverWait wait;

    @BeforeSuite
    public void beforeSuite() {
        extentReport = new ExtentReports();
        extentReport.attachReporter(ReportUtil.getExtentHtmlReporter());
        extentReport.setSystemInfo("OS", System.getProperty("os.name"));
        extentReport.setSystemInfo("Automation Tool ", "Selenium");
        extentReport.setSystemInfo("Build Automation Tool", "Maven");

    }

    public boolean isElementPresent(By by, WebDriver webDriver) {
        try {
            webDriver.findElement(by);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void clickMethod(By by) {
        webDriver.findElement(by).click();
    }

    public void sendKeyMethod(By by, String key) {
        webDriver.findElement(by).sendKeys(key);
    }

    public void clearMethod(By by) {
        webDriver.findElement(by).clear();
    }

//    @BeforeMethod
    public void beforeMethod() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\LQA\\Desktop\\crack\\chromedriver.exe");
//        System.setProperty("webdriver.edge.driver", "C://Users//LQA//Desktop//msedgedriver.exe");
        webDriver = new ChromeDriver();
//        webDriver = new EdgeDriver();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();
        webDriver.get("http://automationpractice.com/index.php");
//        webDriver.get("https://www.seleniumeasy.com/test/drag-drop-range-sliders-demo.html");
    }

    @AfterMethod
    public void afterMethod() throws Exception {
        webDriver.close();
    }


}
