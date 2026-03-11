package tests.ui;

import core.BaseTest;
import core.DriverFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MainPage;


public class TestTitle extends BaseTest {

    MainPage mainPage;

    @Test
    public void getTitel(){
            mainPage = new MainPage(DriverFactory.getDriver());
            String expectedResult = "demosite";
            Assert.assertEquals(mainPage.getHeader(), expectedResult);
    }
}
