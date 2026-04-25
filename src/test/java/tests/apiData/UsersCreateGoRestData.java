package tests.apiData;

import api.models.request.CreateGoRestUserRequest;
import org.testng.annotations.DataProvider;
import tests.Helpers.EmailGenerator;

public class UsersCreateGoRestData {

    @DataProvider(name = "usersWithValidData")
    public Object[][] usersWithValidData(){
        CreateGoRestUserRequest request = new CreateGoRestUserRequest();

        request.setName("userTest22");
        request.setEmail(EmailGenerator.uniqueEmail());
        request.setGender("female");
        request.setStatus("inactive");

        CreateGoRestUserRequest request1 = new CreateGoRestUserRequest();

        request1.setName("userTest33");
        request1.setEmail(EmailGenerator.uniqueEmail());
        request1.setGender("male");
        request1.setStatus("active");

        CreateGoRestUserRequest request2 = new CreateGoRestUserRequest();

        request2.setName("krabKrabich");
        request2.setEmail(EmailGenerator.uniqueEmail());
        request2.setGender("female");
        request2.setStatus("active");

        return new Object[][]{
            {request},
            {request1},
            {request2}
        };
    }
}
