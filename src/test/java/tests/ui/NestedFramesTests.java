package tests.ui;

import core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NestedFramesTests extends BaseTest {

    private static final String TEXT_OF_LEFT_FRAME = "LEFT";
    private static final String TEXT_OF_MIDDLE_FRAME = "MIDDLE";
    private static final String TEXT_OF_RIGHT_FRAME = "RIGHT";
    private static final String TEXT_OF_BOTTOM_FRAME = "BOTTOM";

    @Test(groups = {"smoke", "ui"})
    public void shouldOpenLeftFrameAndReadText(){

        getPages().getMainPage().openNestedFramesPage();
        Assert.assertTrue(getPages().getNestedFramesPage().getLeftFrameText().contains(TEXT_OF_LEFT_FRAME), "Unexpected frame text "
           + getPages().getNestedFramesPage().getFrameText());
    }

    @Test(groups = {"smoke", "ui"})
    public void shouldOpenRightFrameAndReadText(){

        getPages().getMainPage().openNestedFramesPage();
        Assert.assertTrue(getPages().getNestedFramesPage().getRightFrameText().contains(TEXT_OF_RIGHT_FRAME), "Unexpected frame text "
                + getPages().getNestedFramesPage().getFrameText());
    }

    @Test(groups = {"smoke", "ui"})
    public void shouldOpenMiddleFrameAndReadText(){

        getPages().getMainPage().openNestedFramesPage();
        Assert.assertTrue(getPages().getNestedFramesPage().getMiddleFrameText().contains(TEXT_OF_MIDDLE_FRAME), "Unexpected frame text "
            + getPages().getNestedFramesPage().getFrameText());
    }

    @Test(groups = {"smoke", "ui"})
    public void shouldOpenBottomFrameAndReadText(){

        getPages().getMainPage().openNestedFramesPage();
        Assert.assertTrue(getPages().getNestedFramesPage().getBottomFrameText().contains(TEXT_OF_BOTTOM_FRAME), "Unexpected frame text "
            + getPages().getNestedFramesPage().getFrameText());
    }
}