package listeners;

import core.BaseTest;
import core.DriverFactory;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ScreenshotUtils;

import org.openqa.selenium.WebDriver;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getName();
        WebDriver driver = resolveDriver(result);
        ScreenshotUtils.takeScreenshot(driver, testName);
    }

    private WebDriver resolveDriver(ITestResult result) {
        Object instance = result.getInstance();

        if (instance instanceof BaseTest baseTest && baseTest.getDriver() != null) {
            return baseTest.getDriver();
        }

        return DriverFactory.getDriver();
    }
}
