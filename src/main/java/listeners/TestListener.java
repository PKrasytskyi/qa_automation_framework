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
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_RED = "\u001B[31m";

    @Override
    public void onTestSuccess(ITestResult result) {
        consoleTestResult(result);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        consoleTestResult(result);
        populateFailureContext(result);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        consoleTestResult(result);
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult result) {
        if (!method.isTestMethod() || result.getStatus() != ITestResult.FAILURE) {
            return;
        }

        populateFailureContext(result);
    }

    public void consoleTestResult(ITestResult result){
        String testName = result.getMethod().getMethodName();

        if(result.getStatus() == ITestResult.FAILURE){
            System.out.println(ANSI_RED + "Test: " + testName + " FAILED" + ANSI_RESET);
        } else if (result.getStatus() == ITestResult.SKIP) {
            System.out.println(ANSI_YELLOW + "Test: " + testName + " SKIPPED" + ANSI_RESET);
        } else if(result.getStatus() == ITestResult.SUCCESS)
        {
            System.out.println(ANSI_GREEN + "Test: " + testName + " PASSED" + ANSI_RESET);
        }
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
