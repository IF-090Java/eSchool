package academy.softserve.eschool.auxiliary;

import academy.softserve.eschool.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

/**
 * This is a test class for {@link StudentService}
 * In test used {@link Parameterized} class used in conjunction with {@link org.junit.runners.JUnit4}
 */
@RunWith(Parameterized.class)
public class TransliterationTest {
    private String inputWord;
    private String expectedWord;

    public TransliterationTest(String inputWord, String expectedWord) {
        this.inputWord = inputWord;
        this.expectedWord = expectedWord;
    }

    /**
     * Prepare set data for inputWord and expectedWord.
     * @return {@link Collection} of test data.
     */
    @Parameterized.Parameters
    public static Collection data() {
        return Arrays.asList(new Object[][]{
                {"Алушта", "alushta"},
                {"Борщагівка", "borshchahivka"},
                {"Ґалаґан", "galagan"},
                {"Єнакієве", "yenakiieve"},
                {"Їжакевич", "yizhakevych"},
                {"Йосипівка", "yosypivka"},
                {"Юрій", "yurii"},
                {"Яготин", "yahotyn"},
                {"зг", "zgh"},
                {"Знам'янка", "znamianka"},
                {"Корюківка", "koriukivka"},
                {"Стрий", "stryi"},
                {"Есмань", "esman"},
                {"з", "z"}
        });
    }

    @Test
    public void transliteration() {
        assertEquals(expectedWord, Transliteration.transliteration(inputWord));
    }
}