/**
 * purpose : To perform various operations of Zoopla Application
 * Author : Samiksha Shende
 * Date : 09/06/2021
 */


package com.bridgelabz.zoopla.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Home {
    public static LinkedList<String> propertyList = new LinkedList<>();

    //@FindBy(xpath = "//button[text()='Accept all cookies']")
    @FindBy(xpath = "//button[@class='ui-button-primary ui-cookie-accept-all-medium-large']")
    WebElement acceptCookies;

    @FindBy(xpath = "//input[@id='header-location']")
    WebElement searchBox;

    @FindBy(xpath = "//button[@data-testid='London']")
    WebElement selectLocation;

    @FindBy(xpath = "/html/body/div/main/div[1]/div[3]/div[2]/div/div[8]/button")
    WebElement searchButton;

    @FindBy(xpath = "//a[@class='e2uk8e4 css-15tydk8-StyledLink-Link-FullCardLink e33dvwd0']")
    static List<WebElement> listOfProperties;

    @FindBy(linkText = "View agent properties")
    WebElement agentLink;

    @FindBy(xpath = "//li[@class='srp clearfix   ']")
    List<WebElement> agentPropertyList;

    public Home(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public Boolean searchProperties(WebDriver webdriver) {
        try {
            acceptCookies.click();
            WebDriverWait wait2 = new WebDriverWait(webdriver, 10);
            Thread.sleep(1000);
            searchBox.sendKeys("London");
            Thread.sleep(1000);
            selectLocation.click();
            searchButton.click();
            Iterator<WebElement> propertyItr = listOfProperties.iterator();
            while (propertyItr.hasNext()) {
                String property = propertyItr.next().getText();
                propertyList.add(property);
            }
            propertyList.forEach(element -> {
                System.out.println(element);
                System.out.println(" ");
            });
            listOfProperties.get(4).click();
            agentLink.click();
            agentPropertyList.forEach(webElement -> {
                if (propertyList.get(4).contains(webElement.getText())) ;
                {
                    System.out.println("matching");
                }
            });
            return true;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }
}
