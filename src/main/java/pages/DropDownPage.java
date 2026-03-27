package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.stream.Collectors;

public class DropDownPage {

    private final WebDriver driver;

    public DropDownPage(WebDriver driver){
        this.driver = driver;
    }

    //Locators
    private final By dropDownList = By.id("dropdown");

    public void selectOption(String option){
        Select select = getDropDownSelect();
        List<String> availableOptions = getOptionNames();

        for (String availableOption : availableOptions) {
            if (availableOption.equals(option)) {
                select.selectByVisibleText(option);
                return;
            }
        }
    }

    public String getSelectedOption(){
        return getDropDownSelect().getFirstSelectedOption().getText();
    }

    public boolean isOptionSelected(String option){
        return getSelectedOption().equals(option);
    }

    public List<String> getOptionNames() {
        return getDropDownSelect()
                .getOptions()
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    private Select getDropDownSelect() {
        WebElement selectElement = driver.findElement(dropDownList);
        return new Select(selectElement);
    }
}
