package stringhelper;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
list of tests:
1 - If the string is null, then return null;
2 - If the string is empty, then return empty;
3 - if the length of string is equal to 1, then return itself;
4 - If the length of the string is equal to two, then swap the first character with the last one;
5 - If the length of the string is greater than two, then swap the last character with the next to last one.
**/

@Test
public class StringHelperTest {
    
    private StringHelper helper;
    
    @BeforeMethod
    private void setUp() {
        helper = new StringHelper();
    }
    
    @DataProvider
    private Object[][] valuesForReverseLast2Chars() {
        return new Object[][] {
            { null, null },
            { "", "" },
            { "a", "a" },
            { "ab", "ba" },
            { "RAIN", "RANI" }
        };
    }
    
    @Test(dataProvider="valuesForReverseLast2Chars")
    public void givenStringWhenReverseLast2CharsThenReturnStringWithLast2CharsSwapped(String str, String expected) {
        String res = helper.reverseLast2Chars(str);

        assertEquals(res, expected);
    }

}
