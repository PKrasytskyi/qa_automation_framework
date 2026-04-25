package tests.builder;

import api.models.request.CreateGoRestUserRequest;
import tests.Helpers.EmailGenerator;

public class GoRestRequestBuilder {

    private String name = "Default name";
    private String email = EmailGenerator.uniqueEmail();
    private String gender = "male";
    private String status = "active";

    public GoRestRequestBuilder withValidName(String name){
        this.name = name;
        return this;
    }

    public GoRestRequestBuilder withUniqueEmail(String email){
        this.email = email;
        return this;
    }

    public GoRestRequestBuilder withGender(String gender){
        this.gender = gender;
        return this;
    }

    public GoRestRequestBuilder withStatus(String status){
        this.status = status;
        return this;
    }

    public CreateGoRestUserRequest build(){
        CreateGoRestUserRequest request = new CreateGoRestUserRequest();

            request.setName(name);
            request.setEmail(email);
            request.setGender(gender);
            request.setStatus(status);

        return request;
    }
}
