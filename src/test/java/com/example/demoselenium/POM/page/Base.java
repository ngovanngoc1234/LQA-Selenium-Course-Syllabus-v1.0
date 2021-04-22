package com.example.demoselenium.POM.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Base {
    public WebDriver webDriver;

    public boolean isElementPresent(By by) {
        try {
            webDriver.findElement(by);
            return true;
        } catch (Exception e) {
            return false ;
        }
    }
}
