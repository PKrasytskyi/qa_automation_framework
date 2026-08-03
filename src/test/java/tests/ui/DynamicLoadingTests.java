package tests.ui;

import core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DynamicLoadingTests extends BaseTest {

    private static final String FINISH_TEXT = "Hello World";

    @Test(groups = {"ui"})
    public void isFinishTextVisibleExample1(){

        getPages().getMainPage().openDynamicLoadingPage();
        getPages().getDynamicLoadingPage().openExample1();
        getPages().getDynamicLoadingPageExample1().clickStartButton();
        Assert.assertTrue(getPages().getDynamicLoadingPageExample1().waitForLoadingBarIsInvisible(), "Loading bar is still visible");
        Assert.assertTrue(getPages().getDynamicLoadingPageExample1().isFinishTextVisible(FINISH_TEXT), "Finish text is not visible");
    }

    @Test(groups = {"ui"})
    public void isFinishTextVisibleExample2(){

        getPages().getMainPage().openDynamicLoadingPage();
        getPages().getDynamicLoadingPage().openExample2();
        getPages().getDynamicLoadingPageExample2().clickStartButton();
        Assert.assertTrue(getPages().getDynamicLoadingPageExample2().isLoadingBarInvisible(), "Loading bar is still visible");
        Assert.assertTrue(getPages().getDynamicLoadingPageExample2().isFinishTextVisible(FINISH_TEXT), "Finish text is not visible");
    }
}
