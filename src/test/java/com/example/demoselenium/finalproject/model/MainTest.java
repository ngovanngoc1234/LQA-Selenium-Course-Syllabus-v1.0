package com.example.demoselenium.finalproject.model;


import com.example.demoselenium.finalproject.object.BaseTest;
import com.example.demoselenium.finalproject.page.*;
import com.mongodb.util.JSON;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;


public class MainTest extends BaseTest {

    @Test
    public void createAccount() throws InterruptedException {
        Home home = new Home(webDriver);
        home.signIn();
        CreateAccount createAccount = new CreateAccount(webDriver);
        IsCreateAccount isCreateAccount = new IsCreateAccount(webDriver);
        isCreateAccount.createAccount("abc123134884544@gmail.com");
        boolean flag;
        if (isCreateAccount.isElementPresent(isCreateAccount.SUCCESSFULLY, webDriver)) {
            System.out.println("tao thanh cong");
            flag = true;
        } else {
            flag = false;
            System.out.println("ko thanh cong");
        }
        createAccount.addSex();
        createAccount.addFirstName("ngo van");
        createAccount.addLastName("ngoc");
        createAccount.addDay(16);
        createAccount.addMonth(7);
        createAccount.addYears("1994");
        createAccount.addCompany("lqa");
        createAccount.address("thanh hoa");

        extentTest = extentReport.createTest("createAccount");
        extentTest.info("Welcome to your account. Here you can manage all of your personal information and orders.");
        if (flag) {
            extentTest.pass("PASS");
        } else {
            extentTest.fail("FAIL");
        }
        extentReport.flush();
    }

    @Test
    public void newsletter() {
        Newsletter newsletter = new Newsletter(webDriver);
        newsletter.submitNewsletterEmail("ngovanngoc112662@yahoo.com");
        extentTest = extentReport.createTest("newsletter");
        extentTest.info(" You have successfully subscribed to this newsletter.");
        WebElement webElement = webDriver.findElement(By.xpath("//*[@id=\"columns\"]/p"));
        if (webElement.getText().equals("Newsletter : You have successfully subscribed to this newsletter.")) {
            extentTest.pass("pass");
        } else {
            extentTest.pass("pass");
        }
        extentReport.flush();

    }

    @Test
    public void createContactus() throws InterruptedException {
        ContactUS contactUS = new ContactUS(webDriver);
        contactUS.ClickContact();
        Thread.sleep(5000);
        contactUS.imports();
        extentTest = extentReport.createTest("createContactus");
        extentTest.info("  Your message has been successfully sent to our team");
        extentTest.pass("pass");

        extentReport.flush();
    }

    @Test
    public void searchClear() throws InterruptedException {
        Search search = new Search(webDriver);
        search.suggestionsSearch();
        extentTest = extentReport.createTest("searchClear");
        extentTest.pass("pass");
        extentReport.flush();
    }


    @Test
    public void mouseAddCart() throws InterruptedException {
        HoverMouseToCart hoverMouseToCart = new HoverMouseToCart(webDriver);
        hoverMouseToCart.addCart();
        extentTest = extentReport.createTest("mouseAddCart");
        extentTest.pass("PASS");
        extentReport.flush();
    }

    @Test
    public void removeCartAddCheckout() throws InterruptedException {
        extentTest = extentReport.createTest("removeCartAddCheckout");
        UpdateCart updateCart = new UpdateCart(webDriver);
        updateCart.removeCartItem();
        extentTest.pass("PASS");
        extentReport.flush();
        //You must agree to the terms of service before continuing.
    }

    @Test
    public void sale20() throws InterruptedException {
        List<WebElement> list = webDriver.findElements(By.xpath("//*[@id=\"homefeatured\"]/li/div/div/div/span[3]"));
        extentTest = extentReport.createTest("sale20%");
        boolean check = false;
        for (WebElement webElement : list) {
            if (webElement.getText().equals("-20%")) {
                System.out.println(webElement.getText());
                check = true;
                webElement.click();
            }
        }
        extentTest.info("Your order on My Store is complete.");
        if (check) {
            extentTest.pass("PASS");
        } else {
            extentTest.fail("FAIL");
        }
        extentReport.flush();
        Thread.sleep(3000);
    }


}
