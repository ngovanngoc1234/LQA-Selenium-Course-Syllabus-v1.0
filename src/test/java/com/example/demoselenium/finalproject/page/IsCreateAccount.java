package com.example.demoselenium.finalproject.page;

import com.example.demoselenium.finalproject.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class IsCreateAccount extends BaseTest {

    public By TEXT = By.xpath("//*[@id='create_account_error']/ol/li");
    public By SEND = By.xpath("//*[@id=\"email_create\"]");
    public By TXT_CREATE_ACCOUNT = By.xpath("//*[@id=\"SubmitCreate\"]");
    public By  SUCCESSFULLY = By.xpath("//*[@id=\"noSlide\"]/h1");

    public IsCreateAccount(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void setEmailAddress(String email) {
        sendKeyMethod(SEND,email);
    }

    public void clickCreateAnAccount() {
        clickMethod(TXT_CREATE_ACCOUNT);
    }

    public void createAccount(String email) {
        setEmailAddress(email);
        clickCreateAnAccount();
    }
}
