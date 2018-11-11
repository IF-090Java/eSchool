package academy.softserve.eschool.service;

import academy.softserve.eschool.dto.DataForLoginDTO;
import academy.softserve.eschool.model.User;
import academy.softserve.eschool.repository.UserRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static academy.softserve.eschool.auxiliary.Transliteration.transliteration;

@Service
public class LoginGeneratorService {
    /**
     * Contains users who have generated login as part of their own login.
     */
    private List<User> users;

    @NonNull
    private UserRepository userRepository;


    /**
     * Generate user login in format transliterated first letter
     * of first name and last name. If there are logins containing
     * this login, add their number to the generated login.
     * Used on to generate login from view.
     * @param data user data with empty login
     * @return user data with generated login
     */
    public DataForLoginDTO generateLogin(DataForLoginDTO data) {
        data.setLogin(generateLogin(data.getFirstName(), data.getLastName()));
        return data;
    }

    /**
     * Generate user login in format transliterated last name
     * and first letter of first name. If there are logins containing
     * this login, add their number to the generated login.
     * @param firstName user's first name
     * @param lastName user's last name
     * @return generated login
     */
    public String generateLogin(String firstName, String lastName) {
        String login = firstName.substring(0, 0);
        login += transliteration(lastName);
        users = userRepository.findByPartOfLogin(login);
        if (!users.isEmpty())
            login += users.size();
        return login;
    }

    /**
     * Check is it user name unique or not.
     * @param userName user name(login)
     * @return true if username is unique else false.
     */
    public boolean isUnique(String userName) {
        return userRepository.findByLogin(userName) == null ? true : false;
    }

}
