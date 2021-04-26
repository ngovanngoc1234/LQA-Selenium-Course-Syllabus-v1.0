package com.example.demoselenium.finalproject;


import com.example.demoselenium.finalproject.page.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;


public class MainTest extends BaseTest {

    WebDriver webDriver;


    @Test
    public void createAccount() throws InterruptedException {
        Home home = new Home(webDriver);
        home.signIn();
        CreateAccount createAccount = new CreateAccount(webDriver);
        IsCreateAccount isCreateAccount = new IsCreateAccount(webDriver);
        isCreateAccount.createAccount("abc1231234@gmail.com");
        Thread.sleep(5000);
        boolean flag;
        if (isCreateAccount.isElementPresent(isCreateAccount.SUCCESSFULLY)) {
            System.out.println("tao thanh cong");
            flag = true;
        } else {
            flag = false;
            System.out.println("ko thanh cong");
        }
        createAccount.addSex();
        createAccount.addFirstName("ngo van");
        createAccount.addLastName("ngoc");
        createAccount.addDay(16);
        createAccount.addMonth(7);
        createAccount.addYears("1994");
        createAccount.addCompany("lqa");
        createAccount.address("thanh hoa");
        Thread.sleep(5000);
        extentTest = extentReport.createTest("createAccount");
        extentTest.info("Welcome to your account. Here you can manage all of your personal information and orders.");
        if (flag) {
            extentTest.pass("PASS");
        } else {
            extentTest.fail("FAIL");
        }
        extentReport.flush();
    }

    @Test
    public void newsletter() {
        Newsletter newsletter = new Newsletter(webDriver);
        newsletter.submitNewsletterEmail("ngovanngoc20@yahoo.com");
        extentTest = extentReport.createTest("newsletter");
        extentTest.info(" You have successfully subscribed to this newsletter.");
        WebElement webElement = webDriver.findElement(By.xpath("//*[@id=\"columns\"]/p"));
        if (webElement.getText().equals("Newsletter : You have successfully subscribed to this newsletter.")) {
            extentTest.pass("pass");
        } else {
            extentTest.pass("pass");
        }
        extentReport.flush();

    }

    @Test
    public void createContactus() throws InterruptedException {
        ContactUS contactUS = new ContactUS(webDriver);
        contactUS.ClickContact();
        Thread.sleep(5000);
        contactUS.imports();
        extentTest = extentReport.createTest("createContactus");
        extentTest.info("  Your message has been successfully sent to our team");
        extentTest.pass("pass");

        extentReport.flush();
    }

    @Test
    public void searchClear() throws InterruptedException {
        Search search = new Search(webDriver);
        search.suggestionsSearch();
        extentTest = extentReport.createTest("searchClear");
        extentTest.pass("pass");
        extentReport.flush();
    }


    @Test
    public void mouseAddCart() throws InterruptedException {
        HoverMouseToCart hoverMouseToCart = new HoverMouseToCart(webDriver);
        hoverMouseToCart.addCart();
        extentTest = extentReport.createTest("mouseAddCart");
        extentTest.pass("PASS");
        extentReport.flush();
    }

    @Test
    public void removeCartAddCheckout() throws InterruptedException {
        extentTest = extentReport.createTest("removeCartAddCheckout");
        UpdateCart updateCart = new UpdateCart(webDriver);
        updateCart.removeCartItem();
        extentTest.pass("PASS");
        extentReport.flush();
        //You must agree to the terms of service before continuing.
    }

    @Test
    public void sale20() throws InterruptedException {
        List<WebElement> list = webDriver.findElements(By.xpath("//*[@id=\"homefeatured\"]/li/div/div/div/span[3]"));
        extentTest = extentReport.createTest("sale20%");
        boolean check = false;
        for (WebElement webElement : list) {
            if (webElement.getText().equals("-20%")) {
                System.out.println(webElement.getText());
                check = true;
                webElement.click();
            }
        }
        extentTest.info("Your order on My Store is complete.");
        if (check) {
            extentTest.pass("PASS");
        } else {
            extentTest.fail("FAIL");
        }
        extentReport.flush();
        Thread.sleep(3000);
    }


//    @Test
//    public void testPro12() throws InterruptedException {
//        webDriver.findElement(By.xpath("//*[@id=\"homefeatured\"]/li[3]/div/div[1]/div/a[1]/img")).click();
//        Thread.sleep(2000);
//        webDriver.findElement(By.xpath("//*[@id=\"center_column\"]/div/div/div[3]/p[7]/button[1]")).click();
//        String parent = webDriver.getWindowHandle();
//        System.out.println(parent);
//        Thread.sleep(3000);
//        Set<String> handles = webDriver.getWindowHandles();
//        String twitter = "";
//        for (String h : handles) {
//            if (!h.equals(parent)) {
//                twitter = h;
//                System.out.println(h);
//            }
//
//        }
//        try {
//            webDriver.switchTo().window(twitter);
//            System.out.println("Title popup = " + webDriver.getTitle());
//            Thread.sleep(3000);
//            webDriver.findElement(By.xpath("//input[@name='session[username_or_email]']")).sendKeys("ngoc");
//
//        } catch (NoSuchWindowException e) {
//            System.out.println("no the switch");
//
//            Thread.sleep(5000);
//        }
//    }

//
//    @Test
//    public void range() throws InterruptedException {
//     WebElement webElement= webDriver.findElement(By.xpath("//*[@id=\"slider1\"]/div/input"));
//        new Actions(webDriver).dragAndDropBy(webElement, 40, 0).build().perform();
//        Thread.sleep(5000);
//    }

    @BeforeMethod
    public void beforeMethod() {
        System.setProperty("webdriver.chrome.driver", "C://Users//LQA//Desktop//chromedriver.exe");
//        System.setProperty("webdriver.edge.driver", "C://Users//LQA//Desktop//msedgedriver.exe");
        webDriver = new ChromeDriver();
//        webDriver = new EdgeDriver();
        webDriver.manage().window().maximize();
        webDriver.get("http://automationpractice.com/index.php");
//        webDriver.get("https://www.seleniumeasy.com/test/drag-drop-range-sliders-demo.html");
    }

    @AfterMethod
    public void afterMethod() {
        webDriver.close();
    }


//    public static void main(String[] args) {
//        try {
//            URL url = new URL("https://trendmicro.ctydtp.vn/top-25-mat-khau-de-bi-hack-nhat.html");
//            Scanner scanner = new Scanner(new InputStreamReader(url.openStream()));
//            scanner.useDelimiter("\\Z");
//            String content = scanner.next();
//            scanner.close();
//            content = content.replaceAll("\\n+", "");
//            Pattern p = Pattern.compile("box-content blog-content\"><p>(.*?)</p></div>");
//            Matcher m = p.matcher(content);
//            List<String> list = new ArrayList<>();
//            while (m.find()) {
//                list.add(m.group(1));
//            }
//            System.out.println(list.toString());
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        }
//    }
}
