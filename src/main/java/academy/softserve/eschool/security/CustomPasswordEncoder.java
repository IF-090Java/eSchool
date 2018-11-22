package academy.softserve.eschool.security;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomPasswordEncoder implements PasswordEncoder {

    private final StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();

    public CustomPasswordEncoder(String key) {
        encryptor.setPassword(key);
    }

    @Override
    public String encode(CharSequence rawPassword) {
        return encryptor.encrypt(rawPassword.toString());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        if (encryptor.decrypt(encodedPassword).equals(rawPassword))
            return true;
        return false;
    }

    public String decode(String encryptedPass) {
        return encryptor.decrypt(encryptedPass);
    }


}
