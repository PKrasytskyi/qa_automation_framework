package tests.api;

import api.clients.PostClient;
import api.models.request.CreatePostRequest;
import api.models.response.PostResponse;
import assertions.PostAssertions;
import core.ApiBaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PostCreateTest extends ApiBaseTest {

    private PostClient postClient;

    @BeforeMethod(alwaysRun = true)
    public void initClient(){
        postClient = new PostClient(api);
    }

    @Test(groups = {"api"})
    public void shouldCreatePostSuccessfully(){
        CreatePostRequest request = new CreatePostRequest();

        request.setUserId(20);
        request.setTitle("Test title 20");
        request.setBody("Test body 20");

        Response response = postClient.createPost(request);
        PostResponse postResponse = response.as(PostResponse.class);

        Assert.assertEquals(response.getStatusCode(), 201, "Status code should be 201");
        PostAssertions.assertCreatedPostMatchesRequest(postResponse, request);
    }

    @Test(groups = {"api"})
    public void shouldCreatePostWithId2Successfully(){
        CreatePostRequest request = new CreatePostRequest();

        request.setUserId(2);
        request.setTitle("Title of the user 2");
        request.setBody("Body of the user 2");

        Response response = postClient.createPost(request);
        PostResponse postResponse = response.as(PostResponse.class);

        Assert.assertEquals(response.getStatusCode(), 201, "Status code should be 201");
        PostAssertions.assertCreatedPostMatchesRequest(postResponse, request);
    }

    @Test(groups = {"api"})
    public void negativeTestWithEmptyTitle(){
        CreatePostRequest request = new CreatePostRequest();

        request.setUserId(2);
        request.setBody("Test body");

        Response response = postClient.createPost(request);
        PostResponse postResponse = response.as(PostResponse.class);

        Assert.assertEquals(response.getStatusCode(), 201, "Status code should be 201");
        Assert.assertEquals(postResponse.getBody(), request.getBody(), "Post body should match request");
        Assert.assertEquals(postResponse.getUserId(), request.getUserId(), "User id should match request");
        Assert.assertNull(postResponse.getTitle(), "Post title should be null");

    }
}
