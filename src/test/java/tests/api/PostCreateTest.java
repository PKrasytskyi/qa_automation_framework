package tests.api;

import api.clients.PostClient;
import api.models.request.CreatePostRequest;
import api.models.response.PostResponse;
import api.specs.ApiResponseSpec;
import assertions.PostAssertions;
import core.ApiBaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.builder.PostRequestBuilder;

public class PostCreateTest extends ApiBaseTest {

    private PostClient postClient;

    @BeforeMethod(alwaysRun = true)
    public void initClient(){
        postClient = new PostClient(api);
    }

    @Test(groups = {"api"})
    public void shouldCreatePostSuccessfully(){
        CreatePostRequest request = new PostRequestBuilder()
                .withUserId(20)
                        .withTitle("Test title 20")
                                .withBody("Test body 20")
                                        .build();

        Response response = postClient.createPost(request);
        PostResponse postResponse = response.as(PostResponse.class);

        response.then().spec(ApiResponseSpec.statusCode201Js());

        PostAssertions.assertCreatedPostMatchesRequest(postResponse, request);
    }

    @Test(groups = {"api"})
    public void shouldCreatePostWithId2Successfully(){
        CreatePostRequest request = new PostRequestBuilder()
                .withUserId(2)
                        .withTitle("Title of the user 2")
                                .withBody("Body of the user 2")
                                        .build();

        Response response = postClient.createPost(request);
        PostResponse postResponse = response.as(PostResponse.class);

        response.then().spec(ApiResponseSpec.statusCode201Js());

        PostAssertions.assertCreatedPostMatchesRequest(postResponse, request);
    }

    @Test(groups = {"api"})
    public void negativeTestWithEmptyTitle(){
        CreatePostRequest request = new CreatePostRequest();

        request.setUserId(2);
        request.setBody("Test body");

        Response response = postClient.createPost(request);
        PostResponse postResponse = response.as(PostResponse.class);

        response.then().spec(ApiResponseSpec.statusCode201Js());
        Assert.assertEquals(postResponse.getBody(), request.getBody(), "Post body should match request");
        Assert.assertEquals(postResponse.getUserId(), request.getUserId(), "User id should match request");
        Assert.assertNull(postResponse.getTitle(), "Post title should be null");

    }
}
