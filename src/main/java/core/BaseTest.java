package core;

import config.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.*;



@Listeners({listeners.TestListener.class, reporting.AllureListener.class})
public abstract class BaseTest {

    protected WebDriver driver;
    protected MainPage mainPage;
    protected LoginPage loginPage;
    protected SecureAreaPage secureAreaPage;
    protected CheckBoxPage checkBoxPage;
    protected InputsPage inputsPage;
    protected DropDownPage dropDownPage;
    protected AddRemoveElementsPage addRemoveElementsPage;

    private void resetPages(){
        mainPage = null;
        loginPage = null;
        secureAreaPage = null;
        checkBoxPage = null;
        inputsPage = null;
        dropDownPage = null;
        addRemoveElementsPage = null;
    }

    @BeforeMethod
    public void setUp(){

        driver = DriverFactory.InitDriver();
        driver.get(ConfigReader.getBaseUrl());
        resetPages();

    }

    protected MainPage getMainPage(){
        if(mainPage == null) {
            mainPage = new MainPage(driver);
        }
        return mainPage;
    }

    protected LoginPage getLoginPage(){
        if(loginPage == null){
            loginPage = new LoginPage(driver);
        }
        return loginPage;
    }

    protected SecureAreaPage getSecureAreaPage(){
        if(secureAreaPage == null){
            secureAreaPage = new SecureAreaPage(driver);
        }
        return secureAreaPage;
    }

    protected CheckBoxPage getCheckBoxPage(){
        if(checkBoxPage == null){
            checkBoxPage = new CheckBoxPage(driver);
        }
        return checkBoxPage;
    }

    protected InputsPage getInputsPage(){
        if(inputsPage == null){
            inputsPage = new InputsPage(driver);
        }
        return inputsPage;
    }

    protected DropDownPage getDropDownPage(){
        if(dropDownPage == null){
            dropDownPage = new DropDownPage(driver);
        }
        return dropDownPage;
    }

    protected AddRemoveElementsPage getAddRemoveElementsPage(){
        if(addRemoveElementsPage ==null){
            addRemoveElementsPage = new AddRemoveElementsPage(driver);
        }
        return addRemoveElementsPage;
    }

    public WebDriver getDriver() {
        return driver != null ? driver : DriverFactory.getDriver();
    }

    @AfterMethod
    public void tearDown(ITestResult result){
        if (DriverFactory.getDriver() != null){
            DriverFactory.quitDriver();
        }
    }
}
