package put.text_transformer.functions;

/**
 * A basic implementation of the {@link TextFunction} interface that returns
 * the input text unchanged.
 * <p>
 * This class serves as the foundational or "base" function in the
 * Decorator design pattern used in the text transformer framework.
 * Other decorators can be layered on top of this to apply transformations.
 * </p>
 */
public class BaseTextFunction implements TextFunction {

    /**
     * Returns the input text without any modifications.
     *
     * @param text the input text
     * @return the input text unchanged
     */
    @Override
    public String apply(String text) {
        return text;
    }
}
