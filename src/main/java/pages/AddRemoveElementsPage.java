package pages;

import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddRemoveElementsPage extends BasePage {

    public AddRemoveElementsPage(WebDriver driver) {
        super(driver);
    }

    //Locators
    private final By addElementButton = By.cssSelector("#content > div > button");
    private final By deleteElementButton = By.cssSelector("#elements .added-manually");

    public void addElements(int count) {

        for (int y = 1; y <= count; y++) {
            click(addElementButton);
        }
    }

    public int getAddedElementsCount() {
        return findElements(deleteElementButton).size();
    }

    public void removeElements(int count) {
        int addedElementsCount = getAddedElementsCount();

        if (count > addedElementsCount) {
            throw new IllegalArgumentException(
                    "Cannot remove " + count + " elements because only " + addedElementsCount + " elements are present."
            );
        }

        for (int y = count; y >= 1; y--) {
            findElements(deleteElementButton).get(0).click();
        }
    }
}
