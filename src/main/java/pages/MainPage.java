package pages;

import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends BasePage {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    //Locators
    private final By formAuthentication = By.cssSelector("a[href='/login']");
    private final By checkboxes = By.cssSelector("a[href='/checkboxes']");
    private final By inputsPage = By.cssSelector("a[href='/inputs']");
    private final By dropDownPage = By.cssSelector("a[href='/dropdown']");
    private final By addRemoveElements = By.cssSelector("a[href='/add_remove_elements/']");
    private final By javaScriptAlerts = By.cssSelector("a[href='/javascript_alerts']");
    private final By frames = By.cssSelector("a[href='/frames']");
    private final By nestedFrames = By.cssSelector("a[href='/nested_frames']");
    private final By multipleWindows = By.cssSelector("a[href='/windows']");

    public void openFormAuthentication() {
        click(formAuthentication);
    }

    public void openCheckboxes() {
        click(checkboxes);
    }

    public void openInputsPage() {
        click(inputsPage);
    }

    public void openDropDownPage() {
        click(dropDownPage);
    }

    public void openAddRemoveElementsPage() {
        click(addRemoveElements);
    }

    public void openJavaScriptAlertsPage(){ click(javaScriptAlerts);}

    public void openFramesPage(){ click(frames);}

    public void openNestedFramesPage(){ click(nestedFrames);}

    public void openMultipleWindowsPage(){ click(multipleWindows);}

}

