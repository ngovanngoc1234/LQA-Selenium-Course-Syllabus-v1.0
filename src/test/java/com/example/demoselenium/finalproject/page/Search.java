package com.example.demoselenium.finalproject.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Search extends IsElementPresent {
    public By INPUT_SEARCH = By.xpath("//*[@id=\"search_query_top\"]");
    public By SUBMIT = By.xpath("//*[@id=\"searchbox\"]/button");
    public By SUGGESTIONS = By.xpath("//*[@id=\"index\"]/div[2]/ul");
    public By TXT_SEARCH = By.xpath("//*[@id=\"center_column\"]/h1/span[1]");


    public Search(WebDriver webDriver) {
        this.webDriver = webDriver;
    }


    public void suggestionsSearch() throws InterruptedException {
        inputSearch("aew");
        webDriver.findElement(SUBMIT).click();
        Thread.sleep(3000);
        webDriver.findElement(INPUT_SEARCH).clear();
        inputSearch("dress");
        Thread.sleep(5000);
        List<WebElement> list = webDriver.findElements(By.xpath("//div[@class=\"ac_results\"]//li"));
        System.out.println("size " + list.size());
        for (int i = 0; i < list.size();i++ ) {
//            List<WebElement> list1 = webDriver.findElements(By.xpath("//div[@class=\"ac_results\"]//li"));
            list.get(i).click();
            break;
//            Thread.sleep(5000);
//            inputSearch("dress");
        }
    }



    public String suggestions() {
        return webDriver.findElement(SUGGESTIONS).getText();
    }

    public void inputSearch(String input) {
        webDriver.findElement(INPUT_SEARCH).sendKeys(input);
    }

    public void clearInput() {
        webDriver.findElement(INPUT_SEARCH).clear();
    }

    public void clickSearch() {
        webDriver.findElement(SUBMIT).click();
    }
}
