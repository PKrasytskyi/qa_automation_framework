package tests.ui;

import core.BaseTest;
import data.AddRemoveElementsData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddRemoveElementsTests extends BaseTest {

    @Test(dataProvider = "elementsCount", dataProviderClass = AddRemoveElementsData.class, groups = {"ui"})
    public void shouldAddElements(int count) {

        getMainPage().openAddRemoveElementsPage();
        getAddRemoveElementsPage().addElements(count);
        Assert.assertEquals(
                getAddRemoveElementsPage().getAddedElementsCount(),
                count,
                "Unexpected number of elements " + getAddRemoveElementsPage().getAddedElementsCount()
        );
    }

    @Test(dataProvider = "addAndDelete", dataProviderClass = AddRemoveElementsData.class, groups = {"ui"})
    public void addAndDeleteElements(int add, int delete) {

        getMainPage().openAddRemoveElementsPage();
        getAddRemoveElementsPage().addElements(add);
        getAddRemoveElementsPage().removeElements(delete);
        int expectedResult = add - delete;
        Assert.assertEquals(expectedResult, getAddRemoveElementsPage().getAddedElementsCount(), "Unexpected number of elements " + getAddRemoveElementsPage().getAddedElementsCount()
        );
    }
}
