package core;

import io.restassured.RestAssured;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

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
    }

    public ApiManager getApi(){
        return api;
    }
}
