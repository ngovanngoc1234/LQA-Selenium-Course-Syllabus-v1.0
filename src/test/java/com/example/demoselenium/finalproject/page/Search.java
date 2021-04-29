package com.example.demoselenium.finalproject.page;

import com.example.demoselenium.finalproject.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Search extends BaseTest {
    public By INPUT_SEARCH = By.xpath("//*[@id=\"search_query_top\"]");
    public By SUBMIT = By.xpath("//*[@id=\"searchbox\"]/button");
    public By SUGGESTIONS = By.xpath("//*[@id=\"index\"]/div[2]/ul");
    public By TXT_SEARCH = By.xpath("//*[@id=\"center_column\"]/h1/span[1]");


    public Search(WebDriver webDriver) {
        this.webDriver = webDriver;
    }


    public void suggestionsSearch() throws InterruptedException {
        inputSearch("aew");
        clickMethod(SUBMIT);
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        clearMethod(INPUT_SEARCH);
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        inputSearch("dress");
        List<WebElement> list = webDriver.findElements(By.xpath("//div[@class=\"ac_results\"]//li"));
        System.out.println("size " + list.size());
        for (int i = 0; i < list.size(); i++) {
            list.get(i).click();
            break;

        }
    }


    public String suggestions() {
        return webDriver.findElement(SUGGESTIONS).getText();
    }

    public void inputSearch(String input) {
        sendKeyMethod(INPUT_SEARCH,input);
    }

    public void clearInput() {
        clearMethod(INPUT_SEARCH);
    }

    public void clickSearch() {
        clickMethod(SUBMIT);
    }
}
