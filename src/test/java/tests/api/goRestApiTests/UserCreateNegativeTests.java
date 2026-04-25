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

    @Test(groups = {"api"})
    public void shouldReturnAuthError(){
        CreateGoRestUserRequest request = new CreateGoRestUserRequest();

        request.setName("UnAuthTest");
        request.setEmail(EmailGenerator.uniqueEmail());
        request.setGender("male");
        request.setStatus("active");

        Response response = client.createUserWithoutAuthorization(request);

        response.then().spec(ApiResponseSpec.statusCode401Js());

        Assert.assertTrue(response.getBody().asString().contains("Authentication failed"), "Should return authentication failed");

    }

    @Test(groups = {"api"})
    public void shouldReturnValidationErrorForDuplicateEmail() {
        CreateGoRestUserRequest firstRequest = new CreateGoRestUserRequest();
        firstRequest.setName("duplEmail");
        firstRequest.setEmail("duplemail22@mail.test");
        firstRequest.setGender("male");
        firstRequest.setStatus("active");

        Response firstResponse = client.createUser(firstRequest);
        Assert.assertEquals(firstResponse.getStatusCode(), 201, "First user should be created");

        CreateGoRestUserRequest secondRequest = new CreateGoRestUserRequest();
        secondRequest.setName("dupl2Email");
        secondRequest.setEmail("duplemail22@mail.test");
        secondRequest.setGender("male");
        secondRequest.setStatus("active");

        Response secondResponse = client.createUser(secondRequest);

        Assert.assertEquals(secondResponse.getStatusCode(), 422, "Status code should be 422");
        Assert.assertTrue(secondResponse.getBody().asString().contains("has already been taken"),
                "Response should contain duplicate email validation message");

        client.deleteUserById(firstResponse.jsonPath().get("id"));
    }
}
