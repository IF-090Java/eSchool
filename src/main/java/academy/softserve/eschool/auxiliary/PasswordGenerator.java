package academy.softserve.eschool.auxiliary;

import java.security.SecureRandom;

//todo bk try touse http://kodejava.org/how-do-i-generate-a-random-alpha-numeric-string/ to generate password
public class PasswordGenerator {
    private static SecureRandom random = new SecureRandom();

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
        String result = "";
        for (int i = 0; i < len; i++) {
            int index = random.nextInt(dictionary.length());
            result += dictionary.charAt(index);
        }
        return result;
    }
}
