package academy.softserve.eschool.auxiliary;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings("WeakerAccess")
@RunWith(MockitoJUnitRunner.class)
class TransliterationTest {

    private List<String> ukrainianWords = new ArrayList<>();

    private List<String> englishWords = new ArrayList<>();

    @Before
    public void init() {
        ukrainianWords.addAll(Arrays.asList("Алушта", "Борщагівка", "Ґалаґан", "Єнакієве", "Їжакевич", "Йосипівка", "Юрій",
                "Яготин", "зг", "Знам'янка", "Корюківка", "Стрий", "Есмань"));
        englishWords.addAll(Arrays.asList("alushta", "borshchahivka", "galagan", "yenakiieve", "yizhakevych", "yosypivka", "yurii",
                "yahotyn", "zgh", "znamianka", "koriukivka", "stryi", "esman"));
    }

    @Test
    void transliteration() {
        for (int i = 0, length = ukrainianWords.size(); i < length; i++)
            assertEquals(englishWords.get(i), Transliteration.transliteration(ukrainianWords.get(i)));
    }

    @After
    public void destroy() {
        ukrainianWords = null;
        englishWords = null;
    }
}