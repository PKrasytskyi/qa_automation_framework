package tests.ui;

import core.BaseTest;
import tests.data.AddRemoveElementsData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddRemoveElementsTests extends BaseTest {

    @Test(dataProvider = "elementsCount", dataProviderClass = AddRemoveElementsData.class, groups = {"ui"})
    public void shouldAddElements(int count) {

        getPages().getMainPage().openAddRemoveElementsPage();
        getPages().getAddRemoveElementsPage().addElements(count);
        Assert.assertEquals(
                getPages().getAddRemoveElementsPage().getAddedElementsCount(),
                count,
                "Unexpected number of elements " + getPages().getAddRemoveElementsPage().getAddedElementsCount()
        );
    }

    @Test(dataProvider = "addAndDelete", dataProviderClass = AddRemoveElementsData.class, groups = {"ui"})
    public void addAndDeleteElements(int add, int delete) {

        getPages().getMainPage().openAddRemoveElementsPage();
        getPages().getAddRemoveElementsPage().addElements(add);
        getPages().getAddRemoveElementsPage().removeElements(delete);
        int expectedResult = add - delete;
        Assert.assertEquals(expectedResult, getPages().getAddRemoveElementsPage().getAddedElementsCount(),
                "Unexpected number of elements " + getPages().getAddRemoveElementsPage().getAddedElementsCount()
        );
    }
}
