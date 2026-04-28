package tests.builder;

import api.models.update.UpdateGoRestUserRequest;
import tests.Helpers.EmailGenerator;

public class GoRestUpdateRequestBuilder {

    private String name = "Default name";
    private String email = EmailGenerator.uniqueEmail();
    private String gender = "male";
    private String status = "active";

    public GoRestUpdateRequestBuilder updateName(String name){
        this.name = name;
        return this;
    }

    public GoRestUpdateRequestBuilder updateEmail(String email){
        this.email = email;
        return this;
    }

    public GoRestUpdateRequestBuilder updateGender(String gender){
        this.gender = gender;
        return this;
    }

    public GoRestUpdateRequestBuilder updateStatus(String status){
        this.status = status;
        return this;
    }

    public UpdateGoRestUserRequest build(){
        UpdateGoRestUserRequest update = new UpdateGoRestUserRequest();

        update.setName(name);
        update.setEmail(email);
        update.setGender(gender);
        update.setStatus(status);

        return update;
    }
}
