package pages;

import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NestedFramesPage extends BasePage {

    public NestedFramesPage(WebDriver driver) {
        super(driver);
    }

    //Locators
    private final String frameTop = "frame-top";
    private final String leftFrame = "frame-left";
    private final String middleFrame = "frame-middle";
    private final String rightFrame = "frame-right";
    private final String bottomFrame = "frame-bottom";

    private final By frameBodyText = By.tagName("body");

    public void switchToLeftFrame(){
        switchToDefaultContent();
        switchToIFrameByNameOrId(frameTop);
        switchToIFrameByNameOrId(leftFrame);
    }

    public void switchToMiddleFrame(){
        switchToDefaultContent();
        switchToIFrameByNameOrId(frameTop);
        switchToIFrameByNameOrId(middleFrame);
    }

    public void switchToRightFrame(){
        switchToDefaultContent();
        switchToIFrameByNameOrId(frameTop);
        switchToIFrameByNameOrId(rightFrame);
    }

    public void switchToBottomFrame(){
        switchToDefaultContent();
        switchToIFrameByNameOrId(bottomFrame);
    }

    public String getFrameText(){
       return getText(frameBodyText).trim();
    }

    public  String getLeftFrameText(){
        switchToLeftFrame();
        return getFrameText();
    }

    public String getRightFrameText(){
        switchToRightFrame();
        return getFrameText();
    }

    public String getMiddleFrameText(){
        switchToMiddleFrame();
        return getFrameText();
    }

    public String getBottomFrameText(){
        switchToBottomFrame();
        return getFrameText();
    }
}
