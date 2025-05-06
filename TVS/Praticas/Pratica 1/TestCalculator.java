package ap;
 
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test 
public class TestCalculator {
    
    private Calculator _calc;
    
    @BeforeMethod
    private void setUp() {
        _calc = new Calculator("test");
    }
    
    @DataProvider
    private Object[][] validValuesForConstructor() {
        return new Object[][] {
            {"ab"},
            {"abcde"},
            {"abc"}
        };
    }
    
    @Test(dataProvider = "validValuesForConstructor")
    public void givenValidNameWhenCreatingThenCreate(String name) {
        Calculator calc = new Calculator(name);
        
        assertEquals(calc.getName(), name);
        assertEquals(calc.getNumberOfOperations(), 0);
    }

    @DataProvider
    private Object[][] invalidValuesForConstructor() {
        return new Object[][] {
            {"a"},
            {"abcdef"},
            {null}
        };
    }
    
//    Annotation way
    @Test(dataProvider = "invalidValuesForConstructor", expectedExceptions = IllegalArgumentException.class)
    public void givenInvalidNameWhenCreatingThenThrowException(String name) {
        new Calculator(name);
        
//        Try catch way
//        try {
//            Calculator calc = new Calculator(name);
//            fail("invalid name should throw an exception");
//        }
//        catch (IllegalArgumentException e) {
//            // Do nothing
//        }
        
//        Assert throw way
//        assertThrows(IllegalArgumentException.class, () -> new Calculator(name));
    }

    @DataProvider
    private Object[][] validValuesForSum() {
        return new Object[][] {
            { 1, 2, 3 },
            { 0, 0, 0 },
            { -2, -5, -7 },
            { 2, -5, -3 },
            { -8, 9, 1 },
            { null, 8, 8 },
            { 0, null, 0 },
            { null, null, 0}
        };
    }
    
    @Test(dataProvider = "validValuesForSum")
    public void givenValuesToAddWhenSumThenReturnsResult(Integer i1, Integer i2, Integer expecteRes) {
        
        Integer res = _calc.sum(i1, i2);
        assertEquals(res, expecteRes);
        assertEquals(_calc.getNumberOfOperations(), 1);
    }

    @DataProvider
    private Object[][] validValuesForDivide() {
        return new Object[][] {
            { 1, 1, 1 },
            { 4, 2, 2 },
            { -5, 2, -2 },
            { 0, 1, 0 },
            { null, 4, 0 }
        };
    }
    
    @Test(dataProvider = "validValuesForDivide")
    public void givenValuesToDivideWhenDividingThenReturnsResult(Integer i1, Integer i2, Integer expecteRes) {
        Integer res = _calc.divide(i1, i2);
        assertEquals(res, expecteRes);
        assertEquals(_calc.getNumberOfOperations(), 1);
    }

    @DataProvider
    private Object[][] invalidValuesForDivide() {
        return new Object[][] {
            { 5, 0 },
            { 7, null },
            { null, null },
            { 0, null }
        };
    }
    
    @Test(dataProvider = "invalidValuesForDivide")
    public void givenInvaluesToDivideWhenDividingThenThrowException(Integer i1, Integer i2) {
        assertThrows(IllegalArgumentException.class, () -> _calc.divide(i1, i2));
        assertEquals(_calc.getNumberOfOperations(), 0);
    }
    
}