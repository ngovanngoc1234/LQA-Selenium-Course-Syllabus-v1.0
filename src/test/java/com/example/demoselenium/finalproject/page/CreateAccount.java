package com.example.demoselenium.finalproject.page;


import com.example.demoselenium.finalproject.object.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class CreateAccount extends BaseTest {
    public By SEX = By.xpath("//*[@id=\"account-creation_form\"]/div[1]/div[1]/div[1]");
    public By FIRST_NAME = By.xpath("//*[@id=\"customer_firstname\"]");
    public By LAST_NAME = By.xpath("//*[@id=\"customer_lastname\"]");
    public By EMAIL = By.xpath("//*[@id=\"email\"]");
    public By PASSWORD = By.xpath("//*[@id=\"passwd\"]");
    public By DAYS = By.xpath("//*[@id=\"days\"]");
    public By MONTHS = By.xpath("//*[@id=\"months\"]");
    public By YEARS = By.xpath("//*[@id=\"years\"]");
    public By COMPANY = By.xpath("//*[@id=\"company\"]");
    public By ADDRESS = By.xpath("//*[@id=\"address1\"]");
    public By CTY = By.xpath("//*[@id=\"city\"]");
    public By STATE = By.xpath("//*[@id=\"id_state\"]");
    public By ZIP_POSTAL_CODE = By.xpath("//*[@id=\"postcode\"]");
    public By COUNTY = By.xpath("//*[@id=\"id_country\"]");
    public By OTHER = By.xpath("//*[@id=\"other\"]");
    public By HOME_PHONE = By.xpath("//*[@id=\"phone\"]");
    public By MOBILE_PHONE = By.xpath("//*[@id=\"phone_mobile\"]");
    public By ALIAS = By.xpath("//*[@id=\"alias\"]");
    public By REGISTER = By.xpath("//*[@id=\"account-creation_form\"]/div[4]");




    public CreateAccount(WebDriver webDriver) {
        this.webDriver = webDriver;
    }


    public void addSex() {
        clickMethod(SEX);
        WebElement element = webDriver.findElement(SEX);
        if (element.isSelected()) element.click();
    }

    public void addFirstName(String firstName) {
        sendKeyMethod(FIRST_NAME,firstName);
    }

    public void addLastName(String addLastName) {
        sendKeyMethod(LAST_NAME,addLastName);
    }

    public void addEmail(String addEmail) {
        sendKeyMethod(EMAIL,addEmail);
    }

    public void addPass(String addPass) {
        sendKeyMethod(PASSWORD,addPass);
    }

    public void addDay(int addDay) {
        Select select = new Select(webDriver.findElement(DAYS));
        select.selectByIndex(addDay);
    }

    public void addMonth(int addMonth) {
        Select select = new Select(webDriver.findElement(MONTHS));
        select.selectByIndex(addMonth);
    }

    public void addYears(String addYears) {
        Select select = new Select(webDriver.findElement(YEARS));
        select.selectByValue(addYears);
    }

    public void addCompany(String addCompany) {
        sendKeyMethod(COMPANY,addCompany);
    }

    public void address(String address) {
        sendKeyMethod(ADDRESS,address);
    }

    public void addCty(String addCty) {
        sendKeyMethod(CTY,addCty);
    }

    public void addState(int addState) {
        Select select = new Select(webDriver.findElement(STATE));
        select.selectByIndex(addState);
    }

    public void addZipCode(String addZipCode) {
        sendKeyMethod(ZIP_POSTAL_CODE,addZipCode);
    }

    public void addCounty(int addCounty) {
        Select select = new Select(webDriver.findElement(COUNTY));
        select.selectByIndex(addCounty);
    }

    public void addOther(String addOther) {
        sendKeyMethod(OTHER,addOther);
    }

    public void addHomePhone(String addHomePhone) {
        sendKeyMethod(HOME_PHONE,addHomePhone);
    }

    public void addMobilePhone(String addMobilePhone) {
        sendKeyMethod(MOBILE_PHONE,addMobilePhone);
    }

    public void addAlias(String addAlias) {
        sendKeyMethod(ALIAS,addAlias);
    }

    public void addRegister(String addRegister) {
        sendKeyMethod(REGISTER,addRegister);
    }
}
