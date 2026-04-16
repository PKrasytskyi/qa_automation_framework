package core;

import org.openqa.selenium.WebDriver;
import pages.*;
import pages.DynamicLoadingPages.DynamicLoadingPage;
import pages.DynamicLoadingPages.DynamicLoadingPageExample1;
import pages.DynamicLoadingPages.DynamicLoadingPageExample2;

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
    private NestedFramesPage nestedFramesPage;
    private MultipleWindowsPage multipleWindowsPage;
    private DynamicControlsPage dynamicControlsPage;
    private DynamicLoadingPage dynamicLoadingPage;
    private DynamicLoadingPageExample1 dynamicLoadingPageExample1;
    private DynamicLoadingPageExample2 dynamicLoadingPageExample2;

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

    public NestedFramesPage getNestedFramesPage(){
        if(nestedFramesPage == null){
            nestedFramesPage = new NestedFramesPage(driver);
        }
        return nestedFramesPage;
    }

    public MultipleWindowsPage getMultipleWindowsPage(){
        if(multipleWindowsPage == null){
            multipleWindowsPage = new MultipleWindowsPage(driver);
        }
        return multipleWindowsPage;
    }

    public DynamicControlsPage getDynamicControlsPage(){
        if(dynamicControlsPage == null){
            dynamicControlsPage = new DynamicControlsPage(driver);
        }
        return dynamicControlsPage;
    }

    public DynamicLoadingPage getDynamicLoadingPage(){
        if(dynamicLoadingPage == null){
            dynamicLoadingPage = new DynamicLoadingPage(driver);
        }
        return dynamicLoadingPage;
    }

    public DynamicLoadingPageExample1 getDynamicLoadingPageExample1(){
        if(dynamicLoadingPageExample1 == null){
            dynamicLoadingPageExample1 = new DynamicLoadingPageExample1(driver);
        }
        return dynamicLoadingPageExample1;
    }

    public DynamicLoadingPageExample2 getDynamicLoadingPageExample2(){
        if(dynamicLoadingPageExample2 == null){
            dynamicLoadingPageExample2 = new DynamicLoadingPageExample2(driver);
        }
        return dynamicLoadingPageExample2;
    }
}
