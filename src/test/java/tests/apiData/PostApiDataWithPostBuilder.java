package tests.apiData;

import api.models.request.CreatePostRequest;
import org.testng.annotations.DataProvider;
import tests.builder.PostRequestBuilder;

public class PostApiDataWithPostBuilder {

    @DataProvider(name = "validPostDataWithBuilder")
    public Object[][] validPostDataWithBuilder(){
        CreatePostRequest request = new PostRequestBuilder()
                .withUserId(11)
                .withTitle("Post title user id 11")
                .withBody("Post body user id 11")
                .build();

        CreatePostRequest request1 = new PostRequestBuilder()
                .withUserId(24)
                .withTitle("Post title user id 24")
                .withBody("Post body user id 24")
                .build();

        return new Object[][]{
            {request},
            {request1}
        };
    }
}
