package tests.api;

import api.clients.PostClient;
import api.models.response.PostResponse;
import assertions.PostAssertions;
import core.ApiBaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
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

        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
        PostAssertions.assertPostHasExpectedId(postResponse, expectedPostId);
        PostAssertions.assertPostHasValidRequiredFields(postResponse);
    }
}
