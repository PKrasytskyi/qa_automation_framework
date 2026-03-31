package data;

import org.testng.annotations.DataProvider;

public class CheckboxesData {

    @DataProvider(name = "firstCheckbox")
    public Object[][] firstCheckbox() {
        return new Object[][]{
                {"checkbox 1"}
        };
    }

    @DataProvider(name = "secondCheckbox")
    public Object[][] secondCheckbox() {
        return new Object[][]{
                {"checkbox 2"}
        };
    }

    @DataProvider(name = "allCheckboxes")
    public Object[][] allCheckboxes() {
        return new Object[][]{
                {"checkbox 1", "checkbox 2"}
        };
    }
}
