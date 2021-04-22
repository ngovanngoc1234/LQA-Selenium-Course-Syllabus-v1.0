package com.example.demoselenium.POM.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyAccount extends Base {

    public By TXT_LINK_HOME = By.xpath("//*[@id=\"center_column\"]/ul/li/a");

    public HomePage homePage() {
        webDriver.findElement(TXT_LINK_HOME).click();
        return new HomePage(webDriver);
    }

    public MyAccount(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
}
