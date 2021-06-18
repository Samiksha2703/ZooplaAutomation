/**
 * purpose : To perform test to check functionality of different features of Zoopla Application
 * Author : Samiksha Shende
 * Date : 09/06/2021
 */

package com.bridgelabz.zoopla.test;

import com.bridgelabz.zoopla.base.Base;
import com.bridgelabz.zoopla.pages.Home;
import com.bridgelabz.zoopla.utility.TestNgListener;
import com.bridgelabz.zoopla.utility.Utility;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;

@Listeners(TestNgListener.class)
public class ZooplaTest extends Base {
    Home home;

    @Test
    public void whenSearchWithLocation_ShouldPrintProperties() throws IOException {
        home = new Home(webdriver);
        Boolean check = home.searchProperties(webdriver);
        if (check) {
            test.log(LogStatus.PASS, test.addScreenCapture(Utility.screenshot("AgentPage")));
        } else {
            test.log(LogStatus.FAIL, test.addScreenCapture(Utility.screenshot("AgentPage")));
        }
    }
}
