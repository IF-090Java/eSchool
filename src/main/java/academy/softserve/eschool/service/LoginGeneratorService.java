package academy.softserve.eschool.service;

import academy.softserve.eschool.dto.DataForLoginDTO;
import academy.softserve.eschool.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static academy.softserve.eschool.auxiliary.Transliteration.transliteration;

@Service
@RequiredArgsConstructor
public class LoginGeneratorService {
    /**
     * Contains users logins who contains generated login as part of their own login.
     */
    private List<String> usersLogins;

    @NonNull
    private UserRepository userRepository;


    /**
     * Generate user login in format transliterated first letter
     * of first name and last name. If there are logins containing
     * this login, add first free number to the end of login.
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
     * this login, add first free number to the end of login.
     * @param firstName user's first name
     * @param lastName user's last name
     * @return generated login
     */
    public String generateLogin(String firstName, String lastName) {
        Character firstLetter = firstName.charAt(0);
        String login = transliteration(firstLetter.toString());
        login += transliteration(lastName);
        usersLogins = userRepository.findUsersWithPartOfLogin(login).stream().map(user -> user.getLogin()).collect(Collectors.toCollection(ArrayList::new));
        int possibleEndingOfLogin = 0;
        if (usersLogins.contains(login))
            do{
                possibleEndingOfLogin++;
            }while (usersLogins.contains(login + possibleEndingOfLogin));
        return possibleEndingOfLogin != 0 ? login + possibleEndingOfLogin : login;
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
