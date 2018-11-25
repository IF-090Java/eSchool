package academy.softserve.eschool;

import academy.softserve.eschool.security.CustomPasswordEncoder;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ESchoolApplication {

    public static void main(String[] args) {
        SpringApplication.run(ESchoolApplication.class, args);
    }
}
