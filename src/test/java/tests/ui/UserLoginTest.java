package tests.ui;

import core.BaseTest;
import data.UserLoginData;

import org.testng.Assert;
import org.testng.annotations.Test;
import utils.WaitUtils;

public class UserLoginTest extends BaseTest {

    private static final String SUCCESS_LOGIN_MESSAGE = "You logged into a secure area!";
    private static final String ERROR_USERPASSWORD_MESSAGE = "Your password is invalid!";
    private static final String SUCCESS_LOGOUT_MESSAGE = "You logged out of the secure area!";
    private static final String ERROR_USERNAME_MESSAGE = "Your username is invalid!";
    private static final String DIRECT_SECUREAREA_LINK = "https://the-internet.herokuapp.com/secure";
    private static final String DIRECT_SECUREAREA_ERROR_MESSAGE = "You must login to view the secure area!";

    private void login(String name, String pass){
        getMainPage().clickFormAuthentication();
        getLoginPage().login(name, pass);
    }


    @Test(dataProvider = "validUserData", dataProviderClass = UserLoginData.class)
    public void shouldLoginWithValidCredentials(String name,String pass){

        login(name, pass);

        String actualResult = getSecureAreaPage().getFlashText();
        Assert.assertTrue(getSecureAreaPage().isPageOpened());
        Assert.assertTrue(actualResult.contains(SUCCESS_LOGIN_MESSAGE), "Expected success message not found");

    }

    @Test(dataProvider = "invalidUserData", dataProviderClass = UserLoginData.class)
    public void shouldShowErrorWithInvalidPassword(String name, String pass){

        login(name, pass);

        String actualResult = getLoginPage().getFlashText();
        Assert.assertTrue(actualResult.contains(ERROR_USERPASSWORD_MESSAGE), "Expected user password error message not found");
    }

    @Test(dataProvider = "validUserData", dataProviderClass = UserLoginData.class)
    public void shouldLogoutSuccessfully(String name, String pass) {

        login(name, pass);
        getSecureAreaPage().clickLogoutButton();
        WaitUtils.waitForUrlContains(driver, "/login");

        String actualResult = getLoginPage().getFlashText();
        Assert.assertTrue(actualResult.contains(SUCCESS_LOGOUT_MESSAGE), "Expected success logout message not found");
    }

    @Test(dataProvider = "invalidUsernameData", dataProviderClass = UserLoginData.class)
    public void shouldShowErrorWithInvalidUserName(String name, String pass){

        login(name, pass);
        String actualResult = getLoginPage().getFlashText();
        Assert.assertTrue(actualResult.contains(ERROR_USERNAME_MESSAGE), "Expected username error message not found");
    }

    @Test(dataProvider = "validUserData", dataProviderClass = UserLoginData.class)
    public void shouldKeepSessionAfterRefresh(String name, String pass){

        login(name, pass);
        driver.navigate().refresh();
        Assert.assertTrue(getSecureAreaPage().isLogoutButtonVisible(), "User is not logged");
        Assert.assertTrue(secureAreaPage.isPageOpened(), "Secure page is not opened after refresh");
    }

    @Test
    public void shouldRedirectToLoginWhenAccessingSecureAreaDirectly(){

        driver.get(DIRECT_SECUREAREA_LINK);

        String actualResult = getLoginPage().getFlashText();

        Assert.assertTrue(getLoginPage().isPageOpened(), "Login page not opened when accessing secure URL directly");
        Assert.assertTrue(actualResult.contains(DIRECT_SECUREAREA_ERROR_MESSAGE), "Expected secure area error message not found");
    }

}
