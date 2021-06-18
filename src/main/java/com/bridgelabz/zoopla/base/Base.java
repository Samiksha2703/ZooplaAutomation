/**
 * purpose : To create setup to run before and after test cases
 * Author : Samiksha Shende
 * Date : 09/06/2021
 */

package com.bridgelabz.zoopla.base;

import com.bridgelabz.zoopla.utility.Utility;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import java.io.IOException;

import java.util.concurrent.TimeUnit;

public class Base {
    public static WebDriver webdriver;
    public static ExtentTest test;
    static ExtentReports report;
    public static ThreadLocal<WebDriver> threadLocal = new ThreadLocal<>();

    //method to launch browser
    @BeforeTest
    @Parameters("browserName")
    public static void setUp(String browserName) {
        if (browserName.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            webdriver = new ChromeDriver();
        } else if (browserName.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            webdriver = new FirefoxDriver();
        }
        threadLocal.set(webdriver);
        webdriver.manage().window().maximize();
        webdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        try {
            Utility.checkConnection();
            // launch application
            webdriver.get("https://www.zoopla.co.uk/");
            Thread.sleep(1000);
            startTest();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    //method to close the session open by driver
    @AfterTest
    public void tearDown() {
        endTest();
        webdriver.close();
    }

    //method to run before class to generate extent report
    @BeforeClass
    public static void startTest() {
        report = new ExtentReports("C:\\Users\\kalam\\IdeaProjects\\ZooplaAutomationProject\\src\\main\\resources\\ExtentReport\\" + "ExtentReportResults.html");
        test = report.startTest("Bookswagon Extent Report");
    }

    //method to run after class to generate extent report
    @AfterClass
    public static void endTest() {
        report.endTest(test);
        report.flush();
    }

    public static WebDriver getDriver() {
        return threadLocal.get();
    }
}
