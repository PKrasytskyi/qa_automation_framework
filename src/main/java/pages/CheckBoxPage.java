    package pages;

    import org.openqa.selenium.By;
    import org.openqa.selenium.WebDriver;
    import org.openqa.selenium.WebElement;

    public class CheckBoxPage {

        WebDriver driver;

        public CheckBoxPage(WebDriver driver){
            this.driver = driver;
        }

        private WebElement findCheckBoxByName(String name){

            String cleanName = name.trim();
            String xpath = "//form[@id='checkboxes']/input[normalize-space(following-sibling::text()[1])='" + cleanName + "']";

            return driver.findElement(By.xpath(xpath));
        }

        public void selectAllCheckBoxes(String... names) {

            for (String name : names) {

                String cleanName = name.trim();

                WebElement checkbox = driver.findElement(
                        By.xpath("//form[@id='checkboxes']/input[normalize-space(following-sibling::text()[1])='" + cleanName + "']"));

                if (!checkbox.isSelected()) {
                    checkbox.click();
                }
            }
        }
            public boolean isAllCheckBoxesSelected(String... names){

            for(String name : names){

                String cleanName = name.trim();

                WebElement checkbox = driver.findElement(
                        By.xpath("//form[@id='checkboxes']/input[normalize-space(following-sibling::text()[1])='" + cleanName + "']"));

                if(!checkbox.isSelected()){
                    return false;
                }
            }
            return true;
        }

        public void deselectAllCheckBoxes(String... names){

            for(String name : names){

                String cleanName = name.trim();
                WebElement checkbox = driver.findElement(
                        By.xpath("//form[@id='checkboxes']/input[normalize-space(following-sibling::text()[1])='" + cleanName + "']"));

                if(checkbox.isSelected()){
                    checkbox.click();
                }
            }
        }

        public void selectCheckbox(String name){
            WebElement checkbox = findCheckBoxByName(name);
            if(!checkbox.isSelected()){
                checkbox.click();
            }
        }

        public void deselectCheckBox(String name){
            WebElement checkbox = findCheckBoxByName(name);
            if(checkbox.isSelected()){
                checkbox.click();
            }
        }

        public boolean isSelected(String name){
            return findCheckBoxByName(name).isSelected();
        }

    }
