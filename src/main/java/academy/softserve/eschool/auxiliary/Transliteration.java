package academy.softserve.eschool.auxiliary;

import java.util.HashMap;

public class Transliteration {
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

    public String getLogin(String firstName, String lastName) {
        return transliteration(firstName) + "." + transliteration(lastName);
    }

    public String transliteration(String word) {
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

    private static String firstTwo(char first, char second) {
        String result = "";
        if (first == 'з' && second == 'г')
            return "zgh";
        else if (first == 'є')
            result += "ye";
        else if (first == 'ї')
            result += "yi";
        else if (first == 'й')
            result += "y";
        else if (first == 'ю')
            result += "yu";
        else if (first == 'я')
            result += "ia";
        else result = letters.get(first);
        result += letters.get(second);

        return result;
    }
}
