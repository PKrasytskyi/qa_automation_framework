package core;

import config.ConfigReader;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;


@Listeners(listeners.TestListener.class)
public abstract class BaseTest {

    @BeforeMethod
    public void setUp(){

        DriverFactory.InitDriver();
        DriverFactory.getDriver().get(ConfigReader.getBaseUrl());
    }

    @AfterMethod
    public void tearDown(ITestResult result){
        DriverFactory.quitDriver();
    }
}
