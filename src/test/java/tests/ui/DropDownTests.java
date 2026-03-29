package tests.ui;

import core.BaseTest;
import data.DropDownData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DropDownTests extends BaseTest {

    @Test(dataProvider = "dropdownOptions", dataProviderClass = DropDownData.class, groups = {"ui"})
    public void shouldSelectOptionFromDropDown(String option){
        getMainPage().openDropDownPage();
        getDropDownPage().selectOption(option);
        Assert.assertTrue(
                getDropDownPage().isOptionSelected(option), "Expected option " + option + ",but selected" + getDropDownPage().getSelectedOption()
        );
    }
}
