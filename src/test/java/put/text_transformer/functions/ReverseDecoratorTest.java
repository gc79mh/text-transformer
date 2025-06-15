package put.text_transformer.functions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Reverse Decorator Tests")
public class ReverseDecoratorTest {

    private TextFunction baseFunction;
    private ReverseDecorator decorator;

    @BeforeEach
    void setUp() {
        baseFunction = new BaseTextFunction();
        decorator = new ReverseDecorator(baseFunction);
    }


    @Test
    @DisplayName("Should reverse single character")
    void testSingleCharacter() {
        assertEquals("a", decorator.apply("a"));
        assertEquals("A", decorator.apply("A"));
        assertEquals("1", decorator.apply("1"));
    }


    @Test
    @DisplayName("Should preserve case positions in mixed case")
    void testMixedCasePreservation() {
        assertEquals("Olleh", decorator.apply("Hello"));
        assertEquals("Dlrow", decorator.apply("World"));
    }

    @Test
    @DisplayName("Should handle complex mixed case")
    void testComplexMixedCase() {
        assertEquals("TsEt", decorator.apply("TeSt"));
        assertEquals("CbA", decorator.apply("AbC"));
        assertEquals("GfEdCbA", decorator.apply("AbCdEfG"));
    }

    @Test
    @DisplayName("Should handle strings with spaces")
    void testStringsWithSpaces() {
        assertEquals("Dlrow Olleh", decorator.apply("Hello World"));
        assertEquals("EhT tSET", decorator.apply("TeSt THE"));
    }

    @Test
    @DisplayName("Should handle numbers and special characters")
    void testNumbersAndSpecialChars() {
        assertEquals("321", decorator.apply("123"));
        assertEquals("!dlrow", decorator.apply("world!"));
        assertEquals("@321 olleh", decorator.apply("hello 123@"));
    }

    @Test
    @DisplayName("Should handle palindromes")
    void testPalindromes() {
        assertEquals("aba", decorator.apply("aba"));
        assertEquals("AbA", decorator.apply("AbA"));
        assertEquals("12321", decorator.apply("12321"));
    }

    @Test
    @DisplayName("Should preserve case in long mixed strings")
    void testLongMixedStrings() {
        String input = "ThIs Is A tEsT";
        String result = decorator.apply(input);
        assertEquals("TsEt A sI sIhT", result);
    }

    @Test
    @DisplayName("Should handle all uppercase")
    void testAllUppercase() {
        assertEquals("TSET", decorator.apply("TEST"));
        assertEquals("DLROW OLLEH", decorator.apply("HELLO WORLD"));
    }

    @Test
    @DisplayName("Should handle all lowercase")
    void testAllLowercase() {
        assertEquals("tset", decorator.apply("test"));
        assertEquals("dlrow olleh", decorator.apply("hello world"));
    }

}