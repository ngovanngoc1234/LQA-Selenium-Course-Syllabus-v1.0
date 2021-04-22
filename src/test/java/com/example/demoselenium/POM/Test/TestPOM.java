package com.example.demoselenium.POM.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.example.demoselenium.POM.page.HomePage;
import com.example.demoselenium.POM.page.Login;
import com.example.demoselenium.POM.page.MyAccount;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.example.demoselenium.POM.lib.ReportUtil;

public class TestPOM {

	WebDriver webDriver;
	public static ExtentReports extentReport;
	public ExtentTest extentTest;

	@BeforeMethod
	public void setWebDriver() {
		System.setProperty("webdriver.chrome.driver", "C://Users//LQA//Desktop//chromedriver.exe");
		webDriver = new ChromeDriver();
		webDriver.manage().window().maximize();
		webDriver.get("http://automationpractice.com/index.php");

		// report
		extentReport = new ExtentReports();
		extentReport.attachReporter(ReportUtil.getExtentHtmlReporter());
		extentReport.setSystemInfo("OS", System.getProperty("os.name"));
		extentReport.setSystemInfo("Automation Tool ", "Selenium");
		extentReport.setSystemInfo("Build Automation Tool", "Maven");

	}

	@Test
	public void loginTes() {
		extentTest = extentReport.createTest("loginTes");
		HomePage homePage = new HomePage(webDriver);
		Login login = homePage.clickSign();
		MyAccount myAccount = login.login();
		homePage = myAccount.homePage();
		assert (homePage.isElementPresent(homePage.LINK_BEST));
		System.out.println(homePage.isElementPresent(homePage.LINK_BEST));
		String username = "xxx";
		String password = "xxx";
		
		extentTest.info("username: " + username);
		extentTest.info("password: " + password);
		if (password.contains(username)) {
			extentTest.pass("PASS");
		} else {
			extentTest.fail("FAIL");
		}
		extentReport.flush();
	}

	@AfterMethod
	public void afterMethod() {

	}
}
