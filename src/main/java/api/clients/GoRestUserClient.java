package api.clients;

import api.models.request.CreateGoRestUserRequest;
import core.ApiManager;
import io.restassured.response.Response;

public class GoRestUserClient {

    private static final String USERS_ENDPOINT = "/public/v2/users";

    private final ApiManager apiManager;

    public GoRestUserClient(ApiManager apiManager){
        this.apiManager = apiManager;
    }

    public Response getUsers(){
        return apiManager.newAuthorizedRequest()
                .when()
                .get(USERS_ENDPOINT);
    }

    public Response createUser(CreateGoRestUserRequest bodyRequest){
        return apiManager.newAuthorizedRequest()
                .body(bodyRequest)
                .when()
                .post(USERS_ENDPOINT);
    }

    public Response createUserWithoutAuthorization(CreateGoRestUserRequest bodyRequest) {
        return apiManager.newRequest()
                .body(bodyRequest)
                .when()
                .post(USERS_ENDPOINT);
    }

    public Response deleteUserById(int id){
        return apiManager.newAuthorizedRequest()
                .when()
                .delete(USERS_ENDPOINT + "/" + id);
    }
}
