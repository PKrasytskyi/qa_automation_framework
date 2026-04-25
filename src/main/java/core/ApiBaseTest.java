package core;

import listeners.ApiAllureFailureListener;
import api.logging.ApiCallLogStore;
import io.restassured.RestAssured;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

@Listeners(ApiAllureFailureListener.class)
public abstract class ApiBaseTest {

    protected ApiManager api;

    @BeforeMethod(alwaysRun = true)
    public void setUpApi(){
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        api = new ApiManager();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownApi(){
        if(api != null){
            api.reset();
        }

        api = null;
        ApiCallLogStore.clean();
    }

    public ApiManager getApi(){
        return api;
    }
}
