package listeners;

import core.DriverFactory;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ScreenshotUtils;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {

        String testName = result.getName();
        ScreenshotUtils.takeScreenshot(DriverFactory.getDriver(),testName);

    }
}
