package tests.apiData;

import api.models.request.CreateGoRestUserRequest;
import org.testng.annotations.DataProvider;
import tests.Helpers.EmailGenerator;
import tests.builder.GoRestRequestBuilder;

public class UserCreateBuildBasedGoRest {

    @DataProvider(name = "usersCreateBuildBased")
    public Object[][] usersCreateBuildBased(){
        CreateGoRestUserRequest request = new GoRestRequestBuilder()
                .withValidName("userShouldBeDeleted")
                .withUniqueEmail(EmailGenerator.uniqueEmail())
                .withGender("male")
                .withStatus("active")
                .build();

        CreateGoRestUserRequest request1 = new GoRestRequestBuilder()
                .withValidName("userShouldBeDeleted3")
                .withUniqueEmail(EmailGenerator.uniqueEmail())
                .withGender("female")
                .withStatus("inactive")
                .build();

        return new Object[][]{
                 {request},
                {request1}
        };
    }
}
