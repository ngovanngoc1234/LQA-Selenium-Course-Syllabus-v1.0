package com.example.demoselenium.POM.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Login extends Base {

    public By TXT_EMAIL = By.id("email");
    public By TXT_PASSWORD = By.xpath("//*[@id=\"passwd\"]");
    public By TXT_LOGIN = By.xpath("//*[@id=\"SubmitLogin\"]");

    public Login(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public MyAccount login() {
        webDriver.findElement(TXT_EMAIL).sendKeys("ngovanngoc2014@gmail.com");
        webDriver.findElement(TXT_PASSWORD).sendKeys("Anhngoc123");
        webDriver.findElement(TXT_LOGIN).click();
        return new MyAccount(webDriver);
    }
}
