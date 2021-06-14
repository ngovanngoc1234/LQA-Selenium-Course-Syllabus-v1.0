package com.example.demoselenium.addFileCSV;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class TestScript extends BaseTest {

    @Test
    public void scriptHue() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();
        webDriver.get("https://saas.crowdworks.kr/cw/saas/login.do");
//        login account
        sendKeyMethod(By.xpath("(//input)[1]"), "lqa032.lqa@gmail.com");
        sendKeyMethod(By.xpath("(//input)[2]"), "lqa123456");
        clickMethod(By.xpath("//button"));
//        click inspection
        clickMethod(By.xpath("//*[@id=\"link_check\"]"));
        clickMethod(By.xpath("//*[@class=\"link_inspectlist link_checking_page\"]"));

        String winHandleBefore = webDriver.getWindowHandle();
        // get to popup twitter handle
        Set<String> handles = webDriver.getWindowHandles();
        String PopupHandle = null;
        for (String h : handles) {
            if (!h.equals(winHandleBefore)) {
                PopupHandle = h;
            }
        }
// Switch back to original browser (first window)
        webDriver.switchTo().window(PopupHandle);
        By buttonSubmit = By.xpath("//*[@class=\"css-1r6teak\"]");
        while (true) {
            List<WebElement> statusList = webDriver.findElements(By.xpath("(//*[@class=\"bottom\"])[2]/dl/dd"));
            if (statusList.size() >= 3) {
                clickMethod(By.xpath("//*[@class=\"css-1jj1mxj\"]"));
                Thread.sleep(5000);
                if (isElementPresent(buttonSubmit)) {
                    clickMethod(By.xpath("//*[@class=\"css-1r6teak\"]"));
                }
            } else {
                return;
            }
        }
    }
}
