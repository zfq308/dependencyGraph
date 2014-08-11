
package dependencygraph.utils;

import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * @author Jijo Wilson (jiwilson@expedia.com)
 */
public class InputFormatValidatorTest {

    @Test
    public void testValidInput() {
        assertTrue(InputFormatValidator.validateInputLine("B->C"));
        assertTrue(InputFormatValidator.validateInputLine("Netflix->Amazon"));
        assertTrue(InputFormatValidator.validateInputLine("1->3"));
        assertTrue(InputFormatValidator.validateInputLine("*->&"));
    }


    @Test
    public void testInvalidValidInput() {
        assertFalse(InputFormatValidator.validateInputLine("B>C"));
        assertFalse(InputFormatValidator.validateInputLine("Netflix>Amazon"));
        assertFalse(InputFormatValidator.validateInputLine("123"));
        assertFalse(InputFormatValidator.validateInputLine("->"));
        assertFalse(InputFormatValidator.validateInputLine("A->"));
        assertFalse(InputFormatValidator.validateInputLine("->B"));


    }

}
