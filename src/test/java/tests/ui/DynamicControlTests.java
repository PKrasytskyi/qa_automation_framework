package tests.ui;

import core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DynamicControlTests extends BaseTest {

    private static final String  TEXT_MESSAGE_AFTER_REMOVE_CHECKBOX = "It's gone!";
    private static final String TEXT_MESSAGE_AFTER_ADD_CHECKBOX = "It's back!";
    private static final String TEXT_MESSAGE_AFTER_ENABLE_INPUT_ROW = "It's enabled!";
    private static final String TEXT_MESSAGE_AFTER_DISABLE_INPUT_ROW = "It's disabled!";

    @Test(groups = {"ui", "smoke"})
    public void shouldRemoveCheckbox(){

        pages.getMainPage().openDynamicControlsPage();
        pages.getDynamicControlsPage().clickRemoveButton();
        Assert.assertTrue(pages.getDynamicControlsPage().checkCheckBoxInputDisappearence());
        Assert.assertEquals(pages.getDynamicControlsPage().getTextMessage(), TEXT_MESSAGE_AFTER_REMOVE_CHECKBOX, "Text message is incorrect "
                    + pages.getDynamicControlsPage().getTextMessage());
    }
}
