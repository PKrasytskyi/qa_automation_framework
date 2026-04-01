package tests.ui;

import core.BaseTest;
import tests.data.AddRemoveElementsData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddRemoveElementsTests extends BaseTest {

    @Test(dataProvider = "elementsCount", dataProviderClass = AddRemoveElementsData.class, groups = {"ui"})
    public void shouldAddElements(int count) {

        pages.getMainPage().openAddRemoveElementsPage();
        pages.getAddRemoveElementsPage().addElements(count);
        Assert.assertEquals(
                pages.getAddRemoveElementsPage().getAddedElementsCount(),
                count,
                "Unexpected number of elements " + pages.getAddRemoveElementsPage().getAddedElementsCount()
        );
    }

    @Test(dataProvider = "addAndDelete", dataProviderClass = AddRemoveElementsData.class, groups = {"ui"})
    public void addAndDeleteElements(int add, int delete) {

        pages.getMainPage().openAddRemoveElementsPage();
        pages.getAddRemoveElementsPage().addElements(add);
        pages.getAddRemoveElementsPage().removeElements(delete);
        int expectedResult = add - delete;
        Assert.assertEquals(expectedResult, pages.getAddRemoveElementsPage().getAddedElementsCount(),
                "Unexpected number of elements " + pages.getAddRemoveElementsPage().getAddedElementsCount()
        );
    }
}
