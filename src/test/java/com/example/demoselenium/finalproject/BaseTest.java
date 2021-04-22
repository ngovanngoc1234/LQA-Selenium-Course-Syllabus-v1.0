package com.example.demoselenium.finalproject;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.example.demoselenium.POM.lib.ReportUtil;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;

public class BaseTest {
    protected WebDriver driver;
    public static ExtentReports extentReport;
    public ExtentTest extentTest;

    @BeforeSuite
    //@Parameters({"paraName"})
    public void beforeSuite(
            //String paraName
    ) {
//		System.out.println(paraName);
        extentReport = new ExtentReports();
        extentReport.attachReporter(ReportUtil.getExtentHtmlReporter());
        extentReport.setSystemInfo("OS", System.getProperty("os.name"));
        extentReport.setSystemInfo("Automation Tool ", "Selenium");
        extentReport.setSystemInfo("Build Automation Tool", "Maven");

    }
}
