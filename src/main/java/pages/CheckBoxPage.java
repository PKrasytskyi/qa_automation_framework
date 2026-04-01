package pages;

import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckBoxPage extends BasePage {

    public CheckBoxPage(WebDriver driver) {
        super(driver);
    }

    private WebElement getCheckboxByName(String name) {
        String cleanName = name.trim();
        String xpath = "//form[@id='checkboxes']/input[normalize-space(following-sibling::text()[1])='" + cleanName + "']";
        return findElement(By.xpath(xpath));
    }

    public void selectAllCheckBoxes(String... names) {

        for (String name : names) {

            WebElement checkbox = getCheckboxByName(name);

            if (!checkbox.isSelected()) {
                checkbox.click();
            }
        }
    }

    public boolean isAllCheckBoxesSelected(String... names) {

        for (String name : names) {

            WebElement checkbox = getCheckboxByName(name);

            if (!checkbox.isSelected()) {
                return false;
            }
        }
        return true;
    }

    public void deselectAllCheckBoxes(String... names) {

        for (String name : names) {

            WebElement checkbox = getCheckboxByName(name);

            if (checkbox.isSelected()) {
                checkbox.click();
            }
        }
    }

    public void selectCheckbox(String name) {
        WebElement checkbox = getCheckboxByName(name);
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }

    public void deselectCheckBox(String name) {
        WebElement checkbox = getCheckboxByName(name);
        if (checkbox.isSelected()) {
            checkbox.click();
        }
    }

    public boolean isSelected(String name) {
        return getCheckboxByName(name).isSelected();
    }

}
