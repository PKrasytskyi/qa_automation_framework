package tests.api;

import api.clients.PostClient;
import api.models.request.CreatePostRequest;
import api.models.response.PostResponse;
import assertions.ApiAssertions;
import assertions.PostAssertions;
import core.ApiBaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.apiData.PostApiData;
import tests.apiData.PostApiDataWithPostBuilder;

public class PostCreateTestWithDataProviderTest extends ApiBaseTest {

    private PostClient postClient;

    @BeforeMethod(alwaysRun = true)
    public void initClient(){
        postClient = new PostClient(api);
    }

    @Test(dataProvider = "validCreatePayloads", dataProviderClass = PostApiData.class, groups = {"api"})
    public void shouldCreatePostSuccessfully(CreatePostRequest request){
        Response response = postClient.createPost(request);
        PostResponse postResponse = response.as(PostResponse.class);

        Assert.assertEquals(response.getStatusCode(), 201, "Status code should be 201");
        PostAssertions.assertCreatedPostMatchesRequest(postResponse, request);
    }

    @Test(dataProvider = "validPostDataWithBuilder", dataProviderClass = PostApiDataWithPostBuilder.class, groups = {"api"})
    public void shouldCreatePostSuccessfullyWithNewDataProvider(CreatePostRequest request){
        Response response = postClient.createPost(request);

        ApiAssertions.assertStatusCode(response, 201);

        PostResponse postResponse = response.as(PostResponse.class);
        PostAssertions.assertCreatedPostMatchesRequest(postResponse, request);

    }
}
