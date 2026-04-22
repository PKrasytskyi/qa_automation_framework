package tests.apiData;

import api.models.request.CreatePostRequest;
import org.testng.annotations.DataProvider;

public class PostApiData {

    @DataProvider(name = "validCreatePayloads")
    public Object[][] validCreatePayloads(){
        CreatePostRequest request = new CreatePostRequest();

        request.setUserId(4);
        request.setBody("Post test body userId4");
        request.setTitle("Post test title userId4");

        CreatePostRequest request1 = new CreatePostRequest();

        request1.setUserId(3);
        request1.setBody("Post test body userId3");
        request1.setTitle("Post test title userId3");

        CreatePostRequest request2 = new CreatePostRequest();

        request2.setUserId(11);
        request2.setBody("Post test body userId11");
        request2.setTitle("Post test title userId11");

        return new Object[][]{
                {request},
                {request1},
                {request2}
        };

    }

    @DataProvider(name = "validPostId")
    public Object[][] validPostId(){
        return new Object[][]{
                {1},
                {2},
                {4}
        };
    }
}
