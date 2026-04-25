package listeners;

import api.logging.ApiCallLog;
import api.logging.ApiCallLogStore;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.List;

public class ApiAllureFailureListener implements ITestListener, IInvokedMethodListener {

    private static final String API_ATTACHMENTS_ADDED = "allure.apiAttachmentsAdded";

    @Override
    public void onTestStart(ITestResult result){
        AllureAttachmentSupport.captureTestCaseUuid(result);
    }

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult result){
        if(method.isTestMethod()){
            AllureAttachmentSupport.captureTestCaseUuid(result);
        }
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult result){
        if(!method.isTestMethod()){
            return;
        }

        if(result.getStatus() == ITestResult.FAILURE){
            attachApiTraffic(result);
        }

        ApiCallLogStore.clean();
    }

    @Override
    public void onTestFailure(ITestResult result){
        attachApiTraffic(result);
    }

    private void attachApiTraffic(ITestResult result){
        if(Boolean.TRUE.equals(result.getAttribute(API_ATTACHMENTS_ADDED))) {
        return;
    }

    List<ApiCallLog> calls = ApiCallLogStore.getAll();
        if(calls.isEmpty()){
        return;
    }

        int index = 1;
        for(ApiCallLog call : calls){
            AllureAttachmentSupport.addTextAttachment(
                    result,
                    "API Request " + index + " - " + call.name(),
                    call.request()
            );
            AllureAttachmentSupport.addTextAttachment(
                    result,
                    "API Response " + index + " - " + call.name(),
                    call.response()
            );
            index++;
        }

        result.setAttribute(API_ATTACHMENTS_ADDED, true);
    }

}
