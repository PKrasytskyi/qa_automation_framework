package tests.Helpers;

import java.util.UUID;

public class EmailGenerator {

    private EmailGenerator(){}

    public static String uniqueEmail(){
        String suffix = UUID.randomUUID().toString().substring(0,8);
        return "autotest_" + System.currentTimeMillis() + "_" + suffix + "@mail.test";
    }
}
