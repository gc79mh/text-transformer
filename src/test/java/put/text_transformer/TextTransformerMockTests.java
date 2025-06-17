package put.text_transformer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.InjectMocks;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import put.text_transformer.functions.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("Text Transformer Mock Tests - Component Interactions")
public class TextTransformerMockTests {

    @Mock
    private TextFunction mockBaseFunction;

    @Mock
    private ConvertAcronymDecorator mockAcronymDecorator;

    @Mock
    private NumberToWordsDecorator mockNumberDecorator;

    @Mock
    private ReverseDecorator mockReverseDecorator;

    @Mock
    private CapitalizeDecorator mockCapitalizeDecorator;

    @Mock
    private LowerCaseDecorator mockLowerCaseDecorator;

    @Mock
    private UpperCaseDecorator mockUpperCaseDecorator;

    @InjectMocks
    private TransformController transformController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(transformController).build();
    }

    @Test
    @DisplayName("Mock Call 1: Base TextFunction apply method")
    void testBaseTextFunctionApply() {
        when(mockBaseFunction.apply("test input")).thenReturn("test input");

        String result = mockBaseFunction.apply("test input");

        assertEquals("test input", result);
        verify(mockBaseFunction, times(1)).apply("test input");
    }

    @Test
    @DisplayName("Mock Call 2: ConvertAcronymDecorator with dependency injection")
    void testAcronymDecoratorWithDependency() {
        when(mockBaseFunction.apply("for example test")).thenReturn("for example test");
        when(mockAcronymDecorator.apply("for example test")).thenReturn("e.g. test");

        String baseResult = mockBaseFunction.apply("for example test");
        String decoratedResult = mockAcronymDecorator.apply("for example test");

        assertEquals("for example test", baseResult);
        assertEquals("e.g. test", decoratedResult);
        verify(mockBaseFunction, times(1)).apply("for example test");
        verify(mockAcronymDecorator, times(1)).apply("for example test");
    }

    @Test
    @DisplayName("Mock Call 3: NumberToWordsDecorator conversion")
    void testNumberToWordsDecorator() {
        when(mockNumberDecorator.apply("I have 5 apples")).thenReturn("I have five apples");

        String result = mockNumberDecorator.apply("I have 5 apples");

        assertEquals("I have five apples", result);
        verify(mockNumberDecorator, times(1)).apply("I have 5 apples");
    }

    @Test
    @DisplayName("Mock Call 4: ReverseDecorator with case preservation")
    void testReverseDecoratorCasePreservation() {
        when(mockReverseDecorator.apply("Hello")).thenReturn("Olleh");

        String result = mockReverseDecorator.apply("Hello");

        assertEquals("Olleh", result);
        verify(mockReverseDecorator, times(1)).apply("Hello");
    }

    @Test
    @DisplayName("Mock Call 5: Decorator chain simulation")
    void testDecoratorChainSimulation() {
        when(mockBaseFunction.apply("hello world")).thenReturn("hello world");
        when(mockCapitalizeDecorator.apply("hello world")).thenReturn("Hello World");
        when(mockReverseDecorator.apply("Hello World")).thenReturn("Dlrow Olleh");

        String step1 = mockBaseFunction.apply("hello world");
        String step2 = mockCapitalizeDecorator.apply(step1);
        String step3 = mockReverseDecorator.apply(step2);

        assertEquals("hello world", step1);
        assertEquals("Hello World", step2);
        assertEquals("Dlrow Olleh", step3);

        verify(mockBaseFunction, times(1)).apply("hello world");
        verify(mockCapitalizeDecorator, times(1)).apply("hello world");
        verify(mockReverseDecorator, times(1)).apply("Hello World");
    }

    @Test
    @DisplayName("Mock Call 6: Multiple decorator interactions")
    void testMultipleDecoratorInteractions() {
        when(mockAcronymDecorator.apply("professor Smith")).thenReturn("prof. Smith");
        when(mockUpperCaseDecorator.apply("prof. Smith")).thenReturn("PROF. SMITH");

        String acronymResult = mockAcronymDecorator.apply("professor Smith");
        String upperResult = mockUpperCaseDecorator.apply(acronymResult);

        assertEquals("prof. Smith", acronymResult);
        assertEquals("PROF. SMITH", upperResult);

        verify(mockAcronymDecorator, times(1)).apply("professor Smith");
        verify(mockUpperCaseDecorator, times(1)).apply("prof. Smith");
    }

    @Test
    @DisplayName("Mock Call 7: Exception handling in decorator chain")
    void testDecoratorChainExceptionHandling() {
        when(mockNumberDecorator.apply("invalid input")).thenThrow(new RuntimeException("Number conversion failed"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            mockNumberDecorator.apply("invalid input");
        });

        assertEquals("Number conversion failed", exception.getMessage());
        verify(mockNumberDecorator, times(1)).apply("invalid input");
    }

    @Test
    @DisplayName("Mock Call 8: Conditional behavior with argument matchers")
    void testConditionalBehaviorWithMatchers() {
        when(mockReverseDecorator.apply(argThat(s -> s != null && s.contains("Hello"))))
                .thenReturn("Reversed with Hello");
        when(mockReverseDecorator.apply(argThat(s -> s != null && !s.contains("Hello"))))
                .thenReturn("Reversed without Hello");

        String withHello = mockReverseDecorator.apply("Hello World");
        String withoutHello = mockReverseDecorator.apply("Goodbye World");

        assertEquals("Reversed with Hello", withHello);
        assertEquals("Reversed without Hello", withoutHello);
        verify(mockReverseDecorator, times(2)).apply(anyString());
    }

    @Test
    @DisplayName("Mock Call 9: Verify no interactions when not called")
    void testVerifyNoInteractions() {

        verifyNoInteractions(mockBaseFunction);
        verifyNoInteractions(mockAcronymDecorator);
        verifyNoInteractions(mockNumberDecorator);
    }

    @Test
    @DisplayName("Mock Call 10: Complex transformation pipeline")
    void testComplexTransformationPipeline() {
        String input = "I have 3 apples for example";
        when(mockNumberDecorator.apply(input)).thenReturn("I have three apples for example");
        when(mockAcronymDecorator.apply("I have three apples for example")).thenReturn("I have three apples e.g.");
        when(mockCapitalizeDecorator.apply("I have three apples e.g.")).thenReturn("I Have Three Apples E.g.");

        String step1 = mockNumberDecorator.apply(input);
        String step2 = mockAcronymDecorator.apply(step1);
        String finalResult = mockCapitalizeDecorator.apply(step2);

        assertEquals("I have three apples for example", step1);
        assertEquals("I have three apples e.g.", step2);
        assertEquals("I Have Three Apples E.g.", finalResult);

        verify(mockNumberDecorator, times(1)).apply(input);
        verify(mockAcronymDecorator, times(1)).apply("I have three apples for example");
        verify(mockCapitalizeDecorator, times(1)).apply("I have three apples e.g.");
    }

    @Test
    @DisplayName("Integration: Controller endpoint with MockMvc")
    void testControllerEndpointWithMockMvc() throws Exception {
        mockMvc.perform(get("/transform")
                        .param("text", "hello world")
                        .param("actions", "upper"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.text").value("hello world"))
                .andExpect(jsonPath("$.actions[0]").value("upper"));
    }
}