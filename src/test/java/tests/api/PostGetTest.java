package tests.api;

import api.clients.PostClient;
import api.models.response.PostResponse;
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

        Assert.assertEquals(response.statusCode(), 200, "Status code should be 200");
        Assert.assertEquals(postResponse.getId(), expectedPostId, "Post id should match");
        Assert.assertFalse(postResponse.getTitle().isBlank(),"Post title should not be empty");
    }

    @Test(groups = {"api"})
    public void shouldReturnPostWithValidFields(){
        int expectedPostId = 2;

        Response response = postClient.getPostById(expectedPostId);
        PostResponse postResponse = response.as(PostResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
        Assert.assertTrue(postResponse.getUserId() > 0, "User id should be greater than 0");
        Assert.assertEquals(postResponse.getId(), expectedPostId, "Post id should match");
        Assert.assertFalse(postResponse.getBody().isBlank(),  "Post body should not be empty");
    }

    @Test(groups = {"api"})
    public void shouldReturnPostCollections(){

        Response response = postClient.getAllPosts();
        List<PostResponse> posts = response.jsonPath().getList(".", PostResponse.class);

        Assert.assertEquals(response.statusCode(), 200, "Status code should be 200");
        Assert.assertFalse(posts.isEmpty(), "Post collection should not be empty");
        Assert.assertTrue(posts.stream().findFirst().get().getId() > 0, "Post id should be greater than 0");
    }

    @Test(groups = {"api"})
    public void shouldReturnCode404AndNullBody(){
        int postId = 9999999;

        Response response = postClient.getPostById(postId);
        PostResponse postResponse = response.as(PostResponse.class);

        Assert.assertEquals(response.getStatusCode(), 404, "Status code should be 404");
        Assert.assertNull(postResponse.getBody(), "Post body should be null");

    }
}
