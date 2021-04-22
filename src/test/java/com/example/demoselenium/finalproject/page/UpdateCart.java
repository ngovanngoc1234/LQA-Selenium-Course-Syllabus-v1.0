package com.example.demoselenium.finalproject.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class UpdateCart extends IsElementPresent{
    public By CART = By.xpath("//*[@id=\"header\"]/div[3]/div/div/div[3]/div/a");
    public By REMOVE = By.xpath("//*[@id=\"header\"]/div[3]/div/div/div[3]/div/div/div/div/dl/dt[2]/span/a");
    public By CHECK_UOT = By.xpath("//*[@id=\"button_order_cart\"]");

    public UpdateCart(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void removeCartItem() throws InterruptedException {
//        Actions action = new Actions(webDriver);
//        action.moveToElement(webDriver.findElement(CART)).click(webDriver.findElement(REMOVE))
//                .build().perform();
//        Thread.sleep(5000);
        webDriver.findElement(CART).click();
    }
}
