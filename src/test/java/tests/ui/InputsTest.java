package tests.ui;

import core.BaseTest;
import data.InputsData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class InputsTest extends BaseTest {

    @Test(dataProvider = "inputsFieldValues", dataProviderClass = InputsData.class, groups = {"ui"})
    public void shouldSetInputValue(String number){

        getMainPage().openInputsPage();
        getInputsPage().setInputsData(number);
        String actualResult = getInputsPage().getInputFieldValue();
        Assert.assertEquals(actualResult, number, "Expected: " + number + ", but was: " + actualResult);
    }

    @Test(dataProvider = "oneValue", dataProviderClass = InputsData.class, groups = {"ui"})
    public void shouldInputFieldBeEmptyAfterRefresh(String number){

        getMainPage().openInputsPage();
        getInputsPage().setInputsData(number);
        driver.navigate().refresh();
        Assert.assertTrue(getInputsPage().getInputFieldValue().isEmpty(),  "Input field is not empty " + getInputsPage().getInputFieldValue());
    }
}
