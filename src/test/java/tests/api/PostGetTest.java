package tests.api;

import api.clients.PostClient;
import api.models.response.PostResponse;
import api.specs.ApiResponseSpec;
import assertions.PostAssertions;
import io.restassured.response.Response;
import core.ApiBaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class PostGetTest extends ApiBaseTest {

    private PostClient postClient;

    @BeforeMethod(alwaysRun = true)
    public void initClient(){
        postClient = new PostClient(api);
    }

    @Test(groups = {"api"})
    public void shouldReturnPostByValidId(){

        int expectedPostId = 1;

        Response response = postClient.getPostById(expectedPostId);
        PostResponse postResponse = response.as(PostResponse.class);

        response.then().spec(ApiResponseSpec.statusCode200Js());

        PostAssertions.assertPostHasExpectedId(postResponse, expectedPostId);
        PostAssertions.assertPostHasValidRequiredFields(postResponse);
    }

    @Test(groups = {"api"})
    public void shouldReturnPostWithValidFields(){
        int expectedPostId = 2;

        Response response = postClient.getPostById(expectedPostId);
        PostResponse postResponse = response.as(PostResponse.class);

        response.then().spec(ApiResponseSpec.statusCode200Js());

        PostAssertions.assertPostHasExpectedId(postResponse, expectedPostId);
        PostAssertions.assertPostHasValidRequiredFields(postResponse);
    }

    @Test(groups = {"api"})
    public void shouldReturnPostCollections(){

        Response response = postClient.getAllPosts();
        List<PostResponse> posts = response.jsonPath().getList(".", PostResponse.class);

        response.then().spec(ApiResponseSpec.statusCode200Js());
        Assert.assertFalse(posts.isEmpty(), "Post collection should not be empty");
        Assert.assertTrue(posts.stream().findFirst().get().getId() > 0, "Post id should be greater than 0");
    }

    @Test(groups = {"api"})
    public void shouldReturnCode404AndNullBody(){
        int postId = 9999999;

        Response response = postClient.getPostById(postId);

        response.then().spec(ApiResponseSpec.statusCode404Js());

        Assert.assertTrue(response.jsonPath().getMap("$").isEmpty(), "404 body should be an empty JSON object");
    }
}
