package com.example.demoselenium.finalproject.page;


import com.example.demoselenium.finalproject.object.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactUS extends BaseTest {
    public By BTN_SIGN = By.linkText("Contact us");
    public By IMPORT = By.xpath("//*[@id=\"fileUpload\"]");

    public void ClickContact() throws InterruptedException {
        clickMethod(BTN_SIGN);
    }

    public void imports() throws InterruptedException {
        sendKeyMethod(IMPORT,"C://Users//LQA//Downloads//ngocnv.jpg");
    }

    public ContactUS(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
}
