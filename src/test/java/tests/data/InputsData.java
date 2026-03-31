package tests.data;

import org.testng.annotations.DataProvider;

public class InputsData {

    @DataProvider(name = "inputsFieldValues")
    public Object[][] inputsValue() {
        return new Object[][]{
                {"2321"},
                {"6435"},
                {"6611123212323123123121"}
        };
    }

    @DataProvider(name = "oneValue")
    public Object[][] oneValue() {
        return new Object[][]{
                {"1"}
        };
    }
}
