package core;

import config.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.LoginPage;
import pages.MainPage;
import pages.SecureAreaPage;

import java.time.Duration;


@Listeners(listeners.TestListener.class)
public abstract class BaseTest {

    protected WebDriver driver;
    protected MainPage mainPage;
    protected LoginPage loginPage;
    protected SecureAreaPage secureAreaPage;

    @BeforeMethod
    public void setUp(){

        driver = DriverFactory.InitDriver();
        driver.get(ConfigReader.getBaseUrl());

        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(ConfigReader.getTimeout()));

        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        secureAreaPage = new SecureAreaPage(driver);

    }

    @AfterMethod
    public void tearDown(ITestResult result){
        if (DriverFactory.getDriver() != null){
            DriverFactory.quitDriver();
        }
    }
}
