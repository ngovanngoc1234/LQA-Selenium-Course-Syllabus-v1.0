package com.example.demoselenium.addFileCSV;


import com.example.demoselenium.getAPI.ReadListID;
import com.example.demoselenium.object.DataID;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.interactions.Actions;
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
    //    link file ex to pc
    String excelFilePath = "C:\\Users\\phongdt\\Desktop\\CRW\\Excel\\l2id18052021.xlsx";


    public String takeElementSnapShot(WebElement webElement, String idImage) throws Exception {
        //Create file path
        String screenshotName = idImage + ".png";
//        link chua image to pc
        String screenshotPath = "C:\\Users\\phongdt\\Desktop\\CRW\\Images\\L2\\" + screenshotName;

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
        sendKeyMethod(By.xpath("//*[@name=\"email\"]"), "tien.vu@lqa.com.vn");
        sendKeyMethod(By.xpath("//*[@name=\"password\"]"), "NVgde2ZSMTW4swB");
        clickMethod(By.xpath("//*[@class=\"css-2jtqid\"]"));
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        go to project
        clickMethod(By.xpath("//*[@data-group-id=\"423\"]"));
        clickMethod(By.xpath("(//*[@data-project-id=\"7723\"])[1]"));
//        click project
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        clickMethod(By.xpath("//*[@data-index=\"5\"]"));
        clickMethod(By.xpath("(//*[@class=\"css-jf130o\"])[1]"));
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


        String startDate = "2021-05-18";
        String endDate = "2021-05-18";
        int result = 3305;

//        select Search period
        WebElement searchPeriodElt = webDriver.findElement(By.xpath("//*[@name=\"rangingField\"]"));
        Select select = new Select(searchPeriodElt);
        select.selectByVisibleText("Submit work date");

        // Start date
        WebElement startDateElt = webDriver.findElement(By.xpath("(//*[@placeholder=\"YYYY-MM-DD\"])[1]"));

        ((JavascriptExecutor) webDriver).executeScript("arguments[0].removeAttribute('readonly','readonly')", startDateElt);
        new Actions(webDriver).click(startDateElt).sendKeys(Keys.END).keyDown(Keys.SHIFT).sendKeys(Keys.HOME).keyUp(Keys.SHIFT).sendKeys(Keys.BACK_SPACE).sendKeys(startDate).perform();
        // end date
        WebElement endDateElt = webDriver.findElement(By.xpath("(//*[@placeholder=\"YYYY-MM-DD\"])[2]"));

        ((JavascriptExecutor) webDriver).executeScript("arguments[0].removeAttribute('readonly','readonly')", endDateElt);
        new Actions(webDriver).click(endDateElt).sendKeys(Keys.END).keyDown(Keys.SHIFT).sendKeys(Keys.HOME).keyUp(Keys.SHIFT).sendKeys(Keys.BACK_SPACE).sendKeys(endDate).perform();
        // click Search
        webDriver.findElement(By.xpath("//*[@class=\"project-monitor-board css-486dx3\"]")).click();
        webDriver.findElement(By.xpath("//*[@class=\"css-1ypq19w\"]")).click();
        Thread.sleep(1000);


        int check = 0;
        for (int i = 0; i < 1000; i++) {
            String namClass = "";
            String status = "";
            String workName = "";
            String landmark = "";

            List<WebElement> list_data = webDriver.findElements(By.xpath("//*[@data-id]"));
            List<WebElement> listStatus = webDriver.findElements(By.xpath("//*[@id=\"root\"]/div[1]/section/ul/li/div/ul/li[3]/div/section[2]/table/tbody/tr/td[2]"));
            List<WebElement> listWorkName = webDriver.findElements(By.xpath("//*[@id=\"root\"]/div[1]/section/ul/li/div/ul/li[3]/div/section[2]/table/tbody/tr/td[3]"));
            System.out.println("id = " + list_data.size());

            for (int j = 0, list_dataSize = list_data.size(); j < list_dataSize; j++) {
                WebElement dataIdButton = list_data.get(j);
//                click vao ID
                if (dataIdButton != null) {
                    webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

//                    lay id
                    String id = dataIdButton.getText();
                    status = listStatus.get(j).getText();
                    workName = listWorkName.get(j).getText();
                    dataIdButton.click();
                    webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//                chuyển iframe
                    WebElement forIframe = new WebDriverWait(webDriver, 30)
                            .until(ExpectedConditions.elementToBeClickable(By.xpath("//iframe[@title=\"monitor\"]")));
                    webDriver.switchTo().frame(forIframe);
//                    get text landmark
                    WebElement clickZoom = new WebDriverWait(webDriver, 30)
                            .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div[1]/section/ul/li/div/section[2]/ul/li[2]/div/div[1]/button")));
                    clickZoom.click();
                    WebElement getTextLandmark = new WebDriverWait(webDriver, 30)
                            .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class=\"content-group  css-1rw3nf5\"]//li[2]/div/p")));
                    landmark = getTextLandmark.getText();
//                    chup hinh

                    WebElement imageBox = new WebDriverWait(webDriver, 30)
                            .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class=\"upper-canvas fabric-canvas\"]")));

                    WebElement statusClass = new WebDriverWait(webDriver, 30)
                            .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class=\"tag-label css-11y9bag\"]")));

                    if (imageBox != null || statusClass != null) {
                        Thread.sleep(1000);
                        namClass = statusClass.getText();
                        takeElementSnapShot(imageBox, id);
                    } else {
                        namClass = "";
                    }
                    webDriver.switchTo().defaultContent();

//                add vao list CSV
                    check++;
                    if (check == result) {
                        return;
                    }
                    DataID dataID = new DataID(id, namClass, status, workName, landmark);
                    list.add(dataID);
                    namClass = "";
                    id = "";
                    status = "";
                    workName = "";
                    landmark = "";
                }
            }

            clickMethod(By.xpath("(//*[@type=\"button\"])[34]"));
            Thread.sleep(1000);
        }
    }

    @AfterMethod
    public void afterMethod() throws Exception {
        readListID.writeExcel(list, excelFilePath);

        webDriver.close();
    }
}
