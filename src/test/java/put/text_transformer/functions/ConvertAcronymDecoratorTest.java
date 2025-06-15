package put.text_transformer.functions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Convert Acronym Decorator Tests")
public class ConvertAcronymDecoratorTest {

    private TextFunction baseFunction;
    private ConvertAcronymDecorator decorator;

    @BeforeEach
    void setUp() {
        baseFunction = new BaseTextFunction();
        decorator = new ConvertAcronymDecorator(baseFunction);
    }


    @Test
    @DisplayName("Should convert 'for example' to 'e.g.'")
    void testForExample() {
        assertEquals("Use this e.g. in tests", decorator.apply("Use this for example in tests"));
        assertEquals("Many fruits e.g. apples", decorator.apply("Many fruits for example apples"));
    }

    @Test
    @DisplayName("Should convert 'that is' to 'i.e.'")
    void testThatIs() {
        assertEquals("The capital i.e. Warsaw", decorator.apply("The capital that is Warsaw"));
        assertEquals("Main language i.e. Polish", decorator.apply("Main language that is Polish"));
    }

    @Test
    @DisplayName("Should convert 'doctor' to 'dr'")
    void testDoctor() {
        assertEquals("Call dr Smith", decorator.apply("Call doctor Smith"));
        assertEquals("Visit dr Johnson", decorator.apply("Visit doctor Johnson"));
    }

    @Test
    @DisplayName("Should convert 'professor' to 'prof.'")
    void testProfessor() {
        assertEquals("Ask prof. Brown", decorator.apply("Ask professor Brown"));
        assertEquals("The prof. is here", decorator.apply("The professor is here"));
    }

    @Test
    @DisplayName("Should convert 'among others' to 'i.a.'")
    void testAmongOthers() {
        assertEquals("Students i.a. John", decorator.apply("Students among others John"));
        assertEquals("Cities i.a. Warsaw", decorator.apply("Cities among others Warsaw"));
    }

    @Test
    @DisplayName("Should convert 'and so on' to 'aso'")
    void testAndSoOn() {
        assertEquals("Fruits, vegetables aso", decorator.apply("Fruits, vegetables and so on"));
        assertEquals("Books, papers aso", decorator.apply("Books, papers and so on"));
    }

    @Test
    @DisplayName("Should convert 'note well' to 'N.B.'")
    void testNoteWell() {
        assertEquals("N.B. this is important", decorator.apply("note well this is important"));
        assertEquals("Remember N.B. the date", decorator.apply("Remember note well the date"));
    }

    @Test
    @DisplayName("Should convert 'compare' to 'cf.'")
    void testCompare() {
        assertEquals("cf. previous chapter", decorator.apply("compare previous chapter"));
        assertEquals("See cf. page 10", decorator.apply("See compare page 10"));
    }

    @Test
    @DisplayName("Should convert 'namely' to 'viz.'")
    void testNamely() {
        assertEquals("The author viz. Smith", decorator.apply("The author namely Smith"));
        assertEquals("Main topic viz. testing", decorator.apply("Main topic namely testing"));
    }

    @Test
    @DisplayName("Should convert 'and others' to 'et al.'")
    void testAndOthers() {
        assertEquals("Smith et al. wrote this", decorator.apply("Smith and others wrote this"));
        assertEquals("Authors et al. claim", decorator.apply("Authors and others claim"));
    }

    @Test
    @DisplayName("Should convert 'also known as' to 'aka'")
    void testAlsoKnownAs() {
        assertEquals("John aka Johnny", decorator.apply("John also known as Johnny"));
        assertEquals("Warsaw aka capital", decorator.apply("Warsaw also known as capital"));
    }


    @Test
    @DisplayName("Should handle mixed case")
    void testMixedCase() {
        assertEquals("Use this e.g. here", decorator.apply("Use this For Example here"));
        assertEquals("Call dr Smith", decorator.apply("Call Doctor Smith"));
        assertEquals("The prof. teaches", decorator.apply("The Professor teaches"));
    }

    @Test
    @DisplayName("Should handle multiple conversions in one text")
    void testMultipleConversions() {
        String input = "The professor said that is for example correct";
        String expected = "The prof. said i.e. e.g. correct";
        assertEquals(expected, decorator.apply(input));
    }

    @Test
    @DisplayName("Should not convert partial matches")
    void testPartialMatches() {
        assertEquals("platform example", decorator.apply("platform example"));
        assertEquals("doctoral program", decorator.apply("doctoral program"));
    }


    @Test
    @DisplayName("Should handle phrases at beginning and end")
    void testBoundaryPhrases() {
        assertEquals("e.g. this works", decorator.apply("for example this works"));
        assertEquals("This works e.g.", decorator.apply("This works for example"));
        assertEquals("prof. Smith teaches", decorator.apply("professor Smith teaches"));
    }

}