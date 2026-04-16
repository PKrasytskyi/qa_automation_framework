package tests.ui;

import core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DynamicLoadingTests extends BaseTest {

    private static final String FINISH_TEXT = "Hello World";

    @Test(groups = {"ui"})
    public void isFinishTextVisibleExample1(){

        pages.getMainPage().openDynamicLoadingPage();
        pages.getDynamicLoadingPage().openExample1();
        pages.getDynamicLoadingPageExample1().clickStartButton();
        Assert.assertTrue(pages.getDynamicLoadingPageExample1().waitForLoadingBarIsInvisible(), "Loading bar is still visible");
        Assert.assertTrue(pages.getDynamicLoadingPageExample1().isFinishTextVisible(FINISH_TEXT), "Finish text is not visible");
    }

    @Test(groups = {"ui"})
    public void isFinishTextVisibleExample2(){

        pages.getMainPage().openDynamicLoadingPage();
        pages.getDynamicLoadingPage().openExample2();
        pages.getDynamicLoadingPageExample2().clickStartButton();
        Assert.assertTrue(pages.getDynamicLoadingPageExample2().isLoadingBarInvisible(), "Loading bar is still visible");
        Assert.assertTrue(pages.getDynamicLoadingPageExample2().isFinishTextVisible(FINISH_TEXT), "Finish text is not visible");
    }
}
