package api.clients;

import api.models.request.CreateGoRestUserRequest;
import api.models.request.PatchGoRestUserRequest;
import api.models.update.UpdateGoRestUserRequest;
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
        return apiManager.newUnAuthorizedRequest()
                .header("Authorization", "")
                .body(bodyRequest)
                .when()
                .post(USERS_ENDPOINT);
    }

    public Response updateUserById(int id, UpdateGoRestUserRequest updateRequest){
        return apiManager.newAuthorizedRequest()
                .body(updateRequest)
                .when()
                .put(USERS_ENDPOINT + "/" + id);
    }

    public Response patchUserById(int id, PatchGoRestUserRequest patchRequest){
        return  apiManager.newAuthorizedRequest()
                .body(patchRequest)
                .when()
                .patch(USERS_ENDPOINT + "/" + id);
    }

    public Response getUserById(int id){
        return apiManager.newAuthorizedRequest()
                .pathParam("id", id)
                .when()
                .get(USERS_ENDPOINT + "/{id}");
    }

    public Response deleteUserById(int id){
        return apiManager.newAuthorizedRequest()
                .when()
                .delete(USERS_ENDPOINT + "/" + id);
    }
}
