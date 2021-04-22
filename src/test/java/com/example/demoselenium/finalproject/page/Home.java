package com.example.demoselenium.finalproject.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Home extends IsElementPresent{
    public By BTN_SIGN = By.linkText("Sign in");

    public void signIn() {
        webDriver.findElement(BTN_SIGN).click();
    }
    public Home(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
}
