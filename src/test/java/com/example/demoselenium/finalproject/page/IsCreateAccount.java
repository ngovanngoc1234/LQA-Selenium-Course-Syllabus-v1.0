package com.example.demoselenium.finalproject.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class IsCreateAccount extends IsElementPresent {

    public By TEXT = By.xpath("//*[@id='create_account_error']/ol/li");
    public By SEND = By.xpath("//*[@id=\"email_create\"]");
    public By TXT_CREATE_ACCOUNT = By.xpath("//*[@id=\"SubmitCreate\"]");
    public By  SUCCESSFULLY = By.xpath("//*[@id=\"noSlide\"]/h1");

    public IsCreateAccount(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void setEmailAddress(String email) {
        webDriver.findElement(SEND).sendKeys(email);
    }

    public void clickCreateAnAccount() {
        webDriver.findElement(TXT_CREATE_ACCOUNT).click();
    }

    public void createAccount(String email) {
        setEmailAddress(email);
        clickCreateAnAccount();
    }
}
