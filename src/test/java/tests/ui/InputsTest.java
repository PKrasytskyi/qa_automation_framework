package tests.ui;

import core.BaseTest;
import tests.data.InputsData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class InputsTest extends BaseTest {

    @Test(dataProvider = "inputsFieldValues", dataProviderClass = InputsData.class, groups = {"ui"})
    public void shouldSetInputValue(String number) {

        getPages().getMainPage().openInputsPage();
        getPages().getInputsPage().enterInputData(number);
        String actualResult = getPages().getInputsPage().getInputFieldValue();
        Assert.assertEquals(actualResult, number, "Expected: " + number + ", but was: " + actualResult);
    }

    @Test(dataProvider = "oneValue", dataProviderClass = InputsData.class, groups = {"ui"})
    public void shouldInputFieldBeEmptyAfterRefresh(String number) {

        getPages().getMainPage().openInputsPage();
        getPages().getInputsPage().enterInputData(number);
        getPages().getInputsPage().refreshPage();
        Assert.assertTrue(getPages().getInputsPage().getInputFieldValue().isEmpty(),
                "Input field is not empty " + getPages().getInputsPage().getInputFieldValue());
    }
}
