package data;

import org.testng.annotations.DataProvider;

public class AddRemoveElementsData {

    @DataProvider(name = "elementsCount")
    public Object[][] elementsCount() {
        return new Object[][]{
                {3}
        };
    }

    @DataProvider(name = "addAndDelete")
    public Object[][] addAndDeleteElements() {
        return new Object[][]{
                {3, 2}
        };
    }
}
