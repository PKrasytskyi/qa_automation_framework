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

        pages.getMainPage().openJavaScriptAlertsPage();
        pages.getJavaScriptAlerts().openJsAlert();
        Assert.assertEquals(pages.getJavaScriptAlerts().getJsAlertText(), JS_ALERT_TEXT, "Text is incorrect "
                + pages.getJavaScriptAlerts().getJsAlertText());
    }

    @Test(groups = {"smoke", "ui"})
    public void checkJsConfirmAlertWindowText(){

        pages.getMainPage().openJavaScriptAlertsPage();
        pages.getJavaScriptAlerts().openJsConfirm();
        Assert.assertEquals(pages.getJavaScriptAlerts().getJsAlertText(), JS_CONFIRM_TEXT, "Text is incorrect "
                + pages.getJavaScriptAlerts().getJsAlertText());
    }

    @Test(groups = {"smoke", "ui"})
    public void checkJsPromptAlertWindowText(){

        pages.getMainPage().openJavaScriptAlertsPage();
        pages.getJavaScriptAlerts().openJsPrompt();
        Assert.assertEquals(pages.getJavaScriptAlerts().getJsAlertText(), JS_PROMPT_TEXT, "Text is incorrect "
                + pages.getJavaScriptAlerts().getJsAlertText());
    }

    @Test(groups = {"smoke", "ui"})
    public void shouldSuccessfulTextAfterJsAlertOkDisplayed(){

        pages.getMainPage().openJavaScriptAlertsPage();
        pages.getJavaScriptAlerts().openJsAlert();
        pages.getJavaScriptAlerts().confirmJsAlert();
        Assert.assertEquals(pages.getJavaScriptAlerts().getResult(), JS_ALERT_OK_RESULT, "Result displayed incorrect "
                + pages.getJavaScriptAlerts().getResult());
    }

    @Test(groups = {"smoke", "ui"})
    public void shouldSuccessfulTextAfterJsConfirmOkDisplayed(){

        pages.getMainPage().openJavaScriptAlertsPage();
        pages.getJavaScriptAlerts().openJsConfirm();
        pages.getJavaScriptAlerts().confirmJsAlert();
        Assert.assertEquals(pages.getJavaScriptAlerts().getResult(), JS_CONFIRM_OK_RESULT, "Result displayed incorrect "
                + pages.getJavaScriptAlerts().getResult());
    }

    @Test(groups = {"smoke", "ui"})
    public void shouldCancelTextAfterJsConfirmCancelDisplayed(){

        pages.getMainPage().openJavaScriptAlertsPage();
        pages.getJavaScriptAlerts().openJsConfirm();
        pages.getJavaScriptAlerts().dismissJsAlert();
        Assert.assertEquals(pages.getJavaScriptAlerts().getResult(), JS_CONFIRM_CANCEL_RESULT, "Result displayed incorrect "
                + pages.getJavaScriptAlerts().getResult());
    }

    @Test(groups = {"smoke", "ui"})
    public void shouldEmptyFieldAfterJsPromptOkWithoutAnyEnteredTextDisplayed(){

        pages.getMainPage().openJavaScriptAlertsPage();
        pages.getJavaScriptAlerts().openJsPrompt();
        pages.getJavaScriptAlerts().confirmJsAlert();
        Assert.assertEquals(pages.getJavaScriptAlerts().getResult(), JS_PROMPT_OK_WITHOUT_TEXT_RESULT, "Result is not empty "
                + pages.getJavaScriptAlerts().getResult());
    }

    @Test(groups = {"smoke", "ui"})
    public void shouldNullTextAfterJsPromptCancelWithoutAnyEnteredTextDisplayed(){

        pages.getMainPage().openJavaScriptAlertsPage();
        pages.getJavaScriptAlerts().openJsPrompt();
        pages.getJavaScriptAlerts().dismissJsAlert();
        Assert.assertEquals(pages.getJavaScriptAlerts().getResult(), JS_PROMPT_CANCEL_RESULT, "Result is incorrect "
                + pages.getJavaScriptAlerts().getResult());
    }

    @Test(groups = {"smoke", "ui"})
    public void shouldEnteredJsPromptTextDisplayed(){

        String text = "text field of prompt alert test";

        pages.getMainPage().openJavaScriptAlertsPage();
        pages.getJavaScriptAlerts().openJsPrompt();
        pages.getJavaScriptAlerts().enterJsAlertText(text);
        pages.getJavaScriptAlerts().confirmJsAlert();
        Assert.assertTrue(pages.getJavaScriptAlerts().getResult().contains(text), "Result is incorrect "
                + pages.getJavaScriptAlerts().getResult());
    }

}
