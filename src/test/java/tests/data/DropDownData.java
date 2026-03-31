package data;

import org.testng.annotations.DataProvider;

public class DropDownData {

    @DataProvider(name = "dropdownOptions")
    public Object[][] dropdownOptions() {
        return new Object[][]{
                {"Option 1"},
                {"Option 2"}
        };
    }
}
