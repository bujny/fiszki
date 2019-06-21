package tech.fiszki;

import org.junit.Test;

import tech.fiszki.data.Word;
import tech.fiszki.logic.ContentLoader;
import tech.fiszki.logic.ContentLoaderMock;
import tech.fiszki.logic.repetition_algorithm.InsufficientWordCountException;
import tech.fiszki.logic.repetition_algorithm.MainWordsSelector;
import tech.fiszki.logic.repetition_algorithm.WordSelector;
import tech.fiszki.ui.MainActivity;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
}