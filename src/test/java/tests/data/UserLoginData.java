package tests.data;

import org.testng.annotations.DataProvider;

public class UserLoginData {

    @DataProvider(name = "validUser")
    public Object[][] validData() {
        return new Object[][]{
                {"tomsmith", "SuperSecretPassword!"}
        };
    }

    @DataProvider(name = "invalidUser")
    public Object[][] invalidData() {
        return new Object[][]{
                {"tomsmith", "wrongSuperSecretPassword!"}
        };
    }

    @DataProvider(name = "invalidUsername")
    public Object[][] invalidUsernameData() {
        return new Object[][]{
                {"wrong-user", "SuperSecretPassword!"}
        };
    }
}
