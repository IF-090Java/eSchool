package academy.softserve.eschool.auxiliary;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TransliterationTest {

    private static List<String> ukrainianWords = new ArrayList<>();

    private static List<String> englishWords = new ArrayList<>();

    @BeforeClass
    public static void init() {
        ukrainianWords.addAll(Arrays.asList("Алушта", "Борщагівка", "Ґалаґан", "Єнакієве", "Їжакевич", "Йосипівка", "Юрій",
                "Яготин", "зг", "Знам'янка", "Корюківка", "Стрий", "Есмань", "З"));
        englishWords.addAll(Arrays.asList("alushta", "borshchahivka", "galagan", "yenakiieve", "yizhakevych", "yosypivka", "yurii",
                "yahotyn", "zgh", "znamianka", "koriukivka", "stryi", "esman", "z"));
    }

    @Test
    void transliteration() {
        for (int i = 0, length = ukrainianWords.size(); i < length; i++)
            assertEquals(englishWords.get(i), Transliteration.transliteration(ukrainianWords.get(i)));
    }

    @AfterClass
    public void destroy() {
        ukrainianWords = null;
        englishWords = null;
    }
}