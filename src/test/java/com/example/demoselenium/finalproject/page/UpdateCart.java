package com.example.demoselenium.finalproject.page;

import com.example.demoselenium.finalproject.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class UpdateCart extends BaseTest {
    public By CART = By.xpath("//*[@id=\"header\"]/div[3]/div/div/div[3]/div/a");
    public By REMOVE = By.xpath("//*[@id=\"header\"]/div[3]/div/div/div[3]/div/div/div/div/dl/dt[2]/span/a");
    public By CHECK_UOT = By.xpath("//*[@id=\"button_order_cart\"]");

    public UpdateCart(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void removeCartItem() throws InterruptedException {
        clickMethod(CART);
    }
}
