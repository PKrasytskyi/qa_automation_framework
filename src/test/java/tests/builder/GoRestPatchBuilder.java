package tests.builder;

import api.models.request.PatchGoRestUserRequest;

public class GoRestPatchBuilder {

    private String name;
    private String email;
    private String gender;
    private String status;

    public GoRestPatchBuilder withName(String name){
        this.name = name;
        return this;
    }

    public GoRestPatchBuilder withEmail(String email){
        this.email = email;
        return this;
    }

    public GoRestPatchBuilder withGender(String gender){
        this.gender = gender;
        return this;
    }

    public GoRestPatchBuilder withStatus(String status){
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
