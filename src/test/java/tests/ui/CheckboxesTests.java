package tests.ui;

import core.BaseTest;
import data.CheckboxesData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckboxesTests extends BaseTest {


    @Test(dataProvider = "firstCheckbox", dataProviderClass = CheckboxesData.class)
    public void selectCheckBox1(String name){


        getMainPage().clickCheckboxes();
        getCheckBoxPage().selectCheckbox(name);
        Assert.assertTrue(getCheckBoxPage().isSelected(name), "Checkbox " + name + " is not selected");

    }

    @Test(dataProvider = "secondCheckbox", dataProviderClass = CheckboxesData.class)
    public void deselectCheckBox2(String name){

        getMainPage().clickCheckboxes();
        getCheckBoxPage().deselectCheckBox(name);
        Assert.assertFalse(getCheckBoxPage().isSelected(name), "Checkbox " + name + " is selected");
    }

    @Test(dataProvider = "allCheckboxes", dataProviderClass = CheckboxesData.class)
    public void shouldSelectAllCheckboxes(String... names){

        getMainPage().clickCheckboxes();
        getCheckBoxPage().selectAllCheckBoxes(names);
        Assert.assertTrue(getCheckBoxPage().isAllCheckBoxesSelected(names), "Checkboxes is not selected");
    }

    @Test(dataProvider = "allCheckboxes", dataProviderClass = CheckboxesData.class)
    public void shouldDeselectAllCheckboxes(String... names){

        getMainPage().clickCheckboxes();
        getCheckBoxPage().deselectAllCheckBoxes(names);
        Assert.assertFalse(getCheckBoxPage().isAllCheckBoxesSelected(names), "Checkboxes are selected");
    }
}
