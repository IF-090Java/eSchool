package academy.softserve.eschool.auxiliary;

import academy.softserve.eschool.dto.DataForLoginDTO;
import academy.softserve.eschool.model.User;
import academy.softserve.eschool.repository.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController("/login")
@Api(description = "Login generator controller")
public class LoginGeneratorController {
    /**
     * Contains users who have generated login as part of their own login.
     */
    private List<User> users;

    @Autowired
    private UserRepository userRepository;

    private static HashMap<Character, String> letters = new HashMap<>();

    static {
        letters.put('а', "a");    letters.put('б', "b");    letters.put('в', "v");    letters.put('г', "h");
        letters.put('ґ', "g");    letters.put('д', "d");    letters.put('е', "e");    letters.put('є', "ie");
        letters.put('ж', "zh");   letters.put('з', "z");    letters.put('и', "y");    letters.put('і', "i");
        letters.put('ї', "i");    letters.put('й', "i");    letters.put('к', "k");    letters.put('л', "l");
        letters.put('м', "m");    letters.put('н', "n");    letters.put('о', "o");    letters.put('п', "p");
        letters.put('р', "r");    letters.put('с', "s");    letters.put('т', "t");    letters.put('у', "u");
        letters.put('ф', "f");    letters.put('х', "kh");   letters.put('ц', "ts");   letters.put('ч', "ch");
        letters.put('ш', "sh");   letters.put('щ', "shch"); letters.put('ь', "");     letters.put('ю', "iu");
        letters.put('я', "ia");   letters.put('\'', "");
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Login successfully generated"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @ApiOperation(value = "Create login")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/generate")
    public DataForLoginDTO getLogin(@RequestBody DataForLoginDTO person) {
        StringBuffer login = new StringBuffer(transliteration(person.getFirstName().substring(0,0)));
        login.append(transliteration(person.getLastName()));
        users = userRepository.findByPartOfLogin(login.toString());
        if (!users.isEmpty())
            makeUnique(login);
        person.setLogin(login.toString());
        return null;
    }

    /**
         * Add to login number of users who have this login as part of their own login.
     * @param login
     */
    private void makeUnique(StringBuffer login) {
        int usersLength = users.size();
        login.insert(login.length() - 1, usersLength);
    }


    /**
     *
     * @param word word for transliteration
     * @return transliterated word
     */
    public static String transliteration(String word) {
        word = word.toLowerCase();
        char[] chars = word.toCharArray();
        String result = firstTwo(chars[0], chars[1]);
        int lastIndex = word.length() - 1, i;

        for (i = 2; i < lastIndex; i++){
            if (chars[i] == 'з' && chars[i+1] == 'г') {
                result += "zgh";
                i++;
            } else result += letters.get(chars[i]);
        }

        if (i == lastIndex)
            result += letters.get(chars[lastIndex]);

        return result;
    }

    /**
     * Transliterating first two letters
     * @param first letter
     * @param second letter
     * @return transliterated letters
     */
    private static String firstTwo(Character first, Character second) {
        String result = "";
        if (second != null && (first.equals('з') && second.equals('г'))) {
            return "zgh";
        } else if (first.equals('є'))
                result += "ye";
        else if (first.equals('ї'))
            result += "yi";
        else if (first.equals('й'))
            result += "y";
        else if (first.equals('ю'))
            result += "yu";
        else if (first.equals('я'))
            result += "ia";
        else result = letters.get(first);
        result += second != null ? letters.get(second) : "";

        return result;
    }
}