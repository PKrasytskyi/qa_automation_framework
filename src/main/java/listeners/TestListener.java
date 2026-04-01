package listeners;

import api.triage.FailureContext;
import api.triage.FailureContextBuilder;
import core.BaseTest;
import core.DriverFactory;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ScreenshotUtils;

import org.openqa.selenium.WebDriver;

public class TestListener implements ITestListener, IInvokedMethodListener {

    public static final String FAILURE_CONTEXT_ATTRIBUTE = "failureContext";

    @Override
    public void onTestFailure(ITestResult result) {
        populateFailureContext(result);
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult result) {
        if (!method.isTestMethod() || result.getStatus() != ITestResult.FAILURE) {
            return;
        }

        populateFailureContext(result);
    }

    private void populateFailureContext(ITestResult result) {
        if (result.getAttribute(FAILURE_CONTEXT_ATTRIBUTE) instanceof FailureContext) {
            return;
        }

        String testName = result.getName();
        WebDriver driver = resolveDriver(result);
        String screenshotPath = ScreenshotUtils.takeScreenshot(driver, testName);

        FailureContext failureContext = new FailureContextBuilder(driver)
                .build(result, driver, screenshotPath);

        result.setAttribute(FAILURE_CONTEXT_ATTRIBUTE, failureContext);
    }

    private WebDriver resolveDriver(ITestResult result) {
        WebDriver driver = DriverFactory.getDriver();

        if (driver != null) {
            return driver;
        }

        Object instance = result.getInstance();
        if (instance instanceof BaseTest baseTest) {
            return baseTest.getDriver();
        }

        return null;
    }
}
