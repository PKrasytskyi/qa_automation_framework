package core;

import org.openqa.selenium.WebDriver;
import pages.*;

public class PageManager {

    private final WebDriver driver;

    private MainPage mainPage;
    private LoginPage loginPage;
    private SecureAreaPage secureAreaPage;
    private CheckBoxPage checkBoxPage;
    private InputsPage inputsPage;
    private DropDownPage dropDownPage;
    private AddRemoveElementsPage addRemoveElementsPage;
    private JavaScriptAlertsPage javaScriptAlerts;
    private FramesPage framesPage;

    public PageManager(WebDriver driver) {
        this.driver = driver;
    }

    public MainPage getMainPage() {
        if (mainPage == null) {
            mainPage = new MainPage(driver);
        }
        return mainPage;
    }

    public LoginPage getLoginPage() {
        if (loginPage == null) {
            loginPage = new LoginPage(driver);
        }
        return loginPage;
    }

    public SecureAreaPage getSecureAreaPage() {
        if (secureAreaPage == null) {
            secureAreaPage = new SecureAreaPage(driver);
        }
        return secureAreaPage;
    }

    public CheckBoxPage getCheckBoxPage() {
        if (checkBoxPage == null) {
            checkBoxPage = new CheckBoxPage(driver);
        }
        return checkBoxPage;
    }

    public InputsPage getInputsPage() {
        if (inputsPage == null) {
            inputsPage = new InputsPage(driver);
        }
        return inputsPage;
    }

    public DropDownPage getDropDownPage() {
        if (dropDownPage == null) {
            dropDownPage = new DropDownPage(driver);
        }
        return dropDownPage;
    }

    public AddRemoveElementsPage getAddRemoveElementsPage() {
        if (addRemoveElementsPage == null) {
            addRemoveElementsPage = new AddRemoveElementsPage(driver);
        }
        return addRemoveElementsPage;
    }

    public JavaScriptAlertsPage getJavaScriptAlerts(){
        if(javaScriptAlerts == null){
            javaScriptAlerts = new JavaScriptAlertsPage(driver);
        }
        return javaScriptAlerts;
    }

    public FramesPage getFramesPage() {
        if(framesPage == null){
            framesPage = new FramesPage(driver);
        }
        return framesPage;
    }
}
