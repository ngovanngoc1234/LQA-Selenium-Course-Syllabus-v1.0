package com.example.demoselenium.POM.Test;

import com.example.demoselenium.POM.page.HomePage;
import com.example.demoselenium.POM.page.Login;
import com.example.demoselenium.POM.page.MyAccount;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestPOM {

    WebDriver webDriver;

    @Test
    public void loginTes() {
        HomePage homePage = new HomePage(webDriver);
        Login login = homePage.clickSign();
        MyAccount myAccount = login.login();
        homePage = myAccount.homePage();
        assert (homePage.isElementPresent(homePage.LINK_BEST));
        System.out.println(homePage.isElementPresent(homePage.LINK_BEST));
    }

    @BeforeMethod
    public void setWebDriver() {
        System.setProperty("webdriver.chrome.driver", "C://Users//LQA//Desktop//chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get("http://automationpractice.com/index.php");

    }

    @AfterMethod
    public void afterMethod() {

    }
}
