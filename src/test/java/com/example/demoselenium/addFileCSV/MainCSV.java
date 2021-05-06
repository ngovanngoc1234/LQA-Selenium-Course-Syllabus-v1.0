package com.example.demoselenium.addFileCSV;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class MainCSV extends BaseTest implements Serializable {
    WriteExcelExample writeExcel = new WriteExcelExample();
    List<ReadCSV> readCSVArrayList = new ArrayList<>();
    String excelFilePath = "C:\\Users\\LQA\\Desktop\\Output.xlsx";


    @Test
    public void Test() throws InterruptedException, IOException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\LQA\\Desktop\\crack\\chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();
        webDriver.get("https://client.crowdworks.kr/member/login");
//        form đăng nhập
        sendKeyMethod(By.xpath("//*[@id=\"root\"]/div[1]/section[1]/div[2]/div[2]/form/ul/li[1]/div/ul/li[1]/input"), "tien.vu@lqa.com.vn");
        sendKeyMethod(By.xpath("//*[@id=\"root\"]/div[1]/section[1]/div[2]/div[2]/form/ul/li[2]/div/ul/li[1]/input"), "NVgde2ZSMTW4swB");
        clickMethod(By.xpath("//*[@id=\"root\"]/div[1]/section[1]/div[2]/div[2]/form/ul/li[3]/div/button"));
//        go to project
        clickMethod(By.xpath("//*[@id=\"root\"]/div[1]/section[2]/ul/li/div/section/div/ul/li/div/div"));
        clickMethod(By.xpath("//*[@id=\"root\"]/div[1]/section[2]/ul/li[2]/div/div[2]/div/table/tbody/tr[7]/td[2]/div/div[1]/button"));
//        click project
        clickMethod(By.xpath("//*[@id=\"root\"]/div[1]/section[1]/ul/li/div/section[2]/div[1]/div/div[2]/ul/li[11]/button"));
        clickMethod(By.xpath("//*[@id=\"root\"]/div[1]/section[1]/ul/li/div/section[2]/div[2]/div/table[1]/thead/tr[1]/td/div[1]/button"));


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
//        list id
        String examples = "";
        String food = "";
        String size = "";
        String quantity = "";
        String weight = "";
        String temperatureC = "";
        String smell = "";
        String constitutive = "";
        String offer = "";
        String promotion = "";
        String cartShipper = "";
        String other = "";
        String status = "";
        int check = 0;
        for (int i = 0; i < 501; i++) {

            List<WebElement> list_data = webDriver.findElements(By.xpath("/html/body/div/div/section/ul/li/div/ul/li/div/section/table/tbody/tr/td/button"));
            System.out.println("id = " + list_data.size());
            for (WebElement dataIdButton : list_data) {
//                click vao ID
                if (dataIdButton != null) {
                    webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                    String id = dataIdButton.getText();
                    webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//                chuyển iframe
//                webDriver.switchTo().frame(webDriver.findElement(By.xpath("//iframe[@title=\"monitor\"]")));
//                List<WebElement> listExample = webDriver.findElements(By.xpath("//*[@id=\"root\"]/div/section/ul/li/div/section/ul/li/div/div/section/div/ul/li/div/div/span"));
//                System.out.println("span size = " + listExample.size());
//                for (WebElement example : listExample) {
//                    examples += example.getText();
//                }
////              get thuoc tinh
//                List<WebElement> wdt = webDriver.findElements(By.xpath("(//*[@class=\"css-az7da1\"]//*[@class=\"top\"])//dt//span"));
//                List<WebElement> wdd = webDriver.findElements(By.xpath("//*[@id=\"root\"]/div[1]/section/ul/li/div/section[2]/ul/li[1]/div[2]/div/section/div[1]/ul/li[3]/div/ul/li/form/section/dl/dd/em/span"));
//                List<WebElement> wStatus = webDriver.findElements(By.xpath("//*[@id=\"root\"]/div[1]/section/ul/li/div/section[2]/ul/li[2]/div/div[2]/div/ul/li[3]/dl/dd/table/tbody/tr[2]"));
//                System.out.println("wdt " + wdt.size());
//                System.out.println("wdd " + wdd.size());
//                if (wdd.size() > 0) {
//                    for (WebElement checkValue : wdt) {
//                        for (WebElement properties : wdd) {
//                            if (properties.getText().equals("Khác")) {
//                                other += checkValue.getText() + ", ";
//                            }
//                            if (properties.getText().equals("Món ăn/ Nước uống")) {
//                                food += checkValue.getText() + ", ";
//                            }
//                            if (properties.getText().equals("Số lượng")) {
//                                quantity += checkValue.getText() + ", ";
//                            }
//                        }
//                    }
//                } else {
//                    System.out.println("not found");
//                }
//                for (WebElement sta : wStatus) {
//                    status += sta.getText() + ", ";
//                }
//                System.out.println("other " + other);
//                System.out.println("food " + food);
//                webDriver.switchTo().defaultContent();
//                System.out.println(examples);
                    ReadCSV readCSV = new ReadCSV(id, examples, food, size, quantity, weight, temperatureC, smell, constitutive, offer, promotion, cartShipper, other, status);
//                add vao list CSV
                    readCSVArrayList.add(readCSV);
                    id = null;
//                    examples = "";
//                    food = "";
//                    size = "";
//                    quantity = "";
//                    weight = "";
//                    temperatureC = "";
//                    smell = "";
//                    constitutive = "";
//                    offer = "";
//                    promotion = "";
//                    cartShipper = "";
//                    other = "";
//                    status = "";
                    check++;
                    if (check == 30) {
                        return;
                    }
                }
            }

            clickMethod(By.xpath("//*[@id=\"root\"]/div[1]/section/ul/li/div/ul/li[3]/div/section[2]/table/tfoot/tr/td/ul/li[3]/button"));
            Thread.sleep(1000);
        }
//        extentTest = extentReport.createTest("check timeOut");
//        extentTest.info("good ok");
//        extentTest.pass("PASS");
//        extentReport.flush();
    }

    @AfterMethod
    public void afterMethod() throws Exception {
        writeExcel.writeExcel(readCSVArrayList, excelFilePath);
        webDriver.close();
    }
}
