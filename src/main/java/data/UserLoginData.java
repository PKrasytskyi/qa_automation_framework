package data;

import org.testng.annotations.DataProvider;

public class UserLoginData {

    @DataProvider(name = "validUserData")
    public Object[][] validData(){
        return new Object[][]{
                {"tomsmith", "SuperSecretPassword!"}
        };
    }

    @DataProvider(name = "invalidUserData")
    public Object[][] invalidData(){
        return new Object[][]{
                {"tomsmith", "wrongSuperSecretPassword!"}
        };
    }

    @DataProvider(name = "emptyLoginPass")
    public Object[][] emptyLoginPass(){
        return new Object[][]{
                    {"", ""}
        };
    }
}
