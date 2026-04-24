package assertions;

import io.restassured.response.Response;
import org.testng.Assert;

public class ApiAssertions {

    private ApiAssertions(){}

    public static void assertStatusCode(Response response, int expectedStatusCode){

        Assert.assertEquals(response.getStatusCode(), expectedStatusCode, "Status code should match");
    }
}
