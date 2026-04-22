package api.clients;

import api.models.request.CreatePostRequest;
import api.models.response.PostResponse;
import core.ApiManager;
import io.restassured.response.Response;

public class PostClient {

    private static final String POST_ENDPOINT = "/posts";

    private final ApiManager apiManager;

    public PostClient(ApiManager apiManager) {
        this.apiManager = apiManager;
    }

    public Response getAllPosts(){
        return apiManager.newRequest()
                .when()
                .get(POST_ENDPOINT);
    }

    public Response getPostById(int postId){
        return apiManager.newRequest()
                .pathParam("id", postId)
                .when()
                .get(POST_ENDPOINT + "/{id}");
    }

    public Response createPost(CreatePostRequest requestBody){
        return apiManager.newRequest()
                .body(requestBody)
                .when()
                .post(POST_ENDPOINT);
    }

    public PostResponse getResponsePostIdAsModel(int postId){
        return getPostById(postId).as(PostResponse.class);
    }
}
