package tests.ui;

import core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class IFramesTests extends BaseTest {

    private static final String DEFAULT_EDITOR_TEXT = "Your content goes here.";

    @Test(groups = {"ui"})
    public void shouldDefaultEditorTextDisplayed(){

        getPages().getMainPage().openFramesPage();
        getPages().getFramesPage().openIFrames();
        getPages().getFramesPage().switchToEditorFrame();
        Assert.assertEquals(getPages().getFramesPage().getEditorText(), DEFAULT_EDITOR_TEXT, "Text is incorrect "
                + getPages().getFramesPage().getEditorText());
    }
}
