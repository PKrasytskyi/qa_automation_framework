package tests.api;

import api.clients.PostClient;
import io.restassured.response.Response;
import core.ApiBaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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

        Assert.assertEquals(response.statusCode(), 200, "Status code should be 200");
        Assert.assertEquals(response.jsonPath().getInt("id"), expectedPostId, "Post id should match");
    }
}
