package tests.ui;

import core.BaseTest;
import core.DriverFactory;
import tests.data.UserLoginData;

import org.testng.Assert;
import org.testng.annotations.Test;

public class UserLoginTest extends BaseTest {

    private static final String SUCCESS_LOGIN_MESSAGE = "You logged into a secure area!";
    private static final String ERROR_USERPASSWORD_MESSAGE = "Your password is invalid!";
    private static final String SUCCESS_LOGOUT_MESSAGE = "You logged out of the secure area!";
    private static final String ERROR_USERNAME_MESSAGE = "Your username is invalid!";
    private static final String DIRECT_SECUREAREA_LINK = "https://the-internet.herokuapp.com/secure";
    private static final String DIRECT_SECUREAREA_ERROR_MESSAGE = "You must login to view the secure area!";

    private void login(String name, String pass) {
        getPages().getMainPage().openFormAuthentication();
        getPages().getLoginPage().login(name, pass);
    }


    @Test(dataProvider = "validUser", dataProviderClass = UserLoginData.class, groups = {"ui", "smoke"})
    public void shouldLoginWithValidCredentials(String name, String pass) {

        login(name, pass);

        String actualResult = getPages().getSecureAreaPage().getFlashText();
        Assert.assertTrue(getPages().getSecureAreaPage().isPageOpened());
        Assert.assertTrue(actualResult.contains(SUCCESS_LOGIN_MESSAGE), "Expected success message not found");

    }

    @Test(dataProvider = "invalidUser", dataProviderClass = UserLoginData.class, groups = {"ui", "smoke"})
    public void shouldShowErrorWithInvalidPassword(String name, String pass) {

        login(name, pass);

        String actualResult = getPages().getLoginPage().getFlashMessage();
        Assert.assertTrue(actualResult.contains(ERROR_USERPASSWORD_MESSAGE), "Expected user password error message not found");
    }

    @Test(dataProvider = "validUser", dataProviderClass = UserLoginData.class, groups = {"ui", "smoke"})
    public void shouldLogoutSuccessfully(String name, String pass) {

        login(name, pass);
        getPages().getSecureAreaPage().clickLogoutButton();
        getPages().getLoginPage().isUsernameFieldVisible();
        getPages().getLoginPage().isPageOpened();

        String actualResult = getPages().getLoginPage().getFlashMessage();
        Assert.assertTrue(getPages().getLoginPage().isPageOpened(), "Login page was not opened");
        Assert.assertTrue(getPages().getLoginPage().isUsernameFieldVisible(), "Username field is not visible");
        Assert.assertTrue(actualResult.contains(SUCCESS_LOGOUT_MESSAGE), "Expected success logout message not found");
    }

    @Test(dataProvider = "invalidUsername", dataProviderClass = UserLoginData.class, groups = {"ui", "smoke"})
    public void shouldShowErrorWithInvalidUserName(String name, String pass) {

        login(name, pass);
        String actualResult = getPages().getLoginPage().getFlashMessage();
        Assert.assertTrue(actualResult.contains(ERROR_USERNAME_MESSAGE), "Expected username error message not found");
    }

    @Test(dataProvider = "validUser", dataProviderClass = UserLoginData.class, groups = {"ui", "smoke"})
    public void shouldKeepSessionAfterRefresh(String name, String pass) {

        login(name, pass);
        DriverFactory.getDriver().navigate().refresh();
        Assert.assertTrue(getPages().getSecureAreaPage().isLogoutButtonVisible(), "User is not logged");
        Assert.assertTrue(getPages().getSecureAreaPage().isPageOpened(), "Secure page is not opened after refresh");
    }

    @Test(groups = {"ui", "smoke"})
    public void shouldRedirectToLoginWhenAccessingSecureAreaDirectly() {

        DriverFactory.getDriver().get(DIRECT_SECUREAREA_LINK);

        String actualResult = getPages().getLoginPage().getFlashMessage();

        Assert.assertTrue(getPages().getLoginPage().isPageOpened(), "Login page not opened when accessing secure URL directly");
        Assert.assertTrue(actualResult.contains(DIRECT_SECUREAREA_ERROR_MESSAGE), "Expected secure area error message not found");
    }
}
