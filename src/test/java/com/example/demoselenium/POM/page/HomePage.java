package com.example.demoselenium.POM.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends Base {
    public By BTN_SIGN = By.linkText("Sign in");
    public By LINK_BEST = By.xpath("//*[@id=\"home-page-tabs\"]/li[2]/a");

    public HomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public Login clickSign() {
        webDriver.findElement(BTN_SIGN).click();
        return new Login(webDriver);
    }
}
