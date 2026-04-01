package tests.ui;

import core.BaseTest;
import tests.data.DropDownData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DropDownTests extends BaseTest {

    @Test(dataProvider = "dropdownOptions", dataProviderClass = DropDownData.class, groups = {"ui"})
    public void shouldSelectOptionFromDropDown(String option) {
        pages.getMainPage().openDropDownPage();
        pages.getDropDownPage().selectOption(option);
        Assert.assertTrue(
                pages.getDropDownPage().isOptionSelected(option), "Expected option " + option + "," +
                        "but selected" + pages.getDropDownPage().getSelectedOption()
        );
    }
}
