package listeners;

import core.BaseTest;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


import static core.DriverFactory.getDriver;

public class AllureListener implements ITestListener, IInvokedMethodListener {

    private static final String SCREENSHOT_ATTACHED_ATTRIBUTE = "allure.screenshotAttached";

    @Override
    public void onTestStart(ITestResult result) {
        AllureAttachmentSupport.captureTestCaseUuid(result);
    }

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult result) {
        if (method.isTestMethod()) {
            AllureAttachmentSupport.captureTestCaseUuid(result);
        }
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult result) {
        if (!method.isTestMethod() || result.getStatus() != ITestResult.FAILURE) {
            return;
        }

        attachScreenshot(result);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        attachScreenshot(result);
    }

    private void attachScreenshot(ITestResult result) {
        if (Boolean.TRUE.equals(result.getAttribute(SCREENSHOT_ATTACHED_ATTRIBUTE))) {
            return;
        }

        WebDriver driver = resolveDriver(result);

        if (driver != null) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            boolean attached = AllureAttachmentSupport.addByteAttachment(
                    result,
                    "Screenshot on Failure",
                    "image/png",
                    ".png",
                    screenshot
            );

            if (attached) {
                result.setAttribute(SCREENSHOT_ATTACHED_ATTRIBUTE, true);
            }
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) { }

    @Override
    public void onTestSkipped(ITestResult result) { }

    @Override
    public void onStart(ITestContext context) { }

    @Override
    public void onFinish(ITestContext context) { }

    private WebDriver resolveDriver(ITestResult result) {
        Object instance = result.getInstance();

        if (instance instanceof BaseTest baseTest && baseTest.getDriver() != null) {
            return baseTest.getDriver();
        }

        return getDriver();
    }
}
