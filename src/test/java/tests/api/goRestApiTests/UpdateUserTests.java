package tests.api.goRestApiTests;

import api.clients.GoRestUserClient;
import api.models.request.CreateGoRestUserRequest;
import api.models.response.GoRestUserResponse;
import api.models.update.UpdateGoRestUserRequest;
import api.specs.ApiResponseSpec;
import assertions.GoRestUserAssertions;
import core.ApiBaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.Helpers.EmailGenerator;
import tests.builder.GoRestRequestBuilder;
import tests.builder.GoRestUpdateRequestBuilder;

public class UpdateUserTests extends ApiBaseTest {

    private GoRestUserClient client;
    private Integer createdUserId;

    @BeforeMethod(alwaysRun = true)
    public void initClient(){
        this.client = new GoRestUserClient(api);
        this.createdUserId = null;
    }

    @Test(groups = {"api-auth"})
    public void updateUserNameById(){
        CreateGoRestUserRequest request = new GoRestRequestBuilder()

                .withValidName("Taliaty")
                .withUniqueEmail(EmailGenerator.uniqueEmail())
                .withGender("female")
                .withStatus("inactive")
                .build();

        Response response = client.createUser(request);

        response.then().spec(ApiResponseSpec.statusCode201Js());
        GoRestUserResponse userResponse = response.as(GoRestUserResponse.class);

        createdUserId = userResponse.getId();

        UpdateGoRestUserRequest update = new GoRestUpdateRequestBuilder()

                .updateName("Kalli")
                .updateEmail(EmailGenerator.uniqueEmail())
                .updateGender("male")
                .updateStatus("active")
                .build();

        Response response1 = client.updateUserById(createdUserId, update);
        response1.then().spec(ApiResponseSpec.statusCode200Js());

        Response response2 = client.getUserById(createdUserId);
        response2.then().spec(ApiResponseSpec.statusCode200Js());

        GoRestUserResponse userResponse1 = response2.as(GoRestUserResponse.class);

        Assert.assertEquals(createdUserId, userResponse1.getId());
        GoRestUserAssertions.assertUpdatedUserMatchesRequest(userResponse1, update);
    }

    @AfterMethod(alwaysRun = true)
    public void deleteClient(){
        if(createdUserId != null){
            client.deleteUserById(createdUserId);
            createdUserId = null;
        }
    }
}
