package tests.ui;

import core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DynamicControlTests extends BaseTest {

    private static final String  TEXT_MESSAGE_AFTER_REMOVE_CHECKBOX = "It's gone!";
    private static final String TEXT_MESSAGE_AFTER_ADD_CHECKBOX = "It's back!";
    private static final String TEXT_MESSAGE_AFTER_ENABLE_INPUT_ROW = "It's enabled!";
    private static final String TEXT_MESSAGE_AFTER_DISABLE_INPUT_ROW = "It's disabled!";
    private static final String TEXT_MESSAGE_OF_INPUT_ROW = "test";

    @Test(groups = {"ui", "smoke"})
    public void shouldRemoveCheckbox(){

        pages.getMainPage().openDynamicControlsPage();
        pages.getDynamicControlsPage().clickRemoveButton();
        Assert.assertTrue(pages.getDynamicControlsPage().checkCheckBoxInputDisappearence(), "Checkbox input field is present");
        Assert.assertEquals(pages.getDynamicControlsPage().getTextMessage(), TEXT_MESSAGE_AFTER_REMOVE_CHECKBOX, "Text message is incorrect "
                    + pages.getDynamicControlsPage().getTextMessage());
    }

    @Test(groups = {"ui", "smoke"})
    public void shouldAddCheckboxBack(){

        pages.getMainPage().openDynamicControlsPage();
        pages.getDynamicControlsPage().clickRemoveButton();
        pages.getDynamicControlsPage().clickAddButton();
        Assert.assertTrue(pages.getDynamicControlsPage().checkCheckBoxInputAppearence(), "Checkbox input field is not present");
        Assert.assertEquals(pages.getDynamicControlsPage().getTextMessage(), TEXT_MESSAGE_AFTER_ADD_CHECKBOX, "Text message is incorrect "
                    + pages.getDynamicControlsPage().getTextMessage());
    }

    @Test(groups = {"ui", "smoke"})
    public void shouldEnableInput(){

        pages.getMainPage().openDynamicControlsPage();
        pages.getDynamicControlsPage().clickEnableButton();
        Assert.assertTrue(pages.getDynamicControlsPage().checkInputRowIsEnable(), "Input row is not enable");
        Assert.assertTrue(pages.getDynamicControlsPage().isDisableButtonShown(), "Disable button is not displayed");
        Assert.assertTrue(pages.getDynamicControlsPage().getTextMessage().contains(TEXT_MESSAGE_AFTER_ENABLE_INPUT_ROW));
    }

    @Test(groups = {"ui", "smoke"})
    public void shouldDisableInput(){

        pages.getMainPage().openDynamicControlsPage();
        pages.getDynamicControlsPage().clickEnableButton();
        pages.getDynamicControlsPage().clickDisableButton();
        Assert.assertTrue(pages.getDynamicControlsPage().checkInputRowIsDisable(), "Input row is enable");
        Assert.assertTrue(pages.getDynamicControlsPage().isEnableButtonShown(), "Enable button is not displayed");
        Assert.assertTrue(pages.getDynamicControlsPage().getTextMessage().contains(TEXT_MESSAGE_AFTER_DISABLE_INPUT_ROW));
    }

    @Test(groups = {"ui", "smoke"})
    public void shouldEnterTextIntoEnabledInput(){

        pages.getMainPage().openDynamicControlsPage();
        pages.getDynamicControlsPage().clickEnableButton();
        pages.getDynamicControlsPage().checkInputRowIsEnable();
        pages.getDynamicControlsPage().fillInputRow(TEXT_MESSAGE_OF_INPUT_ROW);
        Assert.assertEquals(pages.getDynamicControlsPage().getTextFromInputRow(), TEXT_MESSAGE_OF_INPUT_ROW, "Input row is empty");
    }
}
