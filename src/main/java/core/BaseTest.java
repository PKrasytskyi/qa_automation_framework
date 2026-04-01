package core;

import config.ConfigReader;
import listeners.AllureListener;
import listeners.AgentTriageListener;
import listeners.TestListener;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;


@Listeners({TestListener.class, AllureListener.class, AgentTriageListener.class})
public abstract class BaseTest {

    protected WebDriver driver;
    protected PageManager pages;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {

        driver = DriverFactory.InitDriver();
        driver.get(ConfigReader.getBaseUrl());
        pages = new PageManager(driver);
    }

    public WebDriver getDriver() {
        return DriverFactory.getDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        if (DriverFactory.getDriver() != null) {
            DriverFactory.quitDriver();
        }
        driver = null;
        pages = null;
    }
}
