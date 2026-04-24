package tests.builder;

import api.models.request.CreatePostRequest;

public class PostRequestBuilder {

    private int userId = 1;
    private String title = "Default test title";
    private String body = "Default test body";

    public PostRequestBuilder withUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public PostRequestBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public PostRequestBuilder withBody(String body) {
        this.body = body;
        return this;
    }

    public CreatePostRequest build(){
        CreatePostRequest request = new CreatePostRequest();
            request.setUserId(userId);
            request.setTitle(title);
            request.setBody(body);

            return  request;
    }
}
