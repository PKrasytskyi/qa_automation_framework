package tests.api.goRestApiTests;

import api.clients.GoRestUserClient;
import api.models.request.CreateGoRestUserRequest;
import api.models.response.GoRestUserResponse;
import api.specs.ApiResponseSpec;
import assertions.GoRestUserAssertions;
import core.ApiBaseTest;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.Helpers.EmailGenerator;
import tests.apiData.UserCreateBuildBasedGoRest;
import tests.apiData.UsersCreateGoRestData;

public class UserCreateTests extends ApiBaseTest {

    private GoRestUserClient client;

    @BeforeMethod(alwaysRun = true)
    public void initClient(){
        this.client = new GoRestUserClient(api);
    }

    @Test(groups = {"api"})
    public void shouldCreateUserWithValidData(){

        CreateGoRestUserRequest request = new CreateGoRestUserRequest();

        request.setName("Test1");
        request.setEmail(EmailGenerator.uniqueEmail());
        request.setGender("female");
        request.setStatus("inactive");

        Response response = client.createUser(request);

        response.then().spec(ApiResponseSpec.statusCode201Js());
        GoRestUserResponse userResponse = response.as(GoRestUserResponse.class);

        GoRestUserAssertions.assertCreatedUserMatchesRequest(userResponse, request);
        GoRestUserAssertions.assertUserHasValidId(userResponse);

    }

    @Test(dataProvider = "usersWithValidData", dataProviderClass = UsersCreateGoRestData.class,groups = {"api"})
    public void shouldCreateUserWithValidDataFromDataPr(CreateGoRestUserRequest request){

        Response response = client.createUser(request);
        response.then().spec(ApiResponseSpec.statusCode201Js());

        GoRestUserResponse userResponse = response.as(GoRestUserResponse.class);

        GoRestUserAssertions.assertCreatedUserMatchesRequest(userResponse, request);
        GoRestUserAssertions.assertUserHasValidId(userResponse);

    }

    @Test(dataProvider = "usersCreateBuildBased", dataProviderClass = UserCreateBuildBasedGoRest.class, groups = {"api"})
    public void shouldCreateUsersSuccessfully(CreateGoRestUserRequest request){

        Response response = client.createUser(request);

        response.then().spec(ApiResponseSpec.statusCode201Js());

        GoRestUserResponse userResponse = response.as(GoRestUserResponse.class);

        GoRestUserAssertions.assertCreatedUserMatchesRequest(userResponse, request);
        GoRestUserAssertions.assertUserHasValidId(userResponse);

        client.deleteUserById(userResponse.getId());

    }
}
