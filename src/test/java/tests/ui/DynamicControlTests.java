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

        getPages().getMainPage().openDynamicControlsPage();
        getPages().getDynamicControlsPage().clickRemoveButton();
        Assert.assertTrue(getPages().getDynamicControlsPage().checkCheckBoxInputDisappearence(), "Checkbox input field is present");
        Assert.assertEquals(getPages().getDynamicControlsPage().getTextMessage(), TEXT_MESSAGE_AFTER_REMOVE_CHECKBOX, "Text message is incorrect "
                    + getPages().getDynamicControlsPage().getTextMessage());
    }

    @Test(groups = {"ui", "smoke"})
    public void shouldAddCheckboxBack(){

        getPages().getMainPage().openDynamicControlsPage();
        getPages().getDynamicControlsPage().clickRemoveButton();
        getPages().getDynamicControlsPage().clickAddButton();
        Assert.assertTrue(getPages().getDynamicControlsPage().checkCheckBoxInputAppearence(), "Checkbox input field is not present");
        Assert.assertEquals(getPages().getDynamicControlsPage().getTextMessage(), TEXT_MESSAGE_AFTER_ADD_CHECKBOX, "Text message is incorrect "
                    + getPages().getDynamicControlsPage().getTextMessage());
    }

    @Test(groups = {"ui", "smoke"})
    public void shouldEnableInput(){

        getPages().getMainPage().openDynamicControlsPage();
        getPages().getDynamicControlsPage().clickEnableButton();
        Assert.assertTrue(getPages().getDynamicControlsPage().checkInputRowIsEnable(), "Input row is not enable");
        Assert.assertTrue(getPages().getDynamicControlsPage().isDisableButtonShown(), "Disable button is not displayed");
        Assert.assertTrue(getPages().getDynamicControlsPage().getTextMessage().contains(TEXT_MESSAGE_AFTER_ENABLE_INPUT_ROW));
    }

    @Test(groups = {"ui", "smoke"})
    public void shouldDisableInput(){

        getPages().getMainPage().openDynamicControlsPage();
        getPages().getDynamicControlsPage().clickEnableButton();
        getPages().getDynamicControlsPage().clickDisableButton();
        Assert.assertTrue(getPages().getDynamicControlsPage().checkInputRowIsDisable(), "Input row is enable");
        Assert.assertTrue(getPages().getDynamicControlsPage().isEnableButtonShown(), "Enable button is not displayed");
        Assert.assertTrue(getPages().getDynamicControlsPage().getTextMessage().contains(TEXT_MESSAGE_AFTER_DISABLE_INPUT_ROW));
    }

    @Test(groups = {"ui", "smoke"})
    public void shouldEnterTextIntoEnabledInput(){

        getPages().getMainPage().openDynamicControlsPage();
        getPages().getDynamicControlsPage().clickEnableButton();
        getPages().getDynamicControlsPage().checkInputRowIsEnable();
        getPages().getDynamicControlsPage().fillInputRow(TEXT_MESSAGE_OF_INPUT_ROW);
        Assert.assertEquals(getPages().getDynamicControlsPage().getTextFromInputRow(), TEXT_MESSAGE_OF_INPUT_ROW, "Input row is empty");
    }
}
