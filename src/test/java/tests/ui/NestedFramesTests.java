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

        pages.getMainPage().openNestedFramesPage();
        Assert.assertTrue(pages.getNestedFramesPage().getLeftFrameText().contains(TEXT_OF_LEFT_FRAME), "Unexpected frame text "
           + pages.getNestedFramesPage().getFrameText());
    }

    @Test(groups = {"smoke", "ui"})
    public void shouldOpenRightFrameAndReadText(){

        pages.getMainPage().openNestedFramesPage();
        Assert.assertTrue(pages.getNestedFramesPage().getRightFrameText().contains(TEXT_OF_RIGHT_FRAME), "Unexpected frame text "
                + pages.getNestedFramesPage().getFrameText());
    }

    @Test(groups = {"smoke", "ui"})
    public void shouldOpenMiddleFrameAndReadText(){

        pages.getMainPage().openNestedFramesPage();
        Assert.assertTrue(pages.getNestedFramesPage().getMiddleFrameText().contains(TEXT_OF_MIDDLE_FRAME), "Unexpected frame text "
            + pages.getNestedFramesPage().getFrameText());
    }

    @Test(groups = {"smoke", "ui"})
    public void shouldOpenBottomFrameAndReadText(){

        pages.getMainPage().openNestedFramesPage();
        Assert.assertTrue(pages.getNestedFramesPage().getBottomFrameText().contains(TEXT_OF_BOTTOM_FRAME), "Unexpected frame text "
            + pages.getNestedFramesPage().getFrameText());
    }
}