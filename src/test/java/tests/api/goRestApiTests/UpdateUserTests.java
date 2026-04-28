package tests.api.goRestApiTests;

import api.models.request.CreateGoRestUserRequest;
import api.models.response.GoRestUserResponse;
import api.models.update.UpdateGoRestUserRequest;
import api.specs.ApiResponseSpec;
import assertions.GoRestUserAssertions;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.Helpers.EmailGenerator;
import tests.builder.GoRestRequestBuilder;
import tests.builder.GoRestUpdateRequestBuilder;

public class UpdateUserTests extends GoRestUserCrudBaseTest {

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
        int createdUserId = userResponse.getId();
        trackCreatedUser(createdUserId);

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
}
