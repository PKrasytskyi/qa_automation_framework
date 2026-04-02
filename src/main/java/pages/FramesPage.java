package pages;

import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FramesPage extends BasePage {

    public FramesPage(WebDriver driver) {
        super(driver);
    }

    //Locators
    private final By iFrames = By.cssSelector("a[href='/iframe']");
    private final String iFrameById = "mce_0_ifr";
    private final By editor = By.cssSelector("#tinymce > p");


    public void openIFrames(){
        click(iFrames);
    }

    public void switchToEditorFrame(){
        switchToIFrameByNameOrId(iFrameById);
    }

    public String getEditorText(){
        return getText(editor);
    }
}
