package com.example.demoselenium.addFileCSV;


import com.example.demoselenium.getAPI.ReadListID;
import com.example.demoselenium.object.DataID;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class MainCSV extends BaseTest implements Serializable {
    ReadListID readListID = new ReadListID();
    List<DataID> list = new ArrayList<>();
    String excelFilePath = "C:\\Users\\phongdt\\Desktop\\result ex\\namclass id.xlsx";


    public String takeElementSnapShot(WebElement webElement, String idImage) throws Exception {
        //Create file path
        String screenshotName = idImage + ".png";
        String screenshotPath = "C:\\Users\\phongdt\\Desktop\\image\\" + screenshotName;

        // Convert web driver object to TakeScreenshot
        TakesScreenshot scrShot = ((TakesScreenshot) webElement);
        // Call getScreenshotAs method to create image file
        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
        // Move image file to new destination
        File DestFile = new File(screenshotPath);
        // Copy file at destination
        try {
            FileUtils.copyFile(SrcFile, DestFile);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return screenshotPath;
    }

    @Test
    public void Test() throws Exception {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();
        webDriver.get("https://client.crowdworks.kr/member/login");
//        form đăng nhập
        sendKeyMethod(By.xpath("//*[@id=\"root\"]/div[1]/section[1]/div[2]/div[2]/form/ul/li[1]/div/ul/li[1]/input"), "tien.vu@lqa.com.vn");
        sendKeyMethod(By.xpath("//*[@id=\"root\"]/div[1]/section[1]/div[2]/div[2]/form/ul/li[2]/div/ul/li[1]/input"), "NVgde2ZSMTW4swB");
        clickMethod(By.xpath("//*[@id=\"root\"]/div[1]/section[1]/div[2]/div[2]/form/ul/li[3]/div/button"));
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        go to project
        clickMethod(By.xpath("//*[@id=\"root\"]/div[1]/section[2]/ul/li/div/section/div/ul/li/div/div"));
        clickMethod(By.xpath("//*[@id=\"root\"]/div[1]/section[2]/ul/li[2]/div/div[2]/div/table/tbody/tr[2]/td[2]/div/div[1]/button"));
//        click project
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        clickMethod(By.xpath("//*[@id=\"root\"]/div[1]/section[1]/ul/li/div/section[2]/div[1]/div/div[2]/ul/li[11]/button"));
        clickMethod(By.xpath("//*[@id=\"root\"]/div[1]/section[1]/ul/li/div/section[2]/div[2]/div/table[1]/thead/tr[1]/td/div[1]/button"));
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

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

        int check = 0;
        String dateStar = "2021-05-17";
        String dateEnd = "2021-05-17";
        for (int i = 0; i < 1000; i++) {
            String namClass = "";
            String status = "";
            String workName = "";

            List<WebElement> list_data = webDriver.findElements(By.xpath("/html/body/div/div/section/ul/li/div/ul/li/div/section/table/tbody/tr/td/button"));
            System.out.println("id = " + list_data.size());

            for (WebElement dataIdButton : list_data) {
//                click vao ID
                if (dataIdButton != null) {
                    webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//                    chon ngay va work date
                    WebElement webElement2 = webDriver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/section/ul/li/div/ul/li[3]/div/section[1]/form/table/tbody/tr[2]/td/div/div[1]/div/select"));
                    Select select = new Select(webElement2);
                    select.selectByVisibleText("Submit work date");
                    webDriver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/section/ul/li/div/ul/li[3]/div/section[1]/form/table/tbody/tr[2]/td/div/div[2]/div[1]/div/div/input")).sendKeys(dateStar);
                    webDriver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/section/ul/li/div/ul/li[3]/div/section[1]/form/table/tbody/tr[2]/td/div/div[2]/div[2]/div/div/input")).sendKeys(dateEnd);
                    webDriver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/section/ul/li/div/ul/li[3]/div/section[1]/form/table/tfoot/tr/td/div[2]/button")).click();
                    Thread.sleep(1000);

//                    lay id
                    String id = dataIdButton.getText();
                    status = webDriver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/section/ul/li/div/ul/li[3]/div/section[2]/table/tbody/tr/td[2]")).getText();
                    workName = webDriver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/section/ul/li/div/ul/li[3]/div/section[2]/table/tbody/tr/td[3]")).getText();
                    dataIdButton.click();
                    webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//                chuyển iframe
                    webDriver.switchTo().frame(webDriver.findElement(By.xpath("//iframe[@title=\"monitor\"]")));
                    webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                    webDriver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/section/ul/li/div/section[2]/ul/li[2]/div/div[1]/button")).click();
                    Thread.sleep(500);
//                    WebDriverWait wait = new WebDriverWait(webDriver, 30);
//                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/div[1]/section/ul/li/div/section[2]/ul/li[1]/div[2]/div/section/div[1]/ul/li[1]/div[1]/div/div[2]/div[2]/div[2]/canvas[2]")));
                    WebElement webElement = webDriver
                            .findElement(By.xpath("//*[@id=\"root\"]/div[1]/section/ul/li/div/section[2]/ul/li[1]/div[2]/div/section/div[1]/ul/li[1]/div[1]/div/div[2]/div[2]/div[2]/canvas[2]"));
                    WebElement webElement1 = webDriver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/section/ul/li/div/section[2]/ul/li[1]/div[2]/div/section/div[1]/ul/li[3]/div/ul/li/form/section[1]/dl/dd"));

                    if (webElement != null || webElement1 != null) {
                        Thread.sleep(1700);
                        namClass = webElement1.getText();
                        takeElementSnapShot(webElement, id);
                    } else {
                        namClass = "";
                    }
                    webDriver.switchTo().defaultContent();

//                add vao list CSV
                    check++;
                    if (check == 90) {
                        return;
                    }
                    DataID dataID = new DataID(id, namClass,status,workName);
                    list.add(dataID);
                    namClass = "";
                    id = "";
                    status = "";
                    workName = "";
                }
            }

            clickMethod(By.xpath("//*[@id=\"root\"]/div[1]/section/ul/li/div/ul/li[3]/div/section[2]/table/tfoot/tr/td/ul/li[3]/button"));
            Thread.sleep(1000);
        }
    }

    @AfterMethod
    public void afterMethod() throws Exception {
        readListID.writeExcel(list, excelFilePath);
        webDriver.close();
    }
}
