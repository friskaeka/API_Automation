package Helper;

import org.apache.commons.lang3.RandomStringUtils;

import java.io.File;

public class Utility {
    public static File getJsonSchemaFile(String JSONFile) {
        return new File("src/test/java/Helper/JsonSchemaData/" + JSONFile);
    }

    public static String generateRandomEmail(){
        String allowedChars = "abcdefghijklmnopqrstuvwxyz" + "1234567890" + "_" + ".";
        String email = "";
        String temp = RandomStringUtils.randomAlphanumeric(10);
        email = temp + "@tesdata.com";
        return email;
    }

}
