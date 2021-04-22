package com.example.demoselenium.finalproject.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Newsletter extends IsElementPresent{

    public By INPUT_NEWSLETTER = By.xpath("//*[@id=\"newsletter-input\"]");
    public By BUTTON = By.xpath("//*[@id=\"newsletter_block_left\"]/div/form/div/button");

    public Newsletter(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void submitNewsletterEmail(String email) {
        webDriver.findElement(INPUT_NEWSLETTER).sendKeys(email);
        webDriver.findElement(BUTTON).click();
    }
}
