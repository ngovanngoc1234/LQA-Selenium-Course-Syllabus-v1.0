package com.example.demoselenium.finalproject;


import com.example.demoselenium.finalproject.page.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;


public class MainTest {
    WebDriver webDriver;

    @Test
    public void createAccount() throws InterruptedException {
        Home home = new Home(webDriver);
        home.signIn();
        CreateAccount createAccount = new CreateAccount(webDriver);
        IsCreateAccount isCreateAccount = new IsCreateAccount(webDriver);
        isCreateAccount.createAccount("abc123123@gmail.com");
        Thread.sleep(5000);
        if (isCreateAccount.isElementPresent(isCreateAccount.SUCCESSFULLY)) {
            System.out.println("tao thanh cong");
        } else {
            System.out.println("ko thanh cong");
        }
        createAccount.addSex();
        createAccount.addFirstName("ngo van");
        createAccount.addLastName("ngoc");
        createAccount.addDay(16);
        createAccount.addMonth(7);
        createAccount.addYears("1994");
        createAccount.addCompany("lqa");
    }

    @Test
    public void newsletter() {
        Newsletter newsletter = new Newsletter(webDriver);
        newsletter.submitNewsletterEmail("ngovanngoc2014@gmail.com");

    }

    @Test
    public void createContactus() throws InterruptedException {
        ContactUS contactUS = new ContactUS(webDriver);
        contactUS.ClickContact();
        Thread.sleep(2000);
        contactUS.imports();
    }

    @Test
    public void searchClear() throws InterruptedException {
        Search search = new Search(webDriver);
        search.suggestionsSearch();
    }

    @Test
    public void mouseAddCart() throws InterruptedException {
        HoverMouseToCart hoverMouseToCart = new HoverMouseToCart(webDriver);
        hoverMouseToCart.addCart();

    }

    @Test
    public void removeCartAddCheckout() throws InterruptedException {
        UpdateCart updateCart = new UpdateCart(webDriver);
        updateCart.removeCartItem();
    }

    @Test
    public void sale20() throws InterruptedException {
        List<WebElement> list = webDriver.findElements(By.xpath("//*[@id=\"homefeatured\"]/li/div/div/div/span[3]"));
        for (WebElement webElement : list) {
            if (webElement.getText().equals("-20%")) {
                System.out.println(webElement.getText());
                webElement.click();
            }
        }
        Thread.sleep(3000);
    }

    @Test
    public void range() throws InterruptedException {
     WebElement webElement= webDriver.findElement(By.xpath("//*[@id=\"slider1\"]/div/input"));
        new Actions(webDriver).dragAndDropBy(webElement, 40, 0).build().perform();
        Thread.sleep(5000);
    }

    @BeforeMethod
    public void beforeMethod() {
        System.setProperty("webdriver.chrome.driver", "C://Users//LQA//Desktop//chromedriver.exe");
//        System.setProperty("webdriver.edge.driver", "C://Users//LQA//Desktop//msedgedriver.exe");
        webDriver = new ChromeDriver();
//        webDriver = new EdgeDriver();
        webDriver.manage().window().maximize();
//        webDriver.get("http://automationpractice.com/index.php");
        webDriver.get("https://www.seleniumeasy.com/test/drag-drop-range-sliders-demo.html");
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
