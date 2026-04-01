package tests.ui;

import core.BaseTest;
import tests.data.CheckboxesData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckboxesTests extends BaseTest {


    @Test(dataProvider = "firstCheckbox", dataProviderClass = CheckboxesData.class, groups = {"smoke"})
    public void selectCheckBox1(String name) {


        pages.getMainPage().openCheckboxes();
        pages.getCheckBoxPage().selectCheckbox(name);
        Assert.assertTrue(pages.getCheckBoxPage().isSelected(name), "Checkbox " + name + " is not selected");

    }

    @Test(dataProvider = "secondCheckbox", dataProviderClass = CheckboxesData.class, groups = {"smoke"})
    public void deselectCheckBox2(String name) {

        pages.getMainPage().openCheckboxes();
        pages.getCheckBoxPage().deselectCheckBox(name);
        Assert.assertFalse(pages.getCheckBoxPage().isSelected(name), "Checkbox " + name + " is selected");
    }

    @Test(dataProvider = "allCheckboxes", dataProviderClass = CheckboxesData.class, groups = {"ui"})
    public void shouldSelectAllCheckboxes(String... names) {

        pages.getMainPage().openCheckboxes();
        pages.getCheckBoxPage().selectAllCheckBoxes(names);
        Assert.assertTrue(pages.getCheckBoxPage().isAllCheckBoxesSelected(names), "Checkboxes is not selected");
    }

    @Test(dataProvider = "allCheckboxes", dataProviderClass = CheckboxesData.class, groups = {"ui"})
    public void shouldDeselectAllCheckboxes(String... names) {

        pages.getMainPage().openCheckboxes();
        pages.getCheckBoxPage().deselectAllCheckBoxes(names);
        Assert.assertFalse(pages.getCheckBoxPage().isAllCheckBoxesSelected(names), "Checkboxes are selected");
    }
}
