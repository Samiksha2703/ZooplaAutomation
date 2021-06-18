package com.bridgelabz.zoopla.utility;

import com.bridgelabz.zoopla.base.Base;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNgListener implements ITestListener {

    //Method to print log when every test starts
    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test starts " + result.getName());
    }

    //Method to print log when test executes successfully
    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Name of the test case passed is " + result.getName());
        TakeScreenshot(Base.getDriver());
    }

    //Method to print log when test fails
    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Name of the test case failed is " + result.getName());
        TakeScreenshot(Base.getDriver());
    }

    //Method to print log when test skipped
    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Name of the test case skipped is " + result.getName());
    }

    //Method to print log when test failed with success percentage
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("Name of the test case failed with success percentage is " + result.getName());
    }

    //Method to print log when test failed with timeout
    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        System.out.println("Name of the test case failed with timeout is " + result.getName());
    }

    //Method to print log when test execution finish
    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Test Finished for " + context.getName());
    }

    @Attachment(value = "Screenshot", type = "image/jpg")
    public byte[] TakeScreenshot(WebDriver webdriver) {
        return ((TakesScreenshot) webdriver).getScreenshotAs(OutputType.BYTES);
    }
}
