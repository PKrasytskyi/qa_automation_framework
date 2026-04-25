package tests.api.goRestApiTests;

import api.clients.GoRestUserClient;
import api.models.request.CreateGoRestUserRequest;
import api.specs.ApiResponseSpec;
import core.ApiBaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.Helpers.EmailGenerator;

public class UserCreateNegativeTests extends ApiBaseTest {

    private GoRestUserClient client;

    @BeforeMethod(alwaysRun = true)
    public void initClient(){
        this.client = new GoRestUserClient(api);
    }

    @Test(groups = {"goRest.Api"})
    public void shouldReturnAuthError(){
        CreateGoRestUserRequest request = new CreateGoRestUserRequest();

        request.setName("UnAuthTest");
        request.setEmail(EmailGenerator.uniqueEmail());
        request.setGender("male");
        request.setStatus("active");

        Response response = client.createUserWithoutAuthorization(request);

        response.then().spec(ApiResponseSpec.statusCode401Js());

        Assert.assertTrue(response.jsonPath().getString("message").contains("Authentication failed"), "Should return authentication failed");

    }

    @Test(groups = {"goRest.Api"})
    public void shouldReturnValidationError(){
        CreateGoRestUserRequest request = new CreateGoRestUserRequest();

        request.setName("duplEmail");
        request.setEmail("duplemail@mail.test");
        request.setGender("male");
        request.setStatus("active");



        Response response = client.createUser(request);

        CreateGoRestUserRequest request1 = new CreateGoRestUserRequest();

        request.setName("dupl2Email");
        request.setEmail("duplemail@mail.test");
        request.setGender("male");
        request.setStatus("active");

        Assert.assertEquals(response.getStatusCode(), 422, "Status code should be 422");
        Assert.assertTrue(response.jsonPath().getString("message").contains("has already been taken"));
    }
}
