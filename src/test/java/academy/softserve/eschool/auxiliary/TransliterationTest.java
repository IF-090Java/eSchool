package academy.softserve.eschool.auxiliary;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(Parameterized.class)
class TransliterationTest {
    private String inputWord;
    private String expectedWord;

    @Before
    public void init() {

    }

    public TransliterationTest(String inputWord, String expectedWord) {
        this.inputWord = inputWord;
        this.expectedWord = expectedWord;
    }


    @Parameterized.Parameters
    public static Collection data() {
        return Arrays.asList(new Object[][] {
                { "Алушта", "alushta" },
                { "Борщагівка", "borshchahivka" },
                { "Ґалаґан", "galagan" },
                { "Єнакієве", "yenakiieve" },
                { "Їжакевич", "yizhakevych" },
                { "Йосипівка", "yosypivka" },
                { "Юрій", "yurii" },
                { "Яготин", "yahotyn" },
                { "зг", "zgh" },
                { "Знам'янка", "znamianka" },
                { "Корюківка", "koriukivka" },
                { "Стрий", "stryi" },
                { "Есмань", "esman" }
        });
    }

    @Test
    void transliteration() {
        assertEquals(expectedWord, Transliteration.transliteration(inputWord));
    }

    @After
    public void destroy() {

    }
}