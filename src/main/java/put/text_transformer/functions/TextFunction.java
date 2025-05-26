package put.text_transformer.functions;

/**
 * Represents a text transformation function.
 * <p>
 * This interface defines a single method {@link #apply(String)} that performs
 * a transformation on input text. It is intended to be implemented or decorated
 * by various classes to support different text processing operations.
 * </p>
 */
public interface TextFunction {

    /**
     * Applies a transformation to the given input text.
     *
     * @param text the input string to transform
     * @return the transformed string
     */
    String apply(String text);
}
