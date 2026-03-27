package reporting;

import core.BaseTest;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


import java.io.ByteArrayInputStream;
import static core.DriverFactory.getDriver;

public class AllureListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = resolveDriver(result);

        if (driver != null) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment("Screenshot on Failure", new ByteArrayInputStream(screenshot));
        }
    }

    @Override
    public void onTestStart(ITestResult result) { }

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
