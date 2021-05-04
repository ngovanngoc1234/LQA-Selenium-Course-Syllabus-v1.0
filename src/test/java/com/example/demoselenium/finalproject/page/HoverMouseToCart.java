package com.example.demoselenium.finalproject.page;

import com.example.demoselenium.finalproject.object.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HoverMouseToCart extends BaseTest {
    public By HOVER_MOUSE = By.xpath("//*[@id=\"homefeatured\"]/li[1]/div");
    public By ADD_TO_CART = By.xpath("//*[@id=\"homefeatured\"]/li[1]/div/div[2]/div[2]/a[1]");
    public By CONTINUE = By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/span");

    public HoverMouseToCart(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void addCart() throws InterruptedException {
        Actions action = new Actions(webDriver);
        action.moveToElement(webDriver.findElement(HOVER_MOUSE)).click(webDriver.findElement(ADD_TO_CART))
                .build().perform();
        WebDriverWait wait = new WebDriverWait(webDriver,5);
        clickMethod(CONTINUE);
    }

}
