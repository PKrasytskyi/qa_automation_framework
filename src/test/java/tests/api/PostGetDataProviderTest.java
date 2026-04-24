package tests.api;

import api.clients.PostClient;
import api.models.response.PostResponse;
import api.specs.ApiResponseSpec;
import assertions.PostAssertions;
import core.ApiBaseTest;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.apiData.PostApiData;

public class PostGetDataProviderTest extends ApiBaseTest {

    private PostClient postClient;

    @BeforeMethod(alwaysRun = true)
    public void initClient(){
        postClient = new PostClient(api);
    }

    @Test(dataProvider = "validPostId", dataProviderClass = PostApiData.class, groups = {"api"})
    public void shouldReturnPostByValidId(int expectedPostId){
        Response response = postClient.getPostById(expectedPostId);
        PostResponse postResponse = response.as(PostResponse.class);

        response.then().spec(ApiResponseSpec.statusCode200Js());

        PostAssertions.assertPostHasExpectedId(postResponse, expectedPostId);
        PostAssertions.assertPostHasValidRequiredFields(postResponse);
    }
}
