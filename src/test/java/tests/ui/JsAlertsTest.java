package tests.ui;

import core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class JsAlertsTest extends BaseTest {

    private static final String JS_ALERT_TEXT = "I am a JS Alert";
    private static final String JS_CONFIRM_TEXT = "I am a JS Confirm";
    private static final String JS_PROMPT_TEXT = "I am a JS prompt";
    private static final String JS_ALERT_OK_RESULT = "You successfully clicked an alert";
    private static final String JS_CONFIRM_OK_RESULT = "You clicked: Ok";
    private static final String JS_CONFIRM_CANCEL_RESULT = "You clicked: Cancel";
    private static final String JS_PROMPT_CANCEL_RESULT = "You entered: null";
    private static final String JS_PROMPT_OK_WITHOUT_TEXT_RESULT = "You entered:";

    @Test(groups = {"smoke", "ui"})
    public void checkJsAlertWindowText(){

        getPages().getMainPage().openJavaScriptAlertsPage();
        getPages().getJavaScriptAlerts().openJsAlert();
        Assert.assertEquals(getPages().getJavaScriptAlerts().getJsAlertText(), JS_ALERT_TEXT, "Text is incorrect "
                + getPages().getJavaScriptAlerts().getJsAlertText());
    }

    @Test(groups = {"smoke", "ui"})
    public void checkJsConfirmAlertWindowText(){

        getPages().getMainPage().openJavaScriptAlertsPage();
        getPages().getJavaScriptAlerts().openJsConfirm();
        Assert.assertEquals(getPages().getJavaScriptAlerts().getJsAlertText(), JS_CONFIRM_TEXT, "Text is incorrect "
                + getPages().getJavaScriptAlerts().getJsAlertText());
    }

    @Test(groups = {"smoke", "ui"})
    public void checkJsPromptAlertWindowText(){

        getPages().getMainPage().openJavaScriptAlertsPage();
        getPages().getJavaScriptAlerts().openJsPrompt();
        Assert.assertEquals(getPages().getJavaScriptAlerts().getJsAlertText(), JS_PROMPT_TEXT, "Text is incorrect "
                + getPages().getJavaScriptAlerts().getJsAlertText());
    }

    @Test(groups = {"smoke", "ui"})
    public void shouldSuccessfulTextAfterJsAlertOkDisplayed(){

        getPages().getMainPage().openJavaScriptAlertsPage();
        getPages().getJavaScriptAlerts().openJsAlert();
        getPages().getJavaScriptAlerts().confirmJsAlert();
        Assert.assertEquals(getPages().getJavaScriptAlerts().getResult(), JS_ALERT_OK_RESULT, "Result displayed incorrect "
                + getPages().getJavaScriptAlerts().getResult());
    }

    @Test(groups = {"smoke", "ui"})
    public void shouldSuccessfulTextAfterJsConfirmOkDisplayed(){

        getPages().getMainPage().openJavaScriptAlertsPage();
        getPages().getJavaScriptAlerts().openJsConfirm();
        getPages().getJavaScriptAlerts().confirmJsAlert();
        Assert.assertEquals(getPages().getJavaScriptAlerts().getResult(), JS_CONFIRM_OK_RESULT, "Result displayed incorrect "
                + getPages().getJavaScriptAlerts().getResult());
    }

    @Test(groups = {"smoke", "ui"})
    public void shouldCancelTextAfterJsConfirmCancelDisplayed(){

        getPages().getMainPage().openJavaScriptAlertsPage();
        getPages().getJavaScriptAlerts().openJsConfirm();
        getPages().getJavaScriptAlerts().dismissJsAlert();
        Assert.assertEquals(getPages().getJavaScriptAlerts().getResult(), JS_CONFIRM_CANCEL_RESULT, "Result displayed incorrect "
                + getPages().getJavaScriptAlerts().getResult());
    }

    @Test(groups = {"smoke", "ui"})
    public void shouldEmptyFieldAfterJsPromptOkWithoutAnyEnteredTextDisplayed(){

        getPages().getMainPage().openJavaScriptAlertsPage();
        getPages().getJavaScriptAlerts().openJsPrompt();
        getPages().getJavaScriptAlerts().confirmJsAlert();
        Assert.assertEquals(getPages().getJavaScriptAlerts().getResult(), JS_PROMPT_OK_WITHOUT_TEXT_RESULT, "Result is not empty "
                + getPages().getJavaScriptAlerts().getResult());
    }

    @Test(groups = {"smoke", "ui"})
    public void shouldNullTextAfterJsPromptCancelWithoutAnyEnteredTextDisplayed(){

        getPages().getMainPage().openJavaScriptAlertsPage();
        getPages().getJavaScriptAlerts().openJsPrompt();
        getPages().getJavaScriptAlerts().dismissJsAlert();
        Assert.assertEquals(getPages().getJavaScriptAlerts().getResult(), JS_PROMPT_CANCEL_RESULT, "Result is incorrect "
                + getPages().getJavaScriptAlerts().getResult());
    }

    @Test(groups = {"smoke", "ui"})
    public void shouldEnteredJsPromptTextDisplayed(){

        String text = "text field of prompt alert test";

        getPages().getMainPage().openJavaScriptAlertsPage();
        getPages().getJavaScriptAlerts().openJsPrompt();
        getPages().getJavaScriptAlerts().enterJsAlertText(text);
        getPages().getJavaScriptAlerts().confirmJsAlert();
        Assert.assertTrue(getPages().getJavaScriptAlerts().getResult().contains(text), "Result is incorrect "
                + getPages().getJavaScriptAlerts().getResult());
    }

}
