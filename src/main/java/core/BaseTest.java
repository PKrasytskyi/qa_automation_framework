package core;

import config.ConfigReader;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {

    ConfigReader configReader;

    @BeforeMethod
    public void setUp(){
        configReader = new ConfigReader();
        DriverFactory.initDriver(configReader.getProperties("browser"));
        DriverFactory.getDriver().get(configReader.getProperties("app.url"));
    }

    @AfterMethod
    public void tearDown(){
        DriverFactory.quitDriver();
    }
}
