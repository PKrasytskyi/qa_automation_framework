package tests.ui;

import core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MultipleWindowsTests extends BaseTest {

    private static final String NEW_PAGE_TEXT = "New Window";
    private static final String ORIGINAL_PAGE_TEXT = "Opening a new window";
    private static final String PARTIAL_LINK_OF_NEW_PAGE = "/new";

    @Test(groups = {"ui"})
    public void shouldOpenNewWindowAndReadText(){

        getPages().getMainPage().openMultipleWindowsPage();
        getPages().getMultipleWindowsPage().switchToAnotherWindow();
        Assert.assertTrue(getPages().getMultipleWindowsPage().getNewPageText().contains(NEW_PAGE_TEXT), "Unexpected new windows text "
            + getPages().getMultipleWindowsPage().getNewPageText());
    }

    @Test(groups = {"ui"})
    public void shouldReturnToOriginalWindowAfterSwitchBack(){

        getPages().getMainPage().openMultipleWindowsPage();
        getPages().getMultipleWindowsPage().switchToAnotherWindow();
        getPages().getMultipleWindowsPage().switchToOriginalPage();
        Assert.assertTrue(getPages().getMultipleWindowsPage().getOriginalPageText().contains(ORIGINAL_PAGE_TEXT), "Unexpected text");

    }

    @Test(groups = {"ui"})
    public void shouldOpenNewWindowAfterClick(){

        getPages().getMainPage().openMultipleWindowsPage();
        getPages().getMultipleWindowsPage().switchToAnotherWindow();
        Assert.assertTrue(getPages().getMultipleWindowsPage().getCurrentLink().contains(PARTIAL_LINK_OF_NEW_PAGE), "Unexpected text");
    }

    @Test(groups = {"ui"})
    public void shouldOriginalWindowDisplayedAfterCloseNewWindow(){

        getPages().getMainPage().openMultipleWindowsPage();
        Assert.assertTrue(getPages().getMultipleWindowsPage().closeNewWindowsReturnToOriginalAndReadText().contains(ORIGINAL_PAGE_TEXT), "Unexpected text" );
    }
}
