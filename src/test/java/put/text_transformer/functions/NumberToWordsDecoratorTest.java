package put.text_transformer.functions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("NumberToWords Decorator Tests")
public class NumberToWordsDecoratorTest {

    private TextFunction baseFunction;
    private NumberToWordsDecorator decorator;

    @BeforeEach
    void setUp() {
        baseFunction = new BaseTextFunction();
        decorator = new NumberToWordsDecorator(baseFunction);
    }


    @Test
    @DisplayName("Should convert zero")
    void testZero() {
        assertEquals("I have zero apples", decorator.apply("I have 0 apples"));
        assertEquals("zero items", decorator.apply("0 items"));
    }

    @Test
    @DisplayName("Should convert single digit numbers")
    void testSingleDigits() {
        assertEquals("I have one apple", decorator.apply("I have 1 apple"));
        assertEquals("Buy five items", decorator.apply("Buy 5 items"));
        assertEquals("Take nine pills", decorator.apply("Take 9 pills"));
    }

    @Test
    @DisplayName("Should convert teen numbers")
    void testTeenNumbers() {
        assertEquals("I am eleven years old", decorator.apply("I am 11 years old"));
        assertEquals("Buy thirteen items", decorator.apply("Buy 13 items"));
        assertEquals("Take nineteen pills", decorator.apply("Take 19 pills"));
    }

    @Test
    @DisplayName("Should convert tens")
    void testTens() {
        assertEquals("I have twenty apples", decorator.apply("I have 20 apples"));
        assertEquals("Buy fifty items", decorator.apply("Buy 50 items"));
        assertEquals("Take ninety pills", decorator.apply("Take 90 pills"));
    }

    @Test
    @DisplayName("Should convert compound numbers")
    void testCompoundNumbers() {
        assertEquals("I have twenty-one apples", decorator.apply("I have 21 apples"));
        assertEquals("Buy thirty-five items", decorator.apply("Buy 35 items"));
        assertEquals("Take ninety-nine pills", decorator.apply("Take 99 pills"));
    }

    @Test
    @DisplayName("Should convert hundreds")
    void testHundreds() {
        assertEquals("I have one hundred apples", decorator.apply("I have 100 apples"));
        assertEquals("Buy two hundred items", decorator.apply("Buy 200 items"));
        assertEquals("Cost is nine hundred dollars", decorator.apply("Cost is 900 dollars"));
    }

    @Test
    @DisplayName("Should convert hundreds with remainder")
    void testHundredsWithRemainder() {
        assertEquals("I have one hundred one apples", decorator.apply("I have 101 apples"));
        assertEquals("Buy two hundred fifty items", decorator.apply("Buy 250 items"));
        assertEquals("Cost is nine hundred ninety-nine dollars", decorator.apply("Cost is 999 dollars"));
    }

    @Test
    @DisplayName("Should convert thousands")
    void testThousands() {
        assertEquals("Population is one thousand", decorator.apply("Population is 1000"));
    }

    @Test
    @DisplayName("Should convert thousands with remainder")
    void testThousandsWithRemainder() {
        assertEquals("Population is one thousand one", decorator.apply("Population is 1001"));
        assertEquals("Distance is twelve thousands three hundred forty-five miles", decorator.apply("Distance is 12345 miles"));
    }

    @Test
    @DisplayName("Should handle decimal numbers")
    void testDecimalNumbers() {
        assertEquals("Price is one and fifty hundredths", decorator.apply("Price is 1.50"));
        assertEquals("Value is three and twenty-five hundredths", decorator.apply("Value is 3.25"));
        assertEquals("Temperature is zero and one hundredths", decorator.apply("Temperature is 0.01"));
    }

    @Test
    @DisplayName("Should handle decimal with zero fractional part")
    void testDecimalWithZeroFraction() {
        assertEquals("Price is five", decorator.apply("Price is 5.00"));
        assertEquals("Value is ten", decorator.apply("Value is 10.0"));
    }

    @Test
    @DisplayName("Should handle rounding for decimals")
    void testDecimalRounding() {
        assertEquals("Price is one and one hundredths", decorator.apply("Price is 1.006"));
        assertEquals("Value is two and ninety-nine hundredths", decorator.apply("Value is 2.994"));
    }

    @Test
    @DisplayName("Should handle multiple numbers in text")
    void testMultipleNumbers() {
        String input = "I have 5 apples and 3 oranges, total 8 fruits";
        String expected = "I have five apples and three oranges, total eight fruits";
        assertEquals(expected, decorator.apply(input));
    }

}