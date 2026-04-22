package tests.api;

import api.clients.PostClient;
import api.models.request.CreatePostRequest;
import api.models.response.PostResponse;
import core.ApiBaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.apiData.PostApiData;

public class PostCreateTestWithDataProviderTest extends ApiBaseTest {

    private PostClient postClient;

    @BeforeMethod(alwaysRun = true)
    public void initClient(){
        postClient = new PostClient(api);
    }

    @Test(dataProvider = "validCreatePayloads", dataProviderClass = PostApiData.class, groups = {"api"})
    public void shouldCreatePostSuccessfully(CreatePostRequest createPostRequest){
        Response response = postClient.createPost(createPostRequest);
        PostResponse postResponse = response.as(PostResponse.class);

        Assert.assertEquals(response.getStatusCode(), 201, "Status code should be 201");
        Assert.assertEquals(postResponse.getUserId(), createPostRequest.getUserId(), "User id should match");
        Assert.assertEquals(postResponse.getTitle(), createPostRequest.getTitle(), "Post title should match");
        Assert.assertEquals(postResponse.getBody(), createPostRequest.getBody(), "Post body should match");
    }
}
