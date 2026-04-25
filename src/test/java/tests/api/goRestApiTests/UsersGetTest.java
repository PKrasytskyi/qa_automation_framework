package tests.api.goRestApiTests;

import api.clients.GoRestUserClient;
import api.models.response.GoRestUserResponse;
import api.specs.ApiResponseSpec;
import core.ApiBaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class UsersGetTest extends ApiBaseTest {

    private GoRestUserClient client;

    @BeforeMethod(alwaysRun = true)
    public void initClient(){
        this.client = new GoRestUserClient(api);
    }

    @Test(groups = {"api"})
    public void shouldReturnAllUsers(){

        Response response = client.getUsers();
        List<GoRestUserResponse> users = response.jsonPath().getList("$", GoRestUserResponse.class);

        response.then().spec(ApiResponseSpec.statusCode200Js());


        Assert.assertTrue(users.stream().findFirst().get().getId() > 0, "User should have id greater than 0");
        Assert.assertFalse(response.jsonPath().getString("name").isBlank(), "User field 'name' should not be empty");
        Assert.assertFalse(response.jsonPath().getString("email").isBlank(), "Field 'email' should not be empty");
        Assert.assertFalse(response.jsonPath().getString("gender").isBlank(), "Field 'gender' should not be empty");
        Assert.assertFalse(response.jsonPath().getString("status").isBlank(), "Field 'status' should not be empty");

    }
}
