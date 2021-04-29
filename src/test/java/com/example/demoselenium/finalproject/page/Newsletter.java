package com.example.demoselenium.finalproject.page;

import com.example.demoselenium.finalproject.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Newsletter extends BaseTest {

    public By INPUT_NEWSLETTER = By.xpath("//*[@id=\"newsletter-input\"]");
    public By BUTTON = By.xpath("//*[@id=\"newsletter_block_left\"]/div/form/div/button");

    public Newsletter(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void submitNewsletterEmail(String email) {
        sendKeyMethod(INPUT_NEWSLETTER,email);
        clickMethod(BUTTON);
    }
}
