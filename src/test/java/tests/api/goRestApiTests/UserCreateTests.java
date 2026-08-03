package tests.api.goRestApiTests;

import api.models.request.CreateGoRestUserRequest;
import api.models.response.GoRestUserResponse;
import api.specs.ApiResponseSpec;
import assertions.GoRestUserAssertions;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import tests.Helpers.EmailGenerator;
import tests.apiData.UserCreateBuildBasedGoRest;
import tests.apiData.UsersCreateGoRestData;

public class UserCreateTests extends GoRestUserCrudBaseTest {

    @Test(groups = {"api-auth"})
    public void shouldCreateUserWithValidData(){

        CreateGoRestUserRequest request = new CreateGoRestUserRequest();

        request.setName("Test1");
        request.setEmail(EmailGenerator.uniqueEmail());
        request.setGender("female");
        request.setStatus("inactive");

        Response response = client.createUser(request);

        response.then().spec(ApiResponseSpec.statusCode201Js());
        GoRestUserResponse userResponse = response.as(GoRestUserResponse.class);
        trackCreatedUser(userResponse.getId());

        GoRestUserAssertions.assertCreatedUserMatchesRequest(userResponse, request);
        GoRestUserAssertions.assertUserHasValidId(userResponse);

    }

    @Test(dataProvider = "usersWithValidData", dataProviderClass = UsersCreateGoRestData.class,groups = {"api-auth"})
    public void shouldCreateUserWithValidDataFromDataPr(CreateGoRestUserRequest request){

        Response response = client.createUser(request);
        response.then().spec(ApiResponseSpec.statusCode201Js());

        GoRestUserResponse userResponse = response.as(GoRestUserResponse.class);
        trackCreatedUser(userResponse.getId());

        GoRestUserAssertions.assertCreatedUserMatchesRequest(userResponse, request);
        GoRestUserAssertions.assertUserHasValidId(userResponse);

    }

    @Test(dataProvider = "usersCreateBuildBased", dataProviderClass = UserCreateBuildBasedGoRest.class, groups = {"api-auth"})
    public void shouldCreateUsersSuccessfully(CreateGoRestUserRequest request){

        Response response = client.createUser(request);

        response.then().spec(ApiResponseSpec.statusCode201Js());

        GoRestUserResponse userResponse = response.as(GoRestUserResponse.class);
        trackCreatedUser(userResponse.getId());

        GoRestUserAssertions.assertCreatedUserMatchesRequest(userResponse, request);
        GoRestUserAssertions.assertUserHasValidId(userResponse);
    }
}
