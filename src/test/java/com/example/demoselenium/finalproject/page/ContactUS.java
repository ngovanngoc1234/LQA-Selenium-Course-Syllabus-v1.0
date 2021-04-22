package com.example.demoselenium.finalproject.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactUS extends IsElementPresent {
    public By BTN_SIGN = By.linkText("Contact us");
    public By IMPORT = By.xpath("//*[@id=\"fileUpload\"]");

    public void ClickContact() throws InterruptedException {
        webDriver.findElement(BTN_SIGN).click();
    }

    public void imports() throws InterruptedException {
        webDriver.findElement(IMPORT).sendKeys("C://Users//LQA//Downloads//ngocnv.jpg");
    }

    public ContactUS(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
}
