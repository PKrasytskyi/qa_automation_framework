package core;

import config.ConfigReader;
import listeners.AllureListener;
import listeners.AgentTriageListener;
import listeners.TestListener;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;


@Listeners({TestListener.class, AllureListener.class, AgentTriageListener.class})
public abstract class BaseTest {

    private static final ThreadLocal<PageManager> PAGE_MANAGER = new ThreadLocal<>();

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        WebDriver driver = DriverFactory.initDriver();

        PAGE_MANAGER.set(new PageManager(driver));
        driver.get(ConfigReader.getBaseUrl());

    }

    public WebDriver getDriver() {
        WebDriver driver = DriverFactory.getDriver();

        if(driver == null){
            throw new IllegalStateException(
                    "WebDriver is not initialized for the current thread"
            );
        }
        return driver;
    }

    protected PageManager getPages() {
        PageManager pageManager = PAGE_MANAGER.get();

        if (pageManager == null) {
            throw new IllegalStateException(
                    "PageManager is not initialized for the current thread"
            );
        }

        return pageManager;
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        try {
            DriverFactory.quitDriver();
        } finally {
            PAGE_MANAGER.remove();
        }
    }
}
