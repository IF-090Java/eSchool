package academy.softserve.eschool.service;

import academy.softserve.eschool.dto.DataForLoginDTO;
import academy.softserve.eschool.model.User;
import academy.softserve.eschool.repository.UserRepository;
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

    @Autowired
    private UserRepository userRepository;

    /**
     * Generate user login in format transliterated first letter
     * of first name and last name. If there are logins containing
     * this login, add their number to the generated login.
     * @param data user data with empty login
     * @return user data with generated login
     */
    public DataForLoginDTO generateLogin(DataForLoginDTO data) {
        String login = transliteration(data.getFirstName().substring(0, 0));
        login += transliteration(data.getLastName());
        users = userRepository.findByPartOfLogin(login);
        if (!users.isEmpty())
            login += users.size();
        data.setLogin(login);
        return data;
    }


}
