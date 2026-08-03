package tests.ui;

import core.BaseTest;
import tests.data.CheckboxesData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckboxesTests extends BaseTest {


    @Test(dataProvider = "firstCheckbox", dataProviderClass = CheckboxesData.class, groups = {"smoke"})
    public void selectCheckBox1(String name) {


        getPages().getMainPage().openCheckboxes();
        getPages().getCheckBoxPage().selectCheckbox(name);
        Assert.assertTrue(getPages().getCheckBoxPage().isSelected(name), "Checkbox " + name + " is not selected");

    }

    @Test(dataProvider = "secondCheckbox", dataProviderClass = CheckboxesData.class, groups = {"smoke"})
    public void deselectCheckBox2(String name) {

        getPages().getMainPage().openCheckboxes();
        getPages().getCheckBoxPage().deselectCheckBox(name);
        Assert.assertFalse(getPages().getCheckBoxPage().isSelected(name), "Checkbox " + name + " is selected");
    }

    @Test(dataProvider = "allCheckboxes", dataProviderClass = CheckboxesData.class, groups = {"ui"})
    public void shouldSelectAllCheckboxes(String... names) {

        getPages().getMainPage().openCheckboxes();
        getPages().getCheckBoxPage().selectAllCheckBoxes(names);
        Assert.assertTrue(getPages().getCheckBoxPage().isAllCheckBoxesSelected(names), "Checkboxes is not selected");
    }

    @Test(dataProvider = "allCheckboxes", dataProviderClass = CheckboxesData.class, groups = {"ui"})
    public void shouldDeselectAllCheckboxes(String... names) {

        getPages().getMainPage().openCheckboxes();
        getPages().getCheckBoxPage().deselectAllCheckBoxes(names);
        Assert.assertFalse(getPages().getCheckBoxPage().isAllCheckBoxesSelected(names), "Checkboxes are selected");
    }
}
