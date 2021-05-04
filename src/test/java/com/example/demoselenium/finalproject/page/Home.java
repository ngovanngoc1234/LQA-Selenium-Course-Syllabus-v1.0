package com.example.demoselenium.finalproject.page;


import com.example.demoselenium.finalproject.object.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Home extends BaseTest {
    public By BTN_SIGN = By.linkText("Sign in");

    public void signIn() {
        webDriver.findElement(BTN_SIGN).click();
    }
    public Home(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
}
