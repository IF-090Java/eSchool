package academy.softserve.eschool.auxiliary;

import org.apache.commons.lang3.RandomStringUtils;

public class PasswordResetTokenGenerator {
    private static final String dictionary = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
            "abcdefghijklmnopqrstuvwxyz" +
            "0123456789";
    public static String generateToken() {
        String randomString = RandomStringUtils.random(20, dictionary);
        long currentTime = System.currentTimeMillis();
        return randomString + currentTime;
    }
}
