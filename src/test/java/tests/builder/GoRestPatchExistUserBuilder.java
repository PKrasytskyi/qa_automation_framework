package tests.builder;

import api.models.request.PatchGoRestUserRequest;
import api.models.response.GoRestUserResponse;

public class GoRestPatchExistUserBuilder {

    private String name;
    private String email;
    private String gender;
    private String status;

    public GoRestPatchExistUserBuilder fromUser(GoRestUserResponse user){
        this.name = user.getName();
        this.email = user.getEmail();
        this.gender = user.getGender();
        this.status = user.getStatus();
        return this;
    }

    public GoRestPatchExistUserBuilder withName(String name){
        this.name = name;
        return this;
    }

    public GoRestPatchExistUserBuilder withEmail(String email){
        this.email = email;
        return this;
    }

    public GoRestPatchExistUserBuilder withGender(String gender){
        this.gender = gender;
        return this;
    }

    public GoRestPatchExistUserBuilder withStatus(String status){
        this.status = status;
        return this;
    }

    public PatchGoRestUserRequest build(){
        PatchGoRestUserRequest request = new PatchGoRestUserRequest();
        request.setName(name);
        request.setEmail(email);
        request.setGender(gender);
        request.setStatus(status);

        return request;
    }
}
