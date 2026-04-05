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

        pages.getMainPage().openMultipleWindowsPage();
        pages.getMultipleWindowsPage().switchToAnotherWindow();
        Assert.assertTrue(pages.getMultipleWindowsPage().getNewPageText().contains(NEW_PAGE_TEXT), "Unexpected new windows text "
            + pages.getMultipleWindowsPage().getNewPageText());
    }

    @Test(groups = {"ui"})
    public void shouldReturnToOriginalWindowAfterSwitchBack(){

        pages.getMainPage().openMultipleWindowsPage();
        pages.getMultipleWindowsPage().switchToAnotherWindow();
        pages.getMultipleWindowsPage().switchToOriginalPage();
        Assert.assertTrue(pages.getMultipleWindowsPage().getOriginalPageText().contains(ORIGINAL_PAGE_TEXT), "Unexpected text");

    }

    @Test(groups = {"ui"})
    public void shouldOpenNewWindowAfterClick(){

        pages.getMainPage().openMultipleWindowsPage();
        pages.getMultipleWindowsPage().switchToAnotherWindow();
        Assert.assertTrue(pages.getMultipleWindowsPage().getCurrentLink().contains(PARTIAL_LINK_OF_NEW_PAGE), "Unexpected text");
    }

    @Test(groups = {"ui"})
    public void shouldOriginalWindowDisplayedAfterCloseNewWindow(){

        pages.getMainPage().openMultipleWindowsPage();
        Assert.assertTrue(pages.getMultipleWindowsPage().closeNewWindowsReturnToOriginalAndReadText().contains(ORIGINAL_PAGE_TEXT), "Unexpected text" );
    }
}
