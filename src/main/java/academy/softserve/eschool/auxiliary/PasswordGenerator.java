package academy.softserve.eschool.auxiliary;

import org.apache.commons.lang3.RandomStringUtils;

public class PasswordGenerator {
    /** dictionari of symbols*/
    private static final String dictionary = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
            "abcdefghijklmnopqrstuvwxyz" +
            "0123456789" +
            "!@#$%^&*_=+-/";

    /**
     * Method will generate random string based on the parameters
     * @param len the length of the random string
     * @return the random password
     */
    public static String generatePassword(int len) {
        return RandomStringUtils.random(len, dictionary);
    }
}
