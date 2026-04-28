package assertions;

import api.models.request.CreateGoRestUserRequest;
import api.models.response.GoRestUserResponse;
import api.models.update.UpdateGoRestUserRequest;
import org.testng.Assert;

public class GoRestUserAssertions {

    private GoRestUserAssertions(){}

    public static void assertCreatedUserMatchesRequest(GoRestUserResponse response, CreateGoRestUserRequest request){

        Assert.assertEquals(response.getName(), request.getName(), "Name should match");
        Assert.assertEquals(response.getEmail(), request.getEmail(), "Emails should match");
        Assert.assertEquals(response.getGender(), request.getGender(), "Gender should match");
        Assert.assertEquals(response.getStatus(), request.getStatus(), "Status should match");
    }

    public static void assertUserHasValidId(GoRestUserResponse response){

        Assert.assertTrue(response.getId() > 0);

    }

    public static void assertUpdatedUserMatchesRequest(GoRestUserResponse response, UpdateGoRestUserRequest request){
        Assert.assertEquals(response.getName(), request.getName(), "Name should match");
        Assert.assertEquals(response.getEmail(), request.getEmail(), "Emails should match");
        Assert.assertEquals(response.getGender(), request.getGender(), "Gender should match");
        Assert.assertEquals(response.getStatus(), request.getStatus(), "Status should match");
    }
}
