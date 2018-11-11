package academy.softserve.eschool.auxiliary;

import academy.softserve.eschool.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;

public class Transliteration {
    @Autowired
    private UserRepository userRepository;

    /**
     * Contains the Ukrainian letters as keys and the as value their transliteration in lowercase
     */
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

    /**
     *
     * @param word word for transliteration
     * @return transliterated word
     */
    public static String transliteration(String word) {
        word = word.toLowerCase();
        char[] chars = word.toCharArray();

        //transliterate first two letters
        String result = word.length() > 1 ? firstTwo(chars[0], chars[1]) : firstTwo(chars[0], null);

        //lsat index of this word
        int lastIndex = word.length() - 1, i;

        //begin from two because first two is transliterated
        for (i = 2; i < lastIndex; i++){
            if (chars[i] == 'з' && chars[i+1] == 'г') {
                result += "zgh";
                i++;
            } else result += letters.get(chars[i]);
        }

        // it use if last two letters == "зг"
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
    //todo bk too many if statements. Refactor it
    private static String firstTwo(Character first, Character second) {
        String result = "";
        //checking special cases of first letter
        switch (first){
            case 'є': result += "ye";
                break;
            case 'ї': result += "yi";
                break;
            case 'й': result += "y";
                break;
            case 'ю': result += "yu";
                break;
            case 'я': result += "ia";
                break;
            case 'з': if (second.equals('г'))
                return "zgh";
            default: result = letters.get(first);
        }
        result += second != null ? letters.get(second) : "";

        return result;
    }
}
