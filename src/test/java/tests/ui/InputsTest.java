package tests.ui;

import core.BaseTest;
import tests.data.InputsData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class InputsTest extends BaseTest {

    @Test(dataProvider = "inputsFieldValues", dataProviderClass = InputsData.class, groups = {"ui"})
    public void shouldSetInputValue(String number) {

        pages.getMainPage().openInputsPage();
        pages.getInputsPage().enterInputData(number);
        String actualResult = pages.getInputsPage().getInputFieldValue();
        Assert.assertEquals(actualResult, number, "Expected: " + number + ", but was: " + actualResult);
    }

    @Test(dataProvider = "oneValue", dataProviderClass = InputsData.class, groups = {"ui"})
    public void shouldInputFieldBeEmptyAfterRefresh(String number) {

        pages.getMainPage().openInputsPage();
        pages.getInputsPage().enterInputData(number);
        pages.getInputsPage().refreshPage();
        Assert.assertTrue(pages.getInputsPage().getInputFieldValue().isEmpty(),
                "Input field is not empty " + pages.getInputsPage().getInputFieldValue());
    }
}
