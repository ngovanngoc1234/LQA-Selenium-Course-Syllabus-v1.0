package com.example.demoselenium.plan;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class MainPageTest {
    WebDriver webDriver;


    @Test
    public void testIframe() {
        webDriver.get("");
    }


    @Test
    public void alertTest() throws InterruptedException {
        webDriver.get("https://www.seleniumeasy.com/test/javascript-alert-box-demo.html");
        webDriver.findElement(By.xpath("//*[@id=\"easycont\"]/div/div[2]/div[1]/div[2]/button")).click();
        Alert alert = webDriver.switchTo().alert();
        System.out.println(alert.getText());
        alert.accept();

        Thread.sleep(3000);

        webDriver.get("https://www.seleniumeasy.com/test/javascript-alert-box-demo.html");
        webDriver.findElement(By.xpath("//*[@id=\"easycont\"]/div/div[2]/div[2]/div[2]/button")).click();
        alert = webDriver.switchTo().alert();
        System.out.println(alert.getText());
        alert.accept();
    }

    @Test
    public void handleByContent() throws InterruptedException {
        webDriver.get("https://www.seleniumeasy.com/test/window-popup-modal-demo.html");
        webDriver.findElement(By.linkText("Follow On Twitter")).click();

        String parentWindowID = webDriver.getWindowHandle();
        System.out.println(parentWindowID);

        Thread.sleep(5000);

        Set<String> handle = webDriver.getWindowHandles();
        for (String window : handle) {
            try {
                webDriver.switchTo().window(window);
                if (webDriver.getPageSource().contains("Selenium Easy")) {
                    System.out.println("title of " + webDriver.getTitle());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Thread.sleep(3000);
        webDriver.close();
        webDriver.switchTo().window(parentWindowID);
        webDriver.findElement(By.linkText("Demo Home")).click();

    }

    @Test
    public void HandlePopupByID() throws InterruptedException {
        webDriver.get("https://www.seleniumeasy.com/test/window-popup-modal-demo.html");
        webDriver.findElement(By.linkText("Follow On Twitter")).click();

        String parentWindowID = webDriver.getWindowHandle();
        System.out.println(parentWindowID);

        Thread.sleep(3000);

        Set<String> handle = webDriver.getWindowHandles();
        String twitter = null;
        for (String s : handle) {
            if (!s.equals(parentWindowID)) {
                twitter = s;
                System.out.println(s);
            }
        }

        try {
            webDriver.switchTo().window(twitter);
            System.out.println("title of popup  " + webDriver.getTitle());
        } catch (Exception e) {
            System.out.println("not able to switch ");
        }

        Thread.sleep(5000);

        webDriver.close();
        webDriver.switchTo().window(parentWindowID);
        webDriver.findElement(By.linkText("Demo Home")).click();

        Thread.sleep(5000);
    }


    @Test
    public void handleByTitle() throws InterruptedException {
        webDriver.get("https://www.seleniumeasy.com/test/window-popup-modal-demo.html");
        webDriver.findElement(By.linkText("Follow On Twitter")).click();

        String parentWindowID = webDriver.getWindowHandle();
        System.out.println(parentWindowID);

        Thread.sleep(5000);

        Set<String> handle = webDriver.getWindowHandles();
        String twitter = "Selenium Easy (@seleniumeasy) / Twitter";

        for (String window : handle) {
            try {
                if (webDriver.switchTo().window(window).getTitle().equals(twitter)) {
                    System.out.println("title of popup  " + webDriver.getTitle());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Thread.sleep(5000);

        webDriver.close();
        webDriver.switchTo().window(parentWindowID);
        webDriver.findElement(By.linkText("Demo Home")).click();

        Thread.sleep(5000);

    }

    @Test
    public void waitTest() {
        webDriver.get("https://www.seleniumeasy.com/test/basic-checkbox-demo.html");
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 10);
        webDriver.get("https://www.seleniumeasy.com/test/basic-checkbox-demo.html");
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        Helper.printCurrentTime();
        try {
            webDriver.findElement(By.xpath("//*[@id=\"isAgeSelected\"]")).click();
        } catch (Exception e) {

        }
        Helper.printCurrentTime();


    }

    @Test
    public void upload() throws InterruptedException {
        webDriver.get("http://www.demo.guru99.com/test/upload/");
        webDriver.findElement(By.id("uploadfile_0")).sendKeys("C://Users//LQA//Desktop//apache-maven-3.8.1");
        webDriver.findElement(By.xpath("//*[@id=\"terms\"]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"submitbutton\"]")).click();
        Thread.sleep(10000);
    }


    @Test
    public void select() throws InterruptedException {
        webDriver.get("https://www.seleniumeasy.com/test/basic-select-dropdown-demo.html");
        Thread.sleep(5000);
        webDriver.findElement(By.xpath("//*[@id=\"select-demo\"]"));
        Select select = new Select(webDriver.findElement(By.xpath("//*[@id=\"select-demo\"]")));
        select.selectByVisibleText("Monday");
    }


    @Test
    public void openSite() {
        System.setProperty("webdriver.chrome.driver", "C://Users//LQA//Desktop//chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get("https://www.seleniumeasy.com/test");

    }


    @Test
    public void findGetTest() {
        String msg = "this is message";
        webDriver.get("https://www.seleniumeasy.com/test/basic-first-form-demo.html");
        webDriver.findElement(By.xpath("//*[@placeholder=\"Please enter your Message\"]")).sendKeys(msg);
        webDriver.findElement(By.xpath("//*[@id=\"get-input\"]/button")).click();
        String outMsg = webDriver.findElement(By.xpath("//*[@id=\"display\"]")).getText();
        if (msg.equals(outMsg)) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }

    }

    @Test
    public void checkBox() throws InterruptedException {
        webDriver.get("https://www.seleniumeasy.com/test/basic-checkbox-demo.html");
        webDriver.findElement(By.xpath("//*[@id=\"isAgeSelected\"]")).click();
        WebElement element = webDriver.findElement(By.xpath("//*[@id=\"isAgeSelected\"]"));
        if (element.isSelected()) element.click();
        Thread.sleep(5000);

    }

    @BeforeMethod
    public void setWebDriver() {
        System.setProperty("webdriver.chrome.driver", "C://Users//LQA//Desktop//chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();

    }

    @AfterMethod
    public void afterMethod() {
        webDriver.close();
    }
}
