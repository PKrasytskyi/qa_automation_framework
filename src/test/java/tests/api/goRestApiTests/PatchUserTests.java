package tests.api.goRestApiTests;

import api.models.request.CreateGoRestUserRequest;
import api.models.request.PatchGoRestUserRequest;
import api.models.response.GoRestUserResponse;
import api.specs.ApiResponseSpec;
import assertions.GoRestUserAssertions;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.Helpers.EmailGenerator;
import tests.builder.GoRestPatchBuilder;
import tests.builder.GoRestPatchExistUserBuilder;
import tests.builder.GoRestRequestBuilder;

public class PatchUserTests extends GoRestUserCrudBaseTest {

    @Test(groups = {"api-auth"})
    public void shouldReturnStatusCode422(){
        CreateGoRestUserRequest request = new GoRestRequestBuilder()
                .withValidName("Toni")
                .withUniqueEmail(EmailGenerator.uniqueEmail())
                .withGender("male")
                .withStatus("inactive")
                .build();

        Response response = client.createUser(request);
        response.then().spec(ApiResponseSpec.statusCode201Js());

        GoRestUserResponse userResponse = response.as(GoRestUserResponse.class);
        int createdUserId = userResponse.getId();
        trackCreatedUser(createdUserId);

        PatchGoRestUserRequest patch = new GoRestPatchBuilder()
                .withName("Forni")
                .build();

        Response response1 = client.patchUserById(createdUserId, patch);
        response1.then().spec(ApiResponseSpec.statusCode422ForBlankPatchFields());


    }

    @Test(groups = {"api-auth"})
    public void shouldPatchUserStatusByUserId(){
        CreateGoRestUserRequest request = new GoRestRequestBuilder()
                .withValidName("Loly")
                .withUniqueEmail(EmailGenerator.uniqueEmail())
                .withGender("female")
                .withStatus("inactive")
                .build();

        Response response = client.createUser(request);
        response.then().spec(ApiResponseSpec.statusCode201Js());

        GoRestUserResponse userResponse = response.as(GoRestUserResponse.class);
        GoRestUserAssertions.assertCreatedUserMatchesRequest(userResponse, request);
        int createdUserId = userResponse.getId();
        trackCreatedUser(createdUserId);

        PatchGoRestUserRequest patch = new GoRestPatchExistUserBuilder()
                .fromUser(userResponse)
                .withStatus("active")
                .build();

        Response response1 = client.patchUserById(createdUserId, patch);
        response1.then().spec(ApiResponseSpec.statusCode200Js());

        Response response2 = client.getUserById(createdUserId);
        response2.then().spec(ApiResponseSpec.statusCode200Js());

        GoRestUserResponse userResponse1 = response2.as(GoRestUserResponse.class);

        Assert.assertEquals(userResponse1.getId(), createdUserId);
        GoRestUserAssertions.assertPatchedUserMatchesRequest(userResponse1, patch);
    }
}
