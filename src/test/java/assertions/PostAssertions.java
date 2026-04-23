package assertions;

import api.models.request.CreatePostRequest;
import api.models.response.PostResponse;
import org.testng.Assert;

public class PostAssertions {

    private PostAssertions(){
    }

    public static void assertPostHasExpectedId(PostResponse response, int expectedId){

        Assert.assertEquals(response.getId(), expectedId, "Post id should match");
    }

    public static void assertPostHasValidRequiredFields(PostResponse response){

        Assert.assertTrue(response.getUserId() > 0, "User id should be greater than 0");
        Assert.assertFalse(response.getTitle().isBlank(), "Post title should not be blank");
        Assert.assertFalse(response.getBody().isBlank(), "Post body should not be blank");
    }

    public static void assertCreatedPostMatchesRequest(PostResponse response, CreatePostRequest request){

        Assert.assertEquals(response.getUserId(), request.getUserId(), "User id should match");
        Assert.assertEquals(response.getBody(), request.getBody(), "Post body should match");
        Assert.assertEquals(response.getTitle(), request.getTitle(), "Post title should match");
        Assert.assertTrue(response.getId() > 0, "Created post id should be positive");
    }
}
